package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final OtherApiClient otherApiClient;

    @Autowired
    public MyService(OtherApiClient otherApiClient) {
        this.otherApiClient = otherApiClient;
    }

    public String callExternalApi() {

        return otherApiClient.getSomeData();
    }

    public String callExternalApiJson() {

        return otherApiClient.getSomeDataJson();
    }
}
