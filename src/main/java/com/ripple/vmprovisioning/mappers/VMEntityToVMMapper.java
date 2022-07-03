package com.ripple.vmprovisioning.mappers;

import com.ripple.vmprovisioning.integration.db.entity.VMEntity;
import com.ripple.vmprovisioning.model.User;
import com.ripple.vmprovisioning.model.VirtualMachine;
import org.springframework.stereotype.Component;

@Component
public class VMEntityToVMMapper implements VMMapper<VMEntity, User, VirtualMachine>{

    public VirtualMachine map(VMEntity vmEntity, User user) {
        if(vmEntity == null){
            return null;
        }
        VirtualMachine virtualMachine = new VirtualMachine();
        virtualMachine.setCpuCores(Integer.valueOf(vmEntity.getCpuCores()));
        virtualMachine.setHd(vmEntity.getHardDisk());
        virtualMachine.setOs(vmEntity.getOs());
        virtualMachine.setRam(vmEntity.getRam());
        virtualMachine.setVirtualMachineId(vmEntity.getVmId());
        return virtualMachine;
    }
}
