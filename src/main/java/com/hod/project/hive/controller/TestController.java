package com.hod.project.hive.controller;

import com.hod.project.hive.common.vo.RequestParams;
import com.hod.project.hive.common.vo.ResponseParams;
import com.hod.project.hive.entity.TestObject;
import com.hod.project.hive.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;


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
    public ResponseEntity<Map<String, String>> methodGet() {
        String id = testService.getSomething();
        Map<String, String> map = new HashMap<>();
        map.put("id", id);

        return ResponseEntity.ok(map);
    }

    @GetMapping("/test/id")
    public ResponseEntity<String> methodGetById() {
        String id = testService.getSome(1234);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/test/map")
    public ResponseEntity<Map<String, Object>> methodGetTypeMap() {
        return ResponseEntity.ok(testService.getObjectTypeMap());
    }

    @GetMapping("/test/object")
    public ResponseEntity<TestObject> methodGetObject() {
        return ResponseEntity.ok(testService.getObject());
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
