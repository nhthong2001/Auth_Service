package com.example.auth_service.controller;

import com.example.auth_service.model.AuthRequestModel;
import com.example.auth_service.service.JwtService;
import com.example.auth_service.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("info")
    @ResponseBody
    public String getInfo(){
        return "This is your info...";
    }
    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "Hello Hoang Thong";
    }

   @PostMapping("/authenticate")
    public String getJwtAuthToken(@RequestBody AuthRequestModel authRequestModel) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestModel.getUserName(), authRequestModel.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or password...", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestModel.getUserName());
        final String jwt = jwtService.getJWT(userDetails);
        return jwt;
    }
}
