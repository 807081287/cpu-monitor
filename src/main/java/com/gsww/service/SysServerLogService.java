package com.gsww.service;

import com.gsww.model.SysServerLog;

public interface SysServerLogService {

	void save(SysServerLog sysServerLog);

	float cpuUsage();

	float memoryUsage();

	float diskIOUsage();

	float netUsage();

}
