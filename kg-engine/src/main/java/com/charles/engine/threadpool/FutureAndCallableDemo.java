package com.charles.engine.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class FutureAndCallableDemo {
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
//		Future<Integer> result =  executor.submit(task);
//		executor.shutdown();
		
		//第二种实现方式
		FutureTask<Integer> result = new FutureTask<Integer>(task);
		executor.submit(result);
		executor.shutdown();
		
		
		try{
			Thread.sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("主线程在执行任务！");
		
		try{
			System.out.println("task运行结果：" + result.get());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("所有任务执行完毕！");
	}
}

class Task implements Callable<Integer> {

	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
		Thread.sleep(3000);
		int sum = 0;
		for(int i = 0; i < 100; i++) {
			sum += i;
		}
		return sum;
	}
	
}
