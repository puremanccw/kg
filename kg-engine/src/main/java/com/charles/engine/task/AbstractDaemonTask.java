package com.charles.engine.task;


public abstract class AbstractDaemonTask implements DaemonTask {

	private String taskDesc;
	
	private String daemonStop;
	
    public void execute() {
		daemonLogger.info("BEGIN------" + getTaskDesc());
		runTask();
		daemonLogger.info("FINISH------" + getTaskDesc());
	}
    
    /**
     * 具体执行后台任务函数，重载
     */
    public abstract void runTask();
    
    public abstract String getTaskDesc();

	public String getDaemonStop() {
		return daemonStop;
	}

	public void setDaemonStop(String daemonStop) {
		this.daemonStop = daemonStop;
	}
	
	
	
	
}
