package com.ripple.vmprovisioning.resource;

import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.service.VMProvisioningServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserResource {

    private final VMProvisioningServiceImpl vmProvisioningService;

    public UserResource(final VMProvisioningServiceImpl vmProvisioningService) {
        this.vmProvisioningService = vmProvisioningService;
    }
    @PostMapping
    @RequestMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User createUser(@RequestBody User user){
        return vmProvisioningService.signup(user);
    }

    public User updateUser(User user){
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@RequestBody User user){
        return vmProvisioningService.signIn(user);
    }


    @DeleteMapping
    @PreAuthorize("hasAuthority('Master')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user){
         vmProvisioningService.deleteUserAccount(user);
    }
}


