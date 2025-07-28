package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqEmployeeRegisterDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqEmployeeUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqPasswordUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeDetailDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeScheduleDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResMypageDTO;
import kr.co.apfactory.storesolution.domain.entity.CodeType;
import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.domain.entity.User;
import kr.co.apfactory.storesolution.domain.entity.UserLoginHistory;
import kr.co.apfactory.storesolution.domain.repository.UserLoginHistoryRepository;
import kr.co.apfactory.storesolution.domain.repository.UserRepository;
import kr.co.apfactory.storesolution.global.i18n.I18nUtility;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

import static kr.co.apfactory.storesolution.global.enums.AuthorityEnum.EMPLOYEE;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final I18nUtility i18nUtility;

    private final UserRepository userRepository;

    private final UserLoginHistoryRepository userLoginHistoryRepository;

    public void saveLoginHistory() {
        userLoginHistoryRepository.save(UserLoginHistory.builder()
                .event(1)
                .user(User.builder().id(LoginUser.getDetails().getId()).build())
                .build());
    }

    public ResMypageDTO getUserInfoForMypage(HttpServletRequest request) {
        ResMypageDTO dto = userRepository.selectUserInfoForMypage(i18nUtility.getLanguage(request), LoginUser.getDetails().getId());
        return dto;
    }

    public ResponseDTO updatePassword(ReqPasswordUpdateDTO dto) {
        Long userId = LoginUser.getDetails().getId();

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(dto.getNowPassword(), user.getPassword())) {
            return ResponseDTO.builder()
                    .isSuccess(false)
                    .message("현재 비밀번호가 일치하지 않습니다.")
                    .build();
        }

        // 새 비밀번호 확인
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return ResponseDTO.builder()
                    .isSuccess(false)
                    .message("비밀번호가 일치하지 않습니다.")
                    .build();
        }

        // 비밀번호 변경
        user.updatePassword(passwordEncoder.encode(dto.getPassword()));

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("비밀번호를 변경하였습니다.\n변경한 비밀번호로 다시 로그인 하시기 바랍니다.")
                .build();
    }

    public ResponseDTO checkLoginId(String loginId) {
        if (userRepository.existsByLoginId(loginId)) {
            return ResponseDTO.builder()
                    .isSuccess(false)
                    .message("이미 사용 중인 아이디입니다.")
                    .build();
        } else {
            return ResponseDTO.builder()
                    .isSuccess(true)
                    .message("사용할 수 있는 아이디입니다.")
                    .build();
        }
    }

    public ResponseDTO registerEmployee(ReqEmployeeRegisterDTO dto) {
        // 아이디 중복 체크
        if (userRepository.existsByLoginId(dto.getLoginId())) {
            return ResponseDTO.builder()
                    .isSuccess(false)
                    .message("이미 사용 중인 아이디입니다.")
                    .build();
        }

        // 사용자 생성
        userRepository.save(User.builder()
                .loginId(dto.getLoginId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .mobileNumber(dto.getMobileNumber())
                .position(CodeType.builder().id(dto.getPosition()).build())
                .authority(EMPLOYEE.getAuth()) // 기본 직원 역할
                .store(Store.builder().id(LoginUser.getDetails().getStoreId()).build())
                .build());

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("직원이 등록되었습니다.")
                .build();
    }

    public ResEmployeeDetailDTO getEmployeeDetail(HttpServletRequest request, Long userId) {
        ResEmployeeDetailDTO dto = userRepository.selectEmployeeDetail(i18nUtility.getLanguage(request), userId, LoginUser.getDetails().getStoreId());
        if (dto == null) {
            throw new IllegalArgumentException();
        }
        return dto;
    }

    public ResponseDTO resetEmployeePassword(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        user.updatePassword(passwordEncoder.encode(user.getMobileNumber()));

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("비밀번호를 휴대전화번호로 초기화하였습니다.")
                .build();
    }

    public ResponseDTO updateEmployee(ReqEmployeeUpdateDTO req) {
        // 사용자 조회
        User user = userRepository.findById(req.getUserId()).orElseThrow(IllegalArgumentException::new);

        // 이름, 전화번호, 직책 변경
        user.updateName(req.getName());
        user.updateMobileNumber(req.getMobileNumber());
        user.updatePosition(CodeType.builder().id(req.getPosition()).build());

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("직원 정보를 수정하였습니다.")
                .build();
    }

    public ResponseDTO deleteEmployee(Long userId) {
        // ID로 직원 조회 (없으면 예외 발생)
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        // 직원 정보 삭제
        user.delete();

        // 삭제 성공 응답 반환
        return ResponseDTO.builder()
                .isSuccess(true)
                .message("직원 정보를 삭제하였습니다.")
                .build();
    }

    public List<ResEmployeeListDTO> getEmployeeList() {
        List<ResEmployeeListDTO> employeeList = userRepository.selectEmployeeList(LoginUser.getDetails().getStoreId());

        return employeeList;
    }

    @Transactional
    public ResponseDTO getEmployeeScheduleList(LocalDate date) {
        List<ResEmployeeScheduleDTO> scheduleList = userRepository.selectEmployeeScheduleList(LoginUser.getDetails().getStoreId(), date);

        if (scheduleList.size() < 5) {
            int limit = 5 - scheduleList.size();

            for (int i = 0; i < limit; i++) {
                scheduleList.add(
                        ResEmployeeScheduleDTO.builder()
                                .name("")
                                .build()
                );
            }
        }

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(scheduleList)
                .build();
    }
}
