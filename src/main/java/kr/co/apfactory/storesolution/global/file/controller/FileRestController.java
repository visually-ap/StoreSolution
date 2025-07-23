package kr.co.apfactory.storesolution.global.file.controller;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.global.file.application.service.FileService;
import kr.co.apfactory.storesolution.global.file.domain.dto.UploadFilesDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileRestController {
    private final FileService fileService;

    @PostMapping("/images/upload")
    @Transactional
    public ResponseEntity<ResponseDTO> uploadSettingImages(@ModelAttribute UploadFilesDTO dto) throws Exception {
        return ResponseEntity.ok(fileService.uploadSettingImages(dto));
    }
}
