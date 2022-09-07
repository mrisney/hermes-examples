package com.vmware.eventhub.javasample.controller;

import com.vmware.eventhub.javasample.service.RestCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SampleController {

    @Autowired
    private RestCall restCall;

    @PostMapping(value = "/call",consumes={"application/json"})
    public String call(@RequestBody String payload){
        ResponseEntity<Map> responseEntity= restCall.callPublisher(payload);
        Map<String,Map<String,String>> map=responseEntity.getBody();
        StringBuilder sb=new StringBuilder();
        sb.append("\n");
        for (String s:map.get("messageStatus").keySet()) {
            sb.append("  -  ");
            sb.append(s);
            sb.append(":");
            sb.append(map.get("messageStatus").get(s));
        }
        return responseEntity.getStatusCode().toString()+sb.toString();
    }

}
