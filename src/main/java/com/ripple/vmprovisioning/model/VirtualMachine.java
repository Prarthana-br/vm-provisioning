package com.ripple.vmprovisioning.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@NonNull
public class VirtualMachine {
    private Long virtualMachineId;
    private String os;
    private String ram;
    private Integer hd;
    private Integer cpuCores;
    private String userName;

    public static void main(String[] args) throws JsonProcessingException {
        VirtualMachine virtualMachine = new VirtualMachine();
        virtualMachine.setHd(1024);
        virtualMachine.setOs("MacOS");
        virtualMachine.setCpuCores(8);
        virtualMachine.setRam("16GB");
        virtualMachine.setUserName("Prarthana");
        System.out.println(
                new ObjectMapper().writeValueAsString(virtualMachine));
    }
}
