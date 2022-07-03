package com.ripple.vmprovisioning.resource;

import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;
import com.ripple.vmprovisioning.service.VMProvisioningService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    @ResponseStatus(HttpStatus.OK)
    public Collection<VirtualMachine> getVirtualMachineDetailsByUser(@RequestBody User user){
        return vmProvisioningService.getVirtualMachines(Optional.of(user), null);
    }

    @GetMapping
    @RequestMapping("/memory/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VirtualMachine> getVirtualMachineDetailsByMemory(@PathVariable Integer limit){
        return vmProvisioningService.getVirtualMachines(null, Optional.of(limit));
    }

}
