package com.hod.project.hive.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> methodPost() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/test")
    public ResponseEntity<String> methodGet() {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/test")
    public ResponseEntity<String> methodPut() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/test")
    public ResponseEntity<String> methodDelete() {
        return ResponseEntity.ok(null);
    }

}
