package com.acalderon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy")
public class Proxy {
    RestTemplate template = new RestTemplate();

    @PostMapping()
    ResponseEntity<String> addUser(@RequestBody String message) {
        StringBuilder builder = new StringBuilder();
        builder.append("I proxied the message, the original message is ");
        builder.append(message);
        return template.getForEntity("http://talker:8080/api/talker?message=" + builder.toString(), String.class);
    }


}
