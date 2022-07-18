package com.ripple.vmprovisioning.integration.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "vm", schema = "vmprovisioning")
public class VMEntity {

    @Id
    @Column(name = "vm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vmId;
    @Column(name = "os")
    private String os;
    @Column(name = "ram")
    private String ram;
    @Column(name = "hd")
    private Integer hardDisk;
    @Column(name = "cpu_cores")
    private String cpuCores;
    @Column(name = "user_id")
    private Integer userId;

//    @ManyToOne
//    private UserEntity user;

    public Long getVmId() {
        return vmId;
    }

    public void setVmId(Long vmId) {
        this.vmId = vmId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Integer getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(Integer hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(String cpuCores) {
        this.cpuCores = cpuCores;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
