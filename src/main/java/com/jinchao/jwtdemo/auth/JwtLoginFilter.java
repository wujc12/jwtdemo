package com.jinchao.jwtdemo.auth;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinchao.jwtdemo.domain.Response;
import com.jinchao.jwtdemo.domain.User;
import com.jinchao.jwtdemo.utils.ResponseUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            // 从 request 头里面读取 user 信息，并进行验证
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        HashMap<String, String> result = new HashMap<>();
        System.out.println("This is successfulAuth");
        ZoneId zoneId = ZoneId.systemDefault();
        Date issuedDate = new Date();
        ZonedDateTime zonedDateTime = LocalDateTime.now().plusMinutes(30).atZone(zoneId);
        Date expireDate = Date.from(zonedDateTime.toInstant());

        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, "jwtSecurity")
                .compact();

        result.put("Authorization", "Bearer " + token);
        ResponseUtil.write(response, JSON.toJSON(Response.success(result)));
    }
}
