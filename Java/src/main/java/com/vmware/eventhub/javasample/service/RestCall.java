package com.vmware.eventhub.javasample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RestCall {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;

    @Value("${publisher.url}")
    private String url;

    private OAuth2AccessToken token;

    private static final Logger LOG = LoggerFactory.getLogger(RestCall.class);

    public ResponseEntity<Map> callPublisher(String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        getOauthToken();
        headers.setBearerAuth(token.getValue());

        ResponseEntity<Map> response=null;
        HttpEntity<Object> entity = new HttpEntity<>(json, headers);
        try{
            response=restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
            LOG.info("Payload was sent");
        }catch(Exception e){
            LOG.error("Cannot send payload. "+e.getMessage());
        }
        return response;
    }

    private void getOauthToken(){
        try{
            if(token==null || token.getExpiresIn()<30){
                token =oAuth2RestTemplate.getAccessToken();
                //If token will expire less than 30 seconds
            }
        }catch (RuntimeException e){
            LOG.error("Could not get OAuth token. "+e.getMessage());
        }
        if(token!=null){
            LOG.info("Token was received");
        }
    }
}
