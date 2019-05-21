package com.jinchao.jwtdemo.auth;

import com.alibaba.fastjson.JSON;
import com.jinchao.jwtdemo.domain.Response;
import com.jinchao.jwtdemo.enums.ResultStatusEnum;
import com.jinchao.jwtdemo.utils.ResponseUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthFilter extends BasicAuthenticationFilter {
    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken authenticationToken = null;

        try {
            authenticationToken = getAuthentication(header);
        } catch (Exception e) {
            ResponseUtil.write(response, JSON.toJSON(Response.failed(ResultStatusEnum.ACCESS_DENIED, "Token Check Error")));
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("jwtSecurity")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();
            System.out.println(user);
            // token验证成功
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

            return null;
        }
        return null;
    }
}
