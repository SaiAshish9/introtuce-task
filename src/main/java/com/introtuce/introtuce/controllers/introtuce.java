package com.introtuce.introtuce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/")
public class introtuce {

    @GetMapping
    public ResponseEntity<?> hello(){

        Map<String,String> msg= new HashMap<>();
        msg.put("msg","introtuce");
        return ResponseEntity.ok(msg);

    }

}
