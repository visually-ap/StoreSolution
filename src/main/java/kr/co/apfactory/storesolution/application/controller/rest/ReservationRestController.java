package kr.co.apfactory.storesolution.application.controller.rest;

import kr.co.apfactory.storesolution.application.service.CustomerService;
import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.application.service.StoreService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.*;
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

    private final StoreService storeService;

    @GetMapping("/employee/schedule/list")
    public ResponseEntity<ResponseDTO> getEmployeeScheduleList(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(storeService.getEmployeeScheduleList(date));
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

    @PostMapping("/popup/partner/detail")
    public ResponseEntity<ResponseDTO> savePartnerAndGetConsultingPartnerDetailById(Long customerId, Long partnerId) {
        return ResponseEntity.ok(customerService.savePartnerAndGetConsultingPartnerDetailById(customerId, partnerId));
    }

    @PostMapping("/customer/purchase/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerCustomerPurchase(@RequestBody ReqCustomerPurchaseDTO reqCustomerPurchaseDTO) {
        return ResponseEntity.ok(customerService.registerCustomerPurchase(reqCustomerPurchaseDTO));
    }

    @GetMapping("/customer/purchase/detail")
    public ResponseEntity<ResponseDTO> getCustomerPurchaseDetailById(Long purchaseId) {
        return ResponseEntity.ok(customerService.getCustomerPurchaseDetailById(purchaseId));
    }

    @PostMapping("/customer/purchase/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateCustomerPurchase(@RequestBody ReqCustomerPurchaseDTO reqCustomerPurchaseDTO) {
        return ResponseEntity.ok(customerService.updateCustomerPurchase(reqCustomerPurchaseDTO));
    }

    @PostMapping("/customer/purchase/delete")
    @Transactional
    public ResponseEntity<ResponseDTO> deleteCustomerPurchase(Long purchaseId) {
        return ResponseEntity.ok(customerService.deleteCustomerPurchase(purchaseId));
    }

    @PostMapping("/customer/payment/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerCustomerPayment(@RequestBody ReqCustomerPaymentDTO reqCustomerPaymentDTO) {
        return ResponseEntity.ok(customerService.registerCustomerPayment(reqCustomerPaymentDTO));
    }

    @GetMapping("/customer/payment/detail")
    public ResponseEntity<ResponseDTO> getCustomerPaymentDetailById(Long paymentId) {
        return ResponseEntity.ok(customerService.getCustomerPaymentDetailById(paymentId));
    }

    @PostMapping("/customer/payment/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateCustomerPayment(@RequestBody ReqCustomerPaymentDTO reqCustomerPaymentDTO) {
        return ResponseEntity.ok(customerService.updateCustomerPayment(reqCustomerPaymentDTO));
    }

    @PostMapping("/customer/payment/delete")
    @Transactional
    public ResponseEntity<ResponseDTO> deleteCustomerPayment(Long paymentId) {
        return ResponseEntity.ok(customerService.deleteCustomerPayment(paymentId));
    }

    @GetMapping("/rental/itemList")
    public ResponseEntity<ResponseDTO> getRentalItemList(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate
            , @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate requestDate
            , String searchKeyword) {
        return ResponseEntity.ok(storeService.getRentalItemList(fromDate, requestDate, searchKeyword));
    }

    @PostMapping("/customer/rental/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerCustomerRental(@RequestBody ReqRentalDTO reqRentalDTO) {
        return ResponseEntity.ok(customerService.registerCustomerRental(reqRentalDTO));
    }

    @GetMapping("/customer/rental/detail")
    public ResponseEntity<ResponseDTO> getCustomerRentalDetailById(Long rentalId) {
        return ResponseEntity.ok(customerService.getCustomerRentalDetailById(rentalId));
    }

    @PostMapping("/customer/rental/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateCustomerRental(@RequestBody ReqRentalDTO reqRentalDTO) {
        return ResponseEntity.ok(customerService.updateCustomerRental(reqRentalDTO));
    }

    @PostMapping("/customer/rental/complete")
    @Transactional
    public ResponseEntity<ResponseDTO> completeCustomerRental(Long rentalId) {
        return ResponseEntity.ok(customerService.completeCustomerRental(rentalId));
    }

    @PostMapping("/customer/rental/complete/cancel")
    @Transactional
    public ResponseEntity<ResponseDTO> cancelCompleteCustomerRental(Long rentalId) {
        return ResponseEntity.ok(customerService.cancelCompleteCustomerRental(rentalId));
    }

    @PostMapping("/customer/rental/back")
    @Transactional
    public ResponseEntity<ResponseDTO> backCustomerRental(Long rentalId) {
        return ResponseEntity.ok(customerService.backCustomerRental(rentalId));
    }

    @PostMapping("/customer/rental/back/cancel")
    @Transactional
    public ResponseEntity<ResponseDTO> cancelBackCustomerRental(Long rentalId) {
        return ResponseEntity.ok(customerService.cancelBackCustomerRental(rentalId));
    }

    @PostMapping("/customer/rental/todate/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateCustomerRentalDate(Long rentalId, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
        return ResponseEntity.ok(customerService.updateCustomerRentalDate(rentalId, toDate));
    }

    @PostMapping("/customer/rental/delete")
    @Transactional
    public ResponseEntity<ResponseDTO> deleteCustomerRental(Long rentalId) {
        return ResponseEntity.ok(customerService.deleteCustomerRental(rentalId));
    }

    @PostMapping("/customer/reservation/register")
    @Transactional
    public ResponseEntity<ResponseDTO> registerCustomerReservation(@RequestBody ReqCustomerReservationDTO reqCustomerReservationDTO) {
        return ResponseEntity.ok(customerService.registerCustomerReservation(reqCustomerReservationDTO));
    }

    @PostMapping("/customer/reservation/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updateCustomerReservation(@RequestBody ReqCustomerReservationDTO reqCustomerReservationDTO) {
        return ResponseEntity.ok(customerService.updateCustomerReservation(reqCustomerReservationDTO));
    }

    @GetMapping("/customer/reservation/detail")
    public ResponseEntity<ResponseDTO> getCustomerReservationDetail(Long reservationId) {
        return ResponseEntity.ok(customerService.getCustomerReservationDetail(reservationId));
    }

    @PostMapping("/customer/reservation/complete")
    @Transactional
    public ResponseEntity<ResponseDTO> completeCustomerReservation(Long reservationId) {
        return ResponseEntity.ok(customerService.completeCustomerReservation(reservationId));
    }
}
