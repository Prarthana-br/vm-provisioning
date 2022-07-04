package com.ripple.vmprovisioning.security;

import com.ripple.vmprovisioning.security.jwt.VMProvisioningJWTService;
import com.ripple.vmprovisioning.security.model.AuthRequest;
import com.ripple.vmprovisioning.security.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VMProvisioningUserDetailsService vmProvisioningUserDetailsService;

    @Autowired
    private VMProvisioningJWTService vmProvisioningJWTService;

    @PostMapping("/authenticate")
    public @ResponseBody AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception{
        UserDetails userDetails = null;
        try {
          Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
          userDetails = (UserDetails) authentication.getPrincipal();
        }catch (BadCredentialsException badCredentialsException){
            throw new Exception("Incorrect username and password", badCredentialsException);
        }
        return new AuthResponse(vmProvisioningJWTService.generateToken(userDetails), userDetails.getUsername());
    }
}
