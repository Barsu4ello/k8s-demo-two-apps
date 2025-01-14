package com.example.k8sdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping(value = "host")
    public Map<String, String> getHost() throws UnknownHostException {
        Map<String, String> map = new HashMap<>();
        String hostName = InetAddress.getLocalHost().getHostName();
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String canonicalHostName = InetAddress.getLocalHost().getCanonicalHostName();
        map.put("version", "0.2");
        map.put("hostName", hostName);
        map.put("hostAddress", hostAddress);
        map.put("canonicalHostName", canonicalHostName);
        return map;
    }

    @GetMapping(value = "nginx")
    public String getNginx() throws UnknownHostException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://nginx", String.class);
        return response.toString();
    }
}
