package com.charles.test.design;

/**
 * 
 * <p>Title:SingletonClass</p>
 * <p>Description:
 * 	1、把类的构造器写成private，从而保证别的类不能实例化此类
 * 	2、提供一个静态的实例并能够返回给使用者，通过这个引用使用到这个类的实例
 * </p>
 *
 * @author puremancw
 *
 */
public class SingletonClass {
	
	private volatile static SingletonClass instance = null;
	
	/**
	 * synchronized修饰的同步块性能很慢，
	 * 检测null的操作和创建对象的操作分离了。
	 * 如果这两个操作能够原子地进行，那么单例就已经保证了
	 * @return
	 */
	public static SingletonClass getInstance() {
		
		if(instance == null) {
			synchronized(SingletonClass.class) {
				if(instance == null ) {
					instance = new SingletonClass();
				}
			}
		}
		
		return instance;
	}
	private SingletonClass() {}
}
