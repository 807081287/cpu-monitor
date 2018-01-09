package com.gsww.controller;

import com.gsww.model.SysServerLog;
import com.gsww.service.SysServerLogService;
import com.gsww.utils.ComputerInfo;
import com.gsww.utils.Dbid;
import com.gsww.utils.TimeHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DecimalFormat;

/**
 * CpuController
 * com.gsww.controller
 *
 * @author xiaoyy
 *         Cpu监控
 * @Date 2017-12-27 下午5:17
 * The word 'impossible' is not in my dictionary.
 */
@Component
public class CpuController {

    Logger logger = Logger.getLogger(CpuController.class);

    @Autowired
    private SysServerLogService sysServerLogService;

    @Scheduled(cron = "0 0/2 8-20 * * ?")
    public void executeFileDownLoadTask() {

        // 间隔2分钟,执行工单上传任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务1:" + current.getId());
        logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
    }

    @Scheduled(cron = "0 0/2 8-20 * * ?")
    public void saveSysServerLog() {
        // 间隔2分钟,执行工单上传任务
        SysServerLog sysServerLog = new SysServerLog();
        float cpuUsage = sysServerLogService.cpuUsage();
        // 构造方法的字符格式这里如果小数不足,会以0补足
        DecimalFormat decimalFormat = new DecimalFormat("##0.000000");

        float memoryUsage = sysServerLogService.memoryUsage();
        float diskIoUsage = sysServerLogService.diskIOUsage();
        double diskUsage = sysServerLogService.diskUsage();
        float netUsage = sysServerLogService.netUsage();
        try {
            sysServerLog.setSysServerLogId(Dbid.getID());
            sysServerLog.setServerIp(ComputerInfo.getLinuxLocalIp());
            sysServerLog.setCpu(String.valueOf(decimalFormat.format(cpuUsage)));
            sysServerLog.setMemory(String.valueOf(decimalFormat.format(memoryUsage)));
            sysServerLog.setDisk(String.valueOf(decimalFormat.format(diskUsage)));
            sysServerLog.setDiskIo(String.valueOf(decimalFormat.format(diskIoUsage)));
            sysServerLog.setNet(String.valueOf(decimalFormat.format(netUsage)));
            sysServerLog.setCreateTime(TimeHelper.getCurrentTime());
            sysServerLogService.save(sysServerLog);
            logger.info("存储成功,id:" + sysServerLog.getSysServerLogId());
        } catch (Exception e) {
            logger.error("存储失败");
        }

    }

}
