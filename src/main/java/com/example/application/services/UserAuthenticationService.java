package com.example.application.services;

import com.example.application.entities.User;
import com.example.application.entities.UserInfo;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.security.AuthenticationContext;
import dev.hilla.BrowserCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@BrowserCallable
@AnonymousAllowed
public class UserAuthenticationService {

    @Autowired
    private AuthenticationContext authenticationContext;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${keycloak.userinfo-uri}")
    private String userinfoUri;

    public Optional<User> getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication instanceof BearerTokenAuthenticationToken bearerTokenAuthenticationToken) {
            Optional<UserInfo> userInfo = getUserInfo(bearerTokenAuthenticationToken.getToken());
            if (userInfo.isPresent()) {
                return Optional.of(new User(userInfo.get()));
            }
        }
        return Optional.empty();
    }

    private Optional<UserInfo> getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<UserInfo> userInfoResponse = restTemplate.exchange(userinfoUri, HttpMethod.GET, requestEntity, UserInfo.class);
        UserInfo userInfo = userInfoResponse.getBody();
        if (userInfo != null) {
            return Optional.of(userInfo);
        } else {
            return Optional.empty();
        }
    }
}
