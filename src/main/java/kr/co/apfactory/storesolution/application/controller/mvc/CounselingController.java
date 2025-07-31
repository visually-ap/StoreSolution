package kr.co.apfactory.storesolution.application.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/counseling")
public class CounselingController {
    @GetMapping("/customer/list")
    public String gotoCustomerList(Model model) {
        return "views/counseling/customer/list";
    }

    @GetMapping("/customer/register")
    public String gotoCustomerRegisterForm(Model model) {
        return "views/counseling/customer/register";
    }

    @GetMapping("/about")
    public String gotoAboutPage(Model model) {
        return "views/counseling/about";
    }

    @GetMapping("/fabric-item")
    public String gotoFabricAndItemPage(Model model) {
        return "views/counseling/fabricItem";
    }

    @GetMapping("/design")
    public String gotoDesignPage(Model model) {
        return "views/counseling/design";
    }

    @GetMapping("/size")
    public String gotoSize(Model model) {
        return "views/counseling/size";
    }
}
