package com.informatics.e_school_journal.controller;

import com.informatics.e_school_journal.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableReactiveMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/hi")
    public UserInfoDto  sayHi(JwtAuthenticationToken auth) {
        return new UserInfoDto(
                auth.getToken().getSubject(),
                auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
}
