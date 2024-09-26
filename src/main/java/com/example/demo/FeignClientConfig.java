package com.example.demo;


import feign.Client;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

@Configuration
public class FeignClientConfig {

    @Bean
    public Client feignClient() throws Exception {
        return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
    }

    private SSLSocketFactory getSSLSocketFactory() throws Exception {
        String password = "allpassword";
        String keystorePath = "/Users/edgars/IdeaProjects/CallSSL/keystore.jks";

        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (FileInputStream keyStoreInputStream = new FileInputStream(keystorePath)) {
            keyStore.load(keyStoreInputStream, password.toCharArray());
        }

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password.toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        return sslContext.getSocketFactory();
    }
}
