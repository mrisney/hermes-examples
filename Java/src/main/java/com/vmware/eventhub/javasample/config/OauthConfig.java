package com.vmware.eventhub.javasample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OauthConfig {

    @Value("${oauth.url}")
    private String url;

    @Value("${oauth.username}")
    private String username;

    @Value("${oauth.password}")
    private String password;

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId(username);
        details.setClientSecret(password);
        details.setAccessTokenUri(url);
        return details;
    }

    @Bean
    public OAuth2RestTemplate createOauthRestTemplate() {
        OAuth2ClientContext context = new DefaultOAuth2ClientContext();
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), context);
    }
}
