package kr.co.apfactory.storesolution.application.controller.rest;

import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqPasswordUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RequiredArgsConstructor
@RestController
public class CommonRestController {
    private final Environment environment;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @PostMapping("/password/update")
    @Transactional
    public ResponseEntity<ResponseDTO> updatePassword(@RequestBody ReqPasswordUpdateDTO reqPasswordUpdateDTO) {
        return ResponseEntity.ok(userService.updatePassword(reqPasswordUpdateDTO));
    }
}
