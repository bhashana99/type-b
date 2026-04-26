package com.typeb.assessment.controller;

import com.typeb.assessment.dto.ErrorResponseDTO;
import com.typeb.assessment.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
@Tag(name = "Hello World", description = "Greeting endpoint")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    @Operation(
            summary = "Greet a user by name",
            description = "Returns a greeting if the name starts with A–M. Returns 400 for N–Z, missing, or empty names."
    )
    public ResponseEntity<Object> hello(@RequestParam(required = false) String name) {
        if (!helloService.isValidName(name)) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Input"));
        }
        return ResponseEntity.ok(helloService.greet(name));
    }
}
