package com.omi.TaskApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/hello")
    public ResponseEntity<String> healthCheck(){
        String check = "this is working fine";
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

}
