package kr.co.apfactory.storesolution.application.controller.rest;

import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqEmployeeRegisterDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqEmployeeUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqEnvironmentUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
    private final Environment environment;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final SiteService siteService;

    @GetMapping("/employee/schedule/list")
    public ResponseEntity<ResponseDTO> getEmployeeScheduleList() {
        return ResponseEntity.ok(userService.getEmployeeScheduleList());
    }
}
