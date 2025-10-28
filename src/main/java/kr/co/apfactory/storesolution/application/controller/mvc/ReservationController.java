package kr.co.apfactory.storesolution.application.controller.mvc;

import kr.co.apfactory.storesolution.application.service.CustomerService;
import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.application.service.StoreService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerPaymentListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerPurchaseListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final StoreService storeService;

    private final SiteService siteService;

    private final CustomerService customerService;

    @GetMapping("/main")
    public String gotoReservationMainPage(Model model) {
        model.addAttribute("item", storeService.getStoreInfo());
        model.addAttribute("employeeList", storeService.getEmployeeList());
        model.addAttribute("siteEnvDto", siteService.getSiteEnvironment());
        return "views/reservation/main";
    }

    @GetMapping("/register")
    public String gotoNewCustomerRegister(Model model) {
        model.addAttribute("employeeList", storeService.getEmployeeList());
        model.addAttribute("userId", LoginUser.getDetails().getId());
        model.addAttribute("siteEnvDto", siteService.getSiteEnvironment());
        return "views/reservation/register";
    }

    @GetMapping("/popup/customer/detail")
    public String openCustomerDetailPopup(Model model, Long customerId) throws Exception {
        model.addAttribute("customerInfo", customerService.getCustomerDetailById(customerId));
        model.addAttribute("partnerList", storeService.getConsultingPartnerList());
        List<ResCustomerPurchaseListDTO> purchaseList = customerService.getCustomerPurchaseList(customerId);
        model.addAttribute("purchaseList", purchaseList);
        List<ResCustomerPaymentListDTO> paymentList = customerService.getCustomerPaymentList(customerId);
        model.addAttribute("paymentList", paymentList);

        BigDecimal totalPurchase = BigDecimal.ZERO;
        for (ResCustomerPurchaseListDTO dto : purchaseList) {
            totalPurchase = totalPurchase.add(dto.getPrice());
        }
        BigDecimal totalPayment = BigDecimal.ZERO;
        BigDecimal totalUnpaid = totalPurchase;
        for (int i = paymentList.size() - 1; i >= 0; i--) {
            totalUnpaid = totalUnpaid.subtract(paymentList.get(i).getAmount());
            paymentList.get(i).setOutstanding(totalUnpaid);
            totalPayment = totalPayment.add(paymentList.get(i).getAmount());
        }
        model.addAttribute("totalUnpaid", totalUnpaid);
        model.addAttribute("counselingList", customerService.getCounselingList(customerId));
        model.addAttribute("rentalList", customerService.getCustomerRentalList(customerId));
        model.addAttribute("reservationList", customerService.getCustomerReservationList(customerId, siteService.getSiteEnvironment()));
        model.addAttribute("employeeList", storeService.getEmployeeList());
        model.addAttribute("siteEnvDto", siteService.getSiteEnvironment());
        return "views/popup/customerDetail";
    }
}
