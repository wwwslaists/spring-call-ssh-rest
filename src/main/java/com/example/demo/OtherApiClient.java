package com.example.demo;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "otherApiClient", url = "https://localhost:8443/api/v1/", configuration = FeignClientConfig.class   )
public interface OtherApiClient {

    @GetMapping("/xml")
    String getSomeData();

    @GetMapping("/json")
    String getSomeDataJson();
}
