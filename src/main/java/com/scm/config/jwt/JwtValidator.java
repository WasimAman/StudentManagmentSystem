package com.scm.config.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter{

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(JwtConstant.HEADER);
        if(token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
            try{
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

                String studentId = claims.get("studentId",String.class);
                String role = claims.get("role",String.class);

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                    studentId,
                    null,
                    Collections.singletonList(authority)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(Exception e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid or expired token");
            }
        }

        filterChain.doFilter(request, response);
    }

    
}