package com.ripple.vmprovisioning.mappers;

import com.ripple.vmprovisioning.integration.db.entity.VMEntity;
import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;
import org.springframework.stereotype.Component;

@Component
public class VMToVMEntityMapper implements VMMapper<VirtualMachine, User, VMEntity> {

    @Override
    public VMEntity map(VirtualMachine virtualMachine, User user) {
        if(virtualMachine == null) {
            return null;
        }
        VMEntity vmEntity = new VMEntity();
        vmEntity.setCpuCores(String.valueOf(virtualMachine.getCpuCores()));
        vmEntity.setHardDisk(virtualMachine.getHd());
        vmEntity.setOs(virtualMachine.getOs());
        vmEntity.setRam(virtualMachine.getRam());
        if(user != null){
            vmEntity.setUserId(String.valueOf(user.getUserId()));
        }
        return vmEntity;
    }
}
