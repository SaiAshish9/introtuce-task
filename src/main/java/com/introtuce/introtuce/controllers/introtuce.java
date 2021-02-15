package com.introtuce.introtuce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController("/")
public class introtuce {

    @GetMapping("/introtuce")
    public ResponseEntity<?> hello(){

        Map<String,String> msg= new HashMap<>();
        msg.put("msg","hi");
        return ResponseEntity.ok(msg);
    }

    @GetMapping
    public ModelAndView home(ModelMap modelMap){
        return new ModelAndView("redirect:swagger-ui.html",modelMap);
    }

}
