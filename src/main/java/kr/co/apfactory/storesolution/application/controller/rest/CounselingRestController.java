package kr.co.apfactory.storesolution.application.controller.rest;

import kr.co.apfactory.storesolution.application.service.CustomerService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqFabricSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqPasswordUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCounselingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/counseling")
public class CounselingRestController {
    private final Environment environment;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final CustomerService customerService;

    @PostMapping("/init")
    @Transactional
    public ResponseEntity<ResponseDTO> initCounseling(HttpServletRequest request, Long id) {
        HttpSession session = request.getSession();
        session.removeAttribute("counseling");

        session.setAttribute("counseling", customerService.getCounselingDetail(id));
        return ResponseEntity.ok(ResponseDTO.builder()
                .isSuccess(true)
                .build());
    }

    @PostMapping("/fabric/save")
    @Transactional
    public ResponseEntity<ResponseDTO> saveFabricData(HttpServletRequest request, @RequestBody ReqFabricSaveDTO reqFabricSaveDTO) {
        HttpSession session = request.getSession();

        ResCounselingDTO resCounselingDTO = (ResCounselingDTO) session.getAttribute("counseling");
        if (resCounselingDTO == null) {
            resCounselingDTO = new ResCounselingDTO();
        }

        resCounselingDTO.updateFabricData(reqFabricSaveDTO);

        session.removeAttribute("counseling");
        session.setAttribute("counseling", resCounselingDTO);
        return ResponseEntity.ok(ResponseDTO.builder()
                .isSuccess(true)
                .build());
    }

    @PostMapping("/design/save")
    @Transactional
    public ResponseEntity<ResponseDTO> saveDesignData(HttpServletRequest request, @RequestBody ReqFabricSaveDTO reqFabricSaveDTO) {
        HttpSession session = request.getSession();

        ResCounselingDTO resCounselingDTO = (ResCounselingDTO) session.getAttribute("counseling");
//        if (resCounselingDTO == null) {
//            resCounselingDTO = new ResCounselingDTO();
//        }
//
//        resCounselingDTO.updateFabricData(reqFabricSaveDTO);
//
//        session.removeAttribute("counseling");
//        session.setAttribute("counseling", resCounselingDTO);
        return ResponseEntity.ok(ResponseDTO.builder()
                .isSuccess(true)
                .build());
    }
}
