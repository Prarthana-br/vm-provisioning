package com.ripple.vmprovisioning.resource;

import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.service.VMProvisioningServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserResource {

    private final VMProvisioningServiceImpl vmProvisioningService;

    public UserResource(final VMProvisioningServiceImpl vmProvisioningService) {
        this.vmProvisioningService = vmProvisioningService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User createUser(@RequestBody User user){
        return vmProvisioningService.signup(user);
    }

    public User updateUser(User user){
        return null;
    }

    public User deleteUser(User user){
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@RequestBody User user){
        return vmProvisioningService.signIn(user);
    }
}


