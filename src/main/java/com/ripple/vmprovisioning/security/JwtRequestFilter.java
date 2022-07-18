package com.ripple.vmprovisioning.security;

import com.ripple.vmprovisioning.security.jwt.VMProvisioningJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private VMProvisioningUserDetailsService vMProvisioningUserDetailsService;

    @Autowired
    private VMProvisioningJWTService vmProvisioningJWTService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt=null;
        String username = null;
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = vmProvisioningJWTService.extractUsername(jwt);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.vMProvisioningUserDetailsService.loadUserByUsername(username);
            if(vmProvisioningJWTService.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
//    {
//        String path = request.getRequestURI();
//        return !"/v1/user".equals(path);
//    }
}
