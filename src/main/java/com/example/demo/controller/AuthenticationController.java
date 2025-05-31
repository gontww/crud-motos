//package com.example.demo.controller;
//
//import com.example.demo.dto.AuthenticationDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("auth")
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("login")
//    public ResponseEntity<String> login(@RequestBody AuthenticationDTO data){
//        var senhaUsuario = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
//        var auth = this.authenticationManager.authenticate(senhaUsuario);
//
//        return ResponseEntity.ok().build();
//    }
//}
