package kr.co.apfactory.storesolution.global.file.controller;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.global.file.application.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class FileController {
    private final Environment environment;

    private final FileService fileService;

    @GetMapping("/store/image/download/{id}")
    public void downloadStoreImageFiles(HttpServletResponse response, @PathVariable("id") Long id) {
        fileService.downloadStoreImageFile(response, id, true, environment.getProperty("store.upload.path"));
    }

    @GetMapping("/store/image/viewer")
    public String gotoImageViewerPopup(Model model, Long id) throws Exception {
        List<FileDTO> fileList = fileService.getStoreImageList(id);
        if (fileList == null || fileList.isEmpty()) {
            throw new Exception();
        }
        model.addAttribute("imageList", fileList);
        return "views/popup/imageViewer";
    }

    @GetMapping("/about/image/download/{id}")
    public void downloadAboutImageFiles(HttpServletResponse response, @PathVariable("id") Long id) {
        fileService.downloadStoreImageFile(response, id, true, environment.getProperty("store.upload.path"));
    }

    @GetMapping("/logo/image/download/{id}")
    public void downloadLogoImageFiles(HttpServletResponse response, @PathVariable("id") Long id) {
        fileService.downloadStoreImageFile(response, id, true, environment.getProperty("store.upload.path"));
    }
}
