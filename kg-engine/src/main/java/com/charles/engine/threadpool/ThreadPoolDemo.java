package com.charles.engine.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
	
	public static void main(String[] args) {
		/**
		 * ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
           corePoolSize：	核心池大小
           maxinumPoolSize：  线程池最大线程数
           keepAliveTime：	线程空闲时间
           unit：			keepAliveTime的时间单位
           					TimeUnit.DAYS			天
           					TimeUnit.HOURS			小时
           					TimeUnit.MINUTES		分钟
           					TimeUnit.SECONDS		秒
           					TimeUnit.MILLISECONDS	毫秒
           					TimeUnit.MICROSECONDS	微秒
           					TimeUnit.NANOSECONDS	纳秒
           	workQueue：		阻塞队列，用于存储等待执行的任务
           					ArrayBlockingQueue		：基于数组实现的一个阻塞队列（先进先出），必须指定大小
           					LinkedBlockingqueue		：基于链表实现的一个阻塞队列（先进先出），如果不指定大小，默认大小为Integer.MAX_VALUE
           					PriorityBlockingQueue	:次队列为无界阻塞队列，按优先级顺序出队，
           					DelayQueue				：基于PriorityQueue的一种延时的无界阻塞队列，DelayQueue中的元素只有当其指定延时时间到了才能从队列中获取该元素
           					SynchronousQueue
           	threadFactory:	线程工厂，用于创建线程
           	handler：		拒绝处理任务时的策略	
           					ThreadPoolExecutor.AbortPolicy			丢弃任务并抛出RejectedExecutionException异常
           					ThreadPoolExecutor.DiscardPolicy		丢弃任务但不抛出异常
           					ThreadPoolExecutor.DiscardOldestPolicy	丢弃队列对前面的任务，然后重新尝试执行任务
           					ThreadPoolExecutor.CallerRunsPolicy		由调用线程处理该任务
		 */
		//创建线程池
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 15, 200, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20));
		//corePoolSize和maxinumPoolSize值是相等的，阻塞队列用的是LinkedBlockingQueue
		ExecutorService executor1 = Executors.newFixedThreadPool(10);
		//corePoolSize和maxinumPoolSize值均为1，阻塞队列用的是LinkedBlockingQueue
		ExecutorService executor4 = Executors.newSingleThreadExecutor(); 
		//corePoolSize值为0，maxinumPoolSize值为Integer.MAX_VALUE，阻塞队列用的是SynchronousQueue
		//来了任务就创建线程，线程空闲超过60s就销毁线程
		ExecutorService executor2 = Executors.newCachedThreadPool();
		ExecutorService executor3 = Executors.newScheduledThreadPool(10);
		
		for(int i = 0; i < 15; i++) {
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
			System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目："
					+ executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
		}
		executor.shutdown();
	}
}

class MyTask implements Runnable {
	
	private int taskNum;
	
	public MyTask(int num) {
		this.taskNum = num;
	}
	
	public void run() {
		System.out.println("正在执行task：" + taskNum);
		try{
			Thread.currentThread().sleep(4000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("task：" + taskNum + "执行完毕！");
	}
	
}