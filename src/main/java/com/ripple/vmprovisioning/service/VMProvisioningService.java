package com.ripple.vmprovisioning.service;

import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;

import java.util.Collection;
import java.util.Optional;

public interface VMProvisioningService {
    User signup(User user);
    User signIn(User user);
    VirtualMachine requestVirtualMachine(VirtualMachine virtualMachine);
    Collection<VirtualMachine> getVirtualMachines(Optional<User> user, Optional<Integer> limit);
    User deleteUserAccount(User user);
}
