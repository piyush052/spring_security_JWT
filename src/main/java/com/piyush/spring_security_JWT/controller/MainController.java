package com.piyush.spring_security_JWT.controller;


import com.piyush.spring_security_JWT.models.AuthRequest;
import com.piyush.spring_security_JWT.models.AuthResponse;
import com.piyush.spring_security_JWT.service.MyUserDetailsService;
import com.piyush.spring_security_JWT.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @GetMapping("/hello")
    String hello(){
        return "<h1> HELLO </h1>";
    }

    @PostMapping("/authenticate")
    ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("username or password is incorrect");
        }
        return ResponseEntity.ok(new AuthResponse( jwtTokenUtil.generateToken( myUserDetailsService.loadUserByUsername(authRequest.getUserName()))));
    }
}
