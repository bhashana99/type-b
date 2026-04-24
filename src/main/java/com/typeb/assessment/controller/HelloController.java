package com.typeb.assessment.controller;

import com.typeb.assessment.dto.ErrorResponseDTO;
import com.typeb.assessment.service.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public ResponseEntity<Object> hello(@RequestParam(required = false) String name) {
        if (!helloService.isValidName(name)) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Input"));
        }
        return ResponseEntity.ok(helloService.greet(name));
    }
}
