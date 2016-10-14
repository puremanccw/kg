package com.charles.engine.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.Assert;

import com.alibaba.druid.support.logging.LogFactory;

public class ThreadPoolTaskExecutor extends CustomizableThreadFactory
		implements SchedulingTaskExecutor, Executor, BeanNameAware, InitializingBean, DisposableBean {
	
	protected final Log logger = (Log) LogFactory.getLog(getClass());
	
	private final Object poolSizeMonitor = new Object();
	
	private int corePoolSize = 1;
	
	private int maxPoolSize = Integer.MAX_VALUE;
	
	private int keepAliveSeconds = 60;
	
	private boolean allowCoreThreadTimeOut = false;
	
	private int queueCapacity = Integer.MAX_VALUE;
	
	private ThreadFactory threadFactory = this;
	
	private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
	
	private boolean waitForTasksToCompleteOnShutdown = false;
	
	private boolean threadNamePrefixSet = false;
	
	private String beanName;
	
	private ThreadPoolExecutor threadPoolExecutor;
	
	public void setCorePoolSiez(int corePoolSize) {
		synchronized (this.poolSizeMonitor) {
			this.corePoolSize = corePoolSize;
			if(this.threadPoolExecutor != null) {
				this.threadPoolExecutor.setCorePoolSize(corePoolSize);
			}
		}
	}
	
	public int getCorePoolSize() {
		synchronized(this.poolSizeMonitor) {
			return this.corePoolSize;
		}
	}
	
	public void setMaxPoolSize(int maxPoolSize) {
		synchronized(this.poolSizeMonitor) {
			this.maxPoolSize = maxPoolSize;
			if(this.threadPoolExecutor != null) {
				this.threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
			}
		}
	}
	
	public int getMaxPoolSize() {
		synchronized(this.poolSizeMonitor) {
			return this.maxPoolSize;
		}
	}
	
	public void setKeepAliveSeconds(int keepAliveSeconds) {
		synchronized(this.poolSizeMonitor) {
			this.keepAliveSeconds = keepAliveSeconds;
			if(this.threadPoolExecutor != null) {
				this.threadPoolExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
			}
		}
	}
	
	public int getKeepAliveSeconds() {
		synchronized(this.poolSizeMonitor) {
			return this.keepAliveSeconds;
		}
	}
	
	public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
		this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
	}
	
	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}
	
	public void setThreadFactory(ThreadFactory threadFactory) {
		this.threadFactory = (threadFactory != null ? threadFactory : this);
	}
	
	public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
		this.rejectedExecutionHandler = 	
				(rejectedExecutionHandler != null ? rejectedExecutionHandler : new ThreadPoolExecutor.AbortPolicy());
	}
	
	public void SetWaitForTasksToCompleteOnShutdown(boolean waitForJobsToCompleteOnShutdown) {
		this.waitForTasksToCompleteOnShutdown = waitForJobsToCompleteOnShutdown;
	}
	
	public void setThreadNamePrefix(String threadNamePrefix) {
		super.setThreadNamePrefix(threadNamePrefix);
		this.threadNamePrefixSet = true;
	}
	
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	public void afterPropertiesSet() {
		initialize();
	}
	
	private void initialize() {
		if(logger.isInfoEnabled()) {
			logger.info("Initializing ThreadPoolExecutor" + (this.beanName != null ? " '" + this.beanName + "'" : ""));
		}
		if(!this.threadNamePrefixSet && this.beanName != null) {
			setThreadNamePrefix(this.beanName + "-");
		}
		BlockingQueue queue = createQueue(this.queueCapacity);
		this.threadPoolExecutor = new ThreadPoolExecutor(
				this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS,
				queue, this.threadFactory, this.rejectedExecutionHandler);
		if(this.allowCoreThreadTimeOut) {
			this.threadPoolExecutor.allowCoreThreadTimeOut(true);
		}
	}

	private BlockingQueue createQueue(int queueCapacity) {
		if(queueCapacity > 0) {
			return new LinkedBlockingQueue(queueCapacity);
		} else {
			return new SynchronousQueue();
		}
	}
	
	public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
		Assert.state(this.threadPoolExecutor != null, "ThreadPoolTaskExecutor not initialized");
		return this.threadPoolExecutor;
	}
	
	public void execute(Runnable task) {
		Executor executor = getThreadPoolExecutor();
		try{
			executor.execute(task);
		} catch(RejectedExecutionException ex) {
			throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
		}
	}
	
	public boolean prefersShortLivedTasks() {
		return true;
	}
	
	public int getPoolSize() {
		return getThreadPoolExecutor().getPoolSize();
	}
	
	public int getActiveCount() {
		return getThreadPoolExecutor().getActiveCount();
	}
	
	public void destroy() throws Exception {
		shutdown();
	}
	
	private void shutdown() {
		if(logger.isInfoEnabled()) {
			logger.info("Shutting down ThreadPoolExecutor" + (this.beanName != null ? " '" + this.beanName + "'" : ""));
		}
		if(this.waitForTasksToCompleteOnShutdown) {
			this.threadPoolExecutor.shutdown();
		} else {
			this.threadPoolExecutor.shutdownNow();
		}
	}

	public void execute(Runnable arg0, long arg1) {
		
	}

	public Future<?> submit(Runnable arg0) {
		return null;
	}

	public <T> Future<T> submit(Callable<T> arg0) {
		return null;
	}

	

}
