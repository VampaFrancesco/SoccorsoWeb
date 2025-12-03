package it.univaq.swa.soccorsoweb.controller;

import it.univaq.swa.soccorsoweb.model.dto.request.LoginRequest;
import it.univaq.swa.soccorsoweb.model.dto.response.UserResponse;
import it.univaq.swa.soccorsoweb.service.AuthControllerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthControllerService authControllerService;

    public AuthController(AuthControllerService authControllerService) {
        this.authControllerService = authControllerService;

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest){
           return ResponseEntity.ok(authControllerService.login(loginRequest));
    }
//
//    @PostMapping("/logut")
//    public ResponseEntity<UserResponse> logout(){
//        return ResponseEntity.ok(authControllerService.logout());
//    }




}
