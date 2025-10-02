package kr.co.apfactory.storesolution.application.controller.rest;

import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.application.service.StoreService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.*;
import kr.co.apfactory.storesolution.global.file.domain.dto.UploadFilesDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/setting")
public class SettingRestController {
    private final Environment environment;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final SiteService siteService;
    private final StoreService storeService;

    @PostMapping("/id/check")
    public ResponseEntity<ResponseDTO> checkLoginId(String loginId) {
        return ResponseEntity.ok(userService.checkLoginId(loginId));
    }

    @PostMapping("/employee/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerEmployee(@RequestBody ReqEmployeeRegisterDTO dto) {
        return ResponseEntity.ok(userService.registerEmployee(dto));
    }

    @PostMapping("/employee/password/reset/{userId}")
    @Transactional
    public ResponseEntity<ResponseDTO> resetEmployeePassword(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.resetEmployeePassword(userId));
    }

    /**
     * 직원 정보를 수정한다.
     */
    @PostMapping("/employee/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateEmployee(@RequestBody ReqEmployeeUpdateDTO req) {
        // 직원 정보 수정 요청을 서비스에 위임
        return ResponseEntity.ok(userService.updateEmployee(req));
    }

    /**
     * 직원 정보를 삭제한다.
     */
    @PostMapping("/employee/delete/{userId}")
    @Transactional
    public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteEmployee(userId));
    }

    /**
     * 환경설정 저장
     */
    @PostMapping("/environment/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateEnvironment(@RequestBody ReqEnvironmentUpdateDTO dto) {
        siteService.updateEnvironment(dto);

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .result(true)
                        .message("환경설정이 저장되었습니다.")
                        .build()
        );
    }

    @PostMapping("/partner/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerConsultingPartner(@RequestBody ReqConsultingPartnerRegisterDTO dto) {
        return ResponseEntity.ok(userService.registerConsultingPartner(dto));
    }

    @PostMapping("/partner/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateConsultingPartner(@RequestBody ReqConsultingPartnerUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateConsultingPartner(dto));
    }

    @PostMapping("/partner/delete/{partnerId}")
    @Transactional
    public ResponseEntity<ResponseDTO> updateConsultingPartner(@PathVariable Long partnerId) {
        return ResponseEntity.ok(userService.deleteConsultingPartner(partnerId));
    }

    @PostMapping("/rental/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerRentalItem(@RequestBody ReqRentalItemDTO dto) {
        return ResponseEntity.ok(storeService.registerRentalItem(dto));
    }

    @PostMapping("/rental/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateRentalItem(@RequestBody ReqRentalItemDTO dto) {
        return ResponseEntity.ok(storeService.updateRentalItem(dto));
    }

    @PostMapping("/rental/delete/{rentalItemId}")
    @Transactional
    public ResponseEntity<ResponseDTO> updateRentalItem(@PathVariable Long rentalItemId) {
        return ResponseEntity.ok(storeService.deleteRentalItem(rentalItemId));
    }
}
