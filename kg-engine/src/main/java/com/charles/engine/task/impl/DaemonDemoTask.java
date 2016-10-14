package com.charles.engine.task.impl;

import com.charles.engine.task.AbstractDaemonTask;

public class DaemonDemoTask extends AbstractDaemonTask {

	@Override
	public void runTask() {
		daemonLogger.info("执行业务逻辑");
		return;
	}

	@Override
	public String getTaskDesc() {
		return "测试TaskDemo";
	}

}
