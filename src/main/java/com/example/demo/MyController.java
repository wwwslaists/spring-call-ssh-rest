package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/external-data-xml")
    public String getExternalData() {

        return myService.callExternalApi();
    }

    @GetMapping("/external-data-json")
    public String getExternalDataJson(){

        return myService.callExternalApiJson();
    }
}
