package com.gsww.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * SysServerLog
 * com.gsww.jup.entity
 *
 * @author xiaoyy
 * @Date 2017-12-28 上午9:27
 * The word 'impossible' is not in my dictionary.
 */
@Entity
@Table(name = "SYS_SERVER_LOG", schema = "SWPORTAL", catalog = "")
public class SysServerLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sysServerLogId;
    private String serverIp;
    private String cpu;
    private String memory;
    private String disk;
    private String diskIo;
    private String net;
    private String createTime;

    @Id
    @Column(name = "SYS_SERVER_LOG_ID")
    public String getSysServerLogId() {
        return sysServerLogId;
    }

    public SysServerLog setSysServerLogId(String sysServerLogId) {
        this.sysServerLogId = sysServerLogId;
        return this;
    }

    @Column(name = "SERVER_IP")
    public String getServerIp() {
        return serverIp;
    }

    public SysServerLog setServerIp(String serverIp) {
        this.serverIp = serverIp;
        return this;
    }

    @Column(name = "CPU")
    public String getCpu() {
        return cpu;
    }

    public SysServerLog setCpu(String cpu) {
        this.cpu = cpu;
        return this;
    }

    @Column(name = "MEMORY")
    public String getMemory() {
        return memory;
    }

    public SysServerLog setMemory(String memory) {
        this.memory = memory;
        return this;
    }

    @Column(name = "DISK")
    public String getDisk() {
        return disk;
    }

    public SysServerLog setDisk(String disk) {
        this.disk = disk;
        return this;
    }

    @Column(name = "NET")
    public String getNet() {
        return net;
    }

    public SysServerLog setNet(String net) {
        this.net = net;
        return this;
    }

    @Column(name = "CREATE_TIME")
    public String getCreateTime() {
        return createTime;
    }

    public SysServerLog setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    @Column(name = "DISK_IO")
    public String getDiskIo() {
        return diskIo;
    }
    public SysServerLog setDiskIo(String diskIo) {
        this.diskIo = diskIo;
        return this;
    }
}
