package com.ripple.vmprovisioning.resource;

import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;
import com.ripple.vmprovisioning.service.VMProvisioningService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/virtualMachine")
public class VMResource {

    private final VMProvisioningService vmProvisioningService;

    public VMResource(VMProvisioningService vmProvisioningService) {
        this.vmProvisioningService = vmProvisioningService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VirtualMachine createVirtualMachine(@RequestBody VirtualMachine virtualMachine){
        return vmProvisioningService.requestVirtualMachine(virtualMachine);
    }

    @GetMapping
    @RequestMapping("/{targetUserName}")
    @PreAuthorize("T(com.ripple.vmprovisioning.security.NameCheckUtil).isTargetNameSameAsLoggedInUser(#loggedInUserName, #targetUserName) or hasAuthority('Master')")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VirtualMachine> getVirtualMachineDetailsByUser(@PathVariable String targetUserName, @RequestHeader("name") String loggedInUserName){
        User user = new User();
        user.setName(targetUserName);
        return vmProvisioningService.getVirtualMachines(Optional.of(user), null);
    }

    @GetMapping
    @RequestMapping("/all/{limit}")
    @PreAuthorize("hasAuthority('Master')")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VirtualMachine> getVirtualMachineDetailsByMemory(@PathVariable Integer limit){
        return vmProvisioningService.getVirtualMachines(null, Optional.of(limit));
    }


    @GetMapping
    @RequestMapping("/loggedInUser/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VirtualMachine> getVirtualMachineDetailsByLoggedInUser(@RequestHeader("name") String name, @PathVariable Integer limit){

        User user = new User();
        user.setName(name);
        return vmProvisioningService.getVirtualMachines(Optional.of(user), Optional.of(limit));
    }
}
