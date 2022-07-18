package com.ripple.vmprovisioning.service;

import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;
import com.ripple.vmprovisioning.service.exceptions.DuplicateUserException;
import com.ripple.vmprovisioning.service.exceptions.VMBadRequestException;
import com.ripple.vmprovisioning.service.exceptions.VMException;

import java.util.Collection;
import java.util.Optional;

public interface VMProvisioningService {
    User signup(User user) throws DuplicateUserException;
    User signIn(User user) throws DuplicateUserException;
    VirtualMachine requestVirtualMachine(VirtualMachine virtualMachine);
    Collection<VirtualMachine> getVirtualMachines(Optional<User> user, Optional<Integer> limit) throws VMException, VMBadRequestException;
    void deleteUserAccount(User user);
}
