package com.hod.project.hive.controller;

import com.hod.project.hive.common.vo.RequestParams;
import com.hod.project.hive.common.vo.ResponseParams;
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

    @PostMapping("/test/param")
    public ResponseEntity<ResponseParams> methodPostWithMapParams(
            @RequestParam String id,
            @RequestParam(value = "data", required = false, defaultValue = "DEFAULT VALUE") String data
    ) {

        ResponseParams params = new ResponseParams();
        params.setResult("200");
        params.setReason("id: " + id + ", data: " + data);
        return ResponseEntity.ok(params);
    }

    @PostMapping("/test/object")
    public ResponseEntity<ResponseParams> methodPostWithObjectParams(
            @RequestBody RequestParams requestParams
    ) {
        ResponseParams params = new ResponseParams(ResponseParams.Status.CODE_200);
        params.setReason(requestParams.toString());
        return ResponseEntity.ok(params);
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
