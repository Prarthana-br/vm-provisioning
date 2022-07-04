package com.ripple.vmprovisioning.resource;

import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;
import com.ripple.vmprovisioning.service.VMProvisioningService;
import org.springframework.http.HttpStatus;
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
    @RequestMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VirtualMachine> getVirtualMachineDetailsByUser(@PathVariable String name){
        User user = new User();
        user.setName(name);
        return vmProvisioningService.getVirtualMachines(Optional.of(user), null);
    }

    @GetMapping
    @RequestMapping("/memory/{limit}")
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
