package com.gsww.service.impl;

import com.gsww.dao.SysServerLogDao;
import com.gsww.model.SysServerLog;
import com.gsww.service.SysServerLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;

/**
 * SysServerLogServiceImpl
 * com.gsww.service.impl
 *
 * @author xiaoyy
 *         serverLog实现类
 * @Date 2017-12-28 上午9:32
 * The word 'impossible' is not in my dictionary.
 */
@Service("sysServerLogServiceImpl")
public class SysServerLogServiceImpl implements SysServerLogService {
    Logger logger = Logger.getLogger(SysServerLogServiceImpl.class);

    @Autowired
    private SysServerLogDao sysServerLogDao;

    @Override
    @Transactional
    public void save(SysServerLog sysServerLog) {
        sysServerLogDao.save(sysServerLog);
    }


    /**
     * @Description 获取cpu使用率
     * @author Xander
     * @Date 2018/1/2 上午9:36
     * @see com.gsww.service.impl
     * The word 'impossible' is not in my dictionary.
     */
    @Override
    public float cpuUsage() {
        logger.info("开始收集cpu使用率");
        float cpuUsage = 0;
        Process pro1, pro2;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/stat";
            //第一次采集CPU时间
            long startTime = System.currentTimeMillis();
            pro1 = r.exec(command);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String line = null;
            long idleCpuTime1 = 0, totalCpuTime1 = 0;    //分别为系统启动后空闲的CPU时间和总的CPU时间
            while ((line = in1.readLine()) != null) {
                if (line.startsWith("cpu")) {
                    line = line.trim();
                    logger.info(line);
                    String[] temp = line.split("\\s+");
                    idleCpuTime1 = Long.parseLong(temp[4]);
                    for (String s : temp) {
                        if (!s.equals("cpu")) {
                            totalCpuTime1 += Long.parseLong(s);
                        }
                    }
                    logger.info("IdleCpuTime: " + idleCpuTime1 + ", " + "TotalCpuTime" + totalCpuTime1);
                    break;
                }
            }
            in1.close();
            pro1.destroy();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                logger.error("CpuUsage休眠时发生InterruptedException. " + e.getMessage());
                logger.error(sw.toString());
            }
            //第二次采集CPU时间
            long endTime = System.currentTimeMillis();
            pro2 = r.exec(command);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            long idleCpuTime2 = 0, totalCpuTime2 = 0;    //分别为系统启动后空闲的CPU时间和总的CPU时间
            while ((line = in2.readLine()) != null) {
                if (line.startsWith("cpu")) {
                    line = line.trim();
                    logger.info(line);
                    String[] temp = line.split("\\s+");
                    idleCpuTime2 = Long.parseLong(temp[4]);
                    for (String s : temp) {
                        if (!s.equals("cpu")) {
                            totalCpuTime2 += Long.parseLong(s);
                        }
                    }
                    logger.info("IdleCpuTime: " + idleCpuTime2 + ", " + "TotalCpuTime" + totalCpuTime2);
                    break;
                }
            }
            if (idleCpuTime1 != 0 && totalCpuTime1 != 0 && idleCpuTime2 != 0 && totalCpuTime2 != 0) {
                cpuUsage = 1 - (float) (idleCpuTime2 - idleCpuTime1) / (float) (totalCpuTime2 - totalCpuTime1);
                logger.info("本节点CPU使用率为: " + cpuUsage);
            }
            in2.close();
            pro2.destroy();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error("cpuUsage发生InstantiationException. " + e.getMessage());
            logger.error(sw.toString());
        }
        return cpuUsage;
    }

    /**
     * @Description 获取memory使用率
     * @author Xander
     * @Date 2018/1/2 上午9:36
     * @see com.gsww.service.impl
     * The word 'impossible' is not in my dictionary.
     */
    @Override
    public float memoryUsage() {
        logger.info("开始收集memory使用率");
        float memUsage = 0.0f;
        Process pro;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/meminfo";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            int count = 0;
            long totalMem = 0, freeMem = 0;
            while ((line = in.readLine()) != null) {
                logger.info(line);
                String[] memInfo = line.split("\\s+");
                if (memInfo[0].startsWith("MemTotal")) {
                    totalMem = Long.parseLong(memInfo[1]);
                }
                if (memInfo[0].startsWith("MemFree")) {
                    freeMem = Long.parseLong(memInfo[1]);
                }
                memUsage = 1 - (float) freeMem / (float) totalMem;
                logger.info("本节点内存使用率为: " + memUsage);
                if (++count == 2) {
                    break;
                }
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error("memoryUsage发生InstantiationException. " + e.getMessage());
            logger.error(sw.toString());
        }
        return memUsage;
    }


    /**
     * @Description 磁盘IO使用率
     * @author Xander
     * @Date 2018/1/2 上午10:09
     * @see com.gsww.service.impl
     * The word 'impossible' is not in my dictionary.
     */
    @Override
    public float diskIOUsage() {
        logger.info("开始收集磁盘IO使用率");
        float ioUsage = 0.0f;
        Process pro = null;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "iostat -d -x";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (++count >= 4) {
                    String[] temp = line.split("\\s+");
                    if (temp.length > 1) {
                        float util = Float.parseFloat(temp[temp.length - 1]);
                        ioUsage = (ioUsage > util) ? ioUsage : util;
                    }
                }
            }
            if (ioUsage > 0) {
                logger.info("本节点磁盘IO使用率为: " + ioUsage);
                ioUsage /= 100;
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error("diskIOUsage发生InstantiationException. " + e.getMessage());
            logger.error(sw.toString());
        }
        return ioUsage;
    }

    @Override
    public float netUsage() {
        float TotalBandwidth = 1000;    //网口带宽,Mbps
        logger.info("开始收集网络带宽使用率");
        float netUsage = 0.0f;
        Process pro1, pro2;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/net/dev";
            //第一次采集流量数据
            long startTime = System.currentTimeMillis();
            pro1 = r.exec(command);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String line = null;
            long inSize1 = 0, outSize1 = 0;
            while ((line = in1.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("eth0")) {
                    String[] temp = line.split("\\s+");
                    inSize1 = Long.parseLong(temp[0].substring(5));    //Receive bytes,单位为Byte
                    outSize1 = Long.parseLong(temp[8]);                //Transmit bytes,单位为Byte
                    break;
                }
            }
            in1.close();
            pro1.destroy();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                logger.error("NetUsage休眠时发生InterruptedException. " + e.getMessage());
                logger.error(sw.toString());
            }
            //第二次采集流量数据
            long endTime = System.currentTimeMillis();
            pro2 = r.exec(command);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            long inSize2 = 0, outSize2 = 0;
            while ((line = in2.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("eth0")) {
                    String[] temp = line.split("\\s+");
                    inSize2 = Long.parseLong(temp[0].substring(5));
                    outSize2 = Long.parseLong(temp[8]);
                    break;
                }
            }
            if (inSize1 != 0 && outSize1 != 0 && inSize2 != 0 && outSize2 != 0) {
                float interval = (float) (endTime - startTime) / 1000;
                //网口传输速度,单位为bps
                float curRate = (float) (inSize2 - inSize1 + outSize2 - outSize1) * 8 / (1000000 * interval);
                netUsage = curRate / TotalBandwidth;
                logger.info("本节点网口速度为: " + curRate + "Mbps");
                logger.info("本节点网络带宽使用率为: " + netUsage);
            }
            in2.close();
            pro2.destroy();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error("netUsage发生InstantiationException. " + e.getMessage());
            logger.error(sw.toString());
        }
        return netUsage;
    }

    /**
     * @Description Linux得到磁盘的使用率
     * @author Xander
     * @Date 2018/1/9 下午5:30
     * @see com.gsww.service.impl
     * The word 'impossible' is not in my dictionary.
     */
    @Override
    public double diskUsage() {
        double totalHD = 0;
        double usedHD = 0;
        // df -hl 查看硬盘空间
        String command = "df -hl";
        Runtime rt = Runtime.getRuntime();
        Process p;
        BufferedReader in = null;
        try {
            p = rt.exec(command);
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;
            while ((str = in.readLine()) != null) {
                int m = 0;
                strArray = str.split(" ");
                for (String tmp : strArray) {
                    if (tmp.trim().length() == 0)
                        continue;
                    ++m;
                    if (tmp.indexOf("G") != -1) {
                        if (m == 2) {
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1)) * 1024;
                        }
                        if (m == 3) {
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1)) * 1024;
                        }
                    }
                    if (tmp.indexOf("M") != -1) {
                        if (m == 2) {
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1));
                        }
                        if (m == 3) {
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1));
                        }
                    }
                }
            }
            in.close();
        } catch (Exception e) {
            logger.debug(e);
        }
        // 保留2位小数
        double precent = (usedHD / totalHD);
        BigDecimal b1 = new BigDecimal(precent);
        precent = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return precent;
    }
}
