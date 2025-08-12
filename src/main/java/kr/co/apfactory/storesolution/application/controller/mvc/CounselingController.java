package kr.co.apfactory.storesolution.application.controller.mvc;

import kr.co.apfactory.storesolution.application.service.CustomerService;
import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.global.file.application.service.FileService;
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
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/counseling")
public class CounselingController {
    private final CustomerService customerService;

    private final FileService fileService;

    @GetMapping("/customer/list")
    public String gotoCustomerList(@PageableDefault(page = 0, size = 5, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable,
                                   @ModelAttribute("searchDTO") SearchDTO searchDTO, Model model) {
        model.addAttribute("itemList", customerService.getCustomerList(pageable, searchDTO));
        return "views/counseling/customer/list";
    }

    @GetMapping("/customer/register")
    public String gotoCustomerRegisterForm(Model model) {
        return "views/counseling/customer/register";
    }

    @GetMapping("/about")
    public String openAboutPopup(Model model, Long id) {
        // 상담판 파일타입 = 3
        List<FileDTO> fileList = fileService.getAboutImageList();

        model.addAttribute("imageList", fileList);
        return "views/popup/about";
    }

    @GetMapping("/fabric-item")
    public String gotoFabricAndItemPage(Model model, Long id) {
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
