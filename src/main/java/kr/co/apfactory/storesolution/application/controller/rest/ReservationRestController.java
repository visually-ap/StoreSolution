package kr.co.apfactory.storesolution.application.controller.rest;

import kr.co.apfactory.storesolution.application.service.CustomerService;
import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationRegisterDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEnvironmentUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
    private final Environment environment;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final SiteService siteService;

    private final CustomerService customerService;

    @GetMapping("/employee/schedule/list")
    public ResponseEntity<ResponseDTO> getEmployeeScheduleList(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(userService.getEmployeeScheduleList(date));
    }

    @PostMapping("/customer/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerCustomerReservation(@RequestBody ReqReservationRegisterDTO reqReservationRegisterDTO) {
        return ResponseEntity.ok(customerService.registerCustomerReservation(reqReservationRegisterDTO));
    }

    @GetMapping("/customer/list")
    public ResponseEntity<ResponseDTO> getCustomerList(String searchKeyword, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate searchDate) {
        ResEnvironmentUpdateDTO resEnvironmentUpdateDTO = siteService.getSiteEnvironment();
        return ResponseEntity.ok(customerService.getCustomerList(searchKeyword, searchDate, resEnvironmentUpdateDTO));
    }

    @GetMapping("/customer/detail")
    public ResponseEntity<ResponseDTO> getCustomerDetailByReservationId(Long reservationId) {
        return ResponseEntity.ok(customerService.getCustomerDetailByReservationId(reservationId));
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateReservation(@RequestBody ReqReservationUpdateDTO reqReservationUpdateDTO) {
        return ResponseEntity.ok(customerService.updateConsultingReservation(reqReservationUpdateDTO));
    }
}
