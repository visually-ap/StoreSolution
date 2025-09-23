package kr.co.apfactory.storesolution.application.controller.rest;

import kr.co.apfactory.storesolution.application.service.CodeService;
import kr.co.apfactory.storesolution.application.service.CounselingService;
import kr.co.apfactory.storesolution.application.service.CustomerService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqDesignSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqFabricSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqSizeSaveDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/counseling")
public class CounselingRestController {
    private final CounselingService counselingService;
    private final CodeService codeService;

    @PostMapping("/fabric/save")
    @Transactional
    public ResponseEntity<ResponseDTO> updateFabricData(@RequestBody ReqFabricSaveDTO reqFabricSaveDTO) {
        return ResponseEntity.ok(counselingService.updateFabricData(reqFabricSaveDTO));
    }

    @PostMapping("/design/save")
    @Transactional
    public ResponseEntity<ResponseDTO> saveDesignData(@RequestBody ReqDesignSaveDTO reqDesignSaveDTO) {
        return ResponseEntity.ok(counselingService.updateDesignData(reqDesignSaveDTO));
    }

    @PostMapping("/size/save")
    @Transactional
    public ResponseEntity<ResponseDTO> saveSizeData(@RequestBody ReqSizeSaveDTO reqSizeSaveDTO) {
        return ResponseEntity.ok(counselingService.updateSizeData(reqSizeSaveDTO));
    }

    @GetMapping("/pattern/gauge")
    @Transactional
    public HashMap getGaugeSizeList(Long id) {
        HashMap map = new HashMap();
        map.put("list", codeService.getGaugeSizeList(id));
        return map;
    }

    @PostMapping("/complete")
    @Transactional
    public ResponseEntity<ResponseDTO> completeCounseling(Long reservationId) {
        return ResponseEntity.ok(counselingService.completeCounseling(reservationId));
    }
}
