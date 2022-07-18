package com.ripple.vmprovisioning.service;

import com.ripple.vmprovisioning.integration.db.entity.UserEntity;
import com.ripple.vmprovisioning.integration.db.entity.VMEntity;
import com.ripple.vmprovisioning.integration.db.repository.UserEntityRepository;
import com.ripple.vmprovisioning.integration.db.repository.VMEntityRepository;
import com.ripple.vmprovisioning.mappers.UserEntityToUserMapper;
import com.ripple.vmprovisioning.mappers.UserToUserEntityMapper;
import com.ripple.vmprovisioning.mappers.VMEntityToVMMapper;
import com.ripple.vmprovisioning.mappers.VMToVMEntityMapper;
import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;
import com.ripple.vmprovisioning.service.exceptions.DuplicateUserException;
import com.ripple.vmprovisioning.service.exceptions.VMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VMProvisioningServiceImpl implements VMProvisioningService {

    private final UserEntityRepository userEntityRepository;
    private final UserToUserEntityMapper userToUserEntityMapper;
    private final UserEntityToUserMapper userEntityToUserMapper;

    @Autowired
    private VMEntityToVMMapper vmEntityToVMMapper;

    @Autowired
    private VMToVMEntityMapper vmToVMEntityMapper;

    @Autowired
    private VMEntityRepository vmEntityRepository;

    public VMProvisioningServiceImpl(final UserEntityRepository userEntityRepository, final UserToUserEntityMapper userToUserEntityMapper, UserEntityToUserMapper userEntityToUserMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userToUserEntityMapper = userToUserEntityMapper;
        this.userEntityToUserMapper = userEntityToUserMapper;
    }

    @Override
    public User signup(User user) {
        User existingUser = signIn(user);
        if (existingUser == null) {
            return userEntityToUserMapper.map(userEntityRepository.save(userToUserEntityMapper.map(user)));
        }
        throw new DuplicateUserException("User already exists");
    }

    @Override
    public User signIn(User user) {
        User userDetails = userEntityToUserMapper.map(userEntityRepository.findByName(user.getName()));
        if(userDetails != null){
            return userDetails;
        }
//        throw new DuplicateUserException("User details not found");
        return null;
    }

    @Override
    public VirtualMachine requestVirtualMachine(VirtualMachine virtualMachine) {
        User userDetails = userEntityToUserMapper.map(userEntityRepository.findByName(virtualMachine.getUserName()));
        return vmEntityToVMMapper.map(vmEntityRepository.save(vmToVMEntityMapper.map(virtualMachine, userDetails)), userDetails);
    }

    @Override
    public Collection<VirtualMachine> getVirtualMachines(Optional<User> user, Optional<Integer> limit) {

        if(user != null && user.isPresent() && limit != null && limit.isPresent()){
            Collection<VirtualMachine> virtualMachines = null;
            UserEntity userDetails = userEntityRepository.findByName(user.get().getName());
            if (userDetails != null) {
                Page<VMEntity>  vmEntitiesPage =  vmEntityRepository.findAllByUserId(Math.toIntExact(userDetails.getUserId()), PageRequest.of(0, limit.get(), Sort.by("hardDisk").descending()));
                virtualMachines = getVirtualMachines(virtualMachines, userDetails, vmEntitiesPage);
                return virtualMachines;
            }

        } else if (user != null && user.isPresent()) {
            //Query By User
            Collection<VirtualMachine> virtualMachines = null;
            UserEntity userDetails = userEntityRepository.findByName(user.get().getName());
            if (userDetails != null) {
                Collection<VMEntity> vmEntities = vmEntityRepository.findByUserId(Math.toIntExact(userDetails.getUserId()));
                if (!CollectionUtils.isEmpty(vmEntities)) {
                    virtualMachines = new ArrayList<>();
                    for (VMEntity vmEntity : vmEntities) {
                        virtualMachines.add(vmEntityToVMMapper.map(vmEntity, null));

                    }
                    return virtualMachines;
                }
            }

        } else if (limit != null && limit.isPresent()) {
            Collection<VirtualMachine> virtualMachines = null;
            Page<VMEntity>  vmEntitiesPage =  vmEntityRepository.findAll(PageRequest.of(0, limit.get(), Sort.by("hardDisk").descending()));
            virtualMachines = getVirtualMachines(virtualMachines, null, vmEntitiesPage);
            return virtualMachines;
        }
        throw new VMException("VM details not found for the given criteria");
    }

    private Collection<VirtualMachine> getVirtualMachines(Collection<VirtualMachine> virtualMachines, UserEntity userDetails, Page<VMEntity> vmEntitiesPage) {
        if(vmEntitiesPage != null && !vmEntitiesPage.isEmpty()){
            virtualMachines = new ArrayList<>();
            List<VMEntity> vmEntityList = vmEntitiesPage.getContent();
            for (VMEntity vmEntity : vmEntityList) {
                virtualMachines.add(vmEntityToVMMapper.map(vmEntity, userEntityToUserMapper.map(userDetails)));
            }
            return virtualMachines;
        }
        throw new VMException("VM details not found for the given criteria");
    }

    @Override
    public void deleteUserAccount(User user) {
        vmEntityRepository.deleteByUserId(Integer.parseInt(""+user.getUserId()));
        userEntityRepository.deleteById(user.getUserId());
    }
}
