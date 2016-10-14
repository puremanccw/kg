package com.charles.test.innerclass;

/**
 * 内部类：
 * 		成员内部类
 * 		局部内部类
 * 		匿名内部类:
 * 				不能有访问修饰符和static修饰符
 * 				唯一一种没有构造器的类，大部分匿名内部类用于接口回调。
 * 		静态内部类
 * 
 * 
 * 内部类使用场景：
 * 	1、每个内部类都能独立的继承一个接口的实现，所以无论外部类是否继承了够格接口的实现，对于内部类都没有影响。
 * 		内部类使得多继承的解决方案变得完美
 * 	2、方便将存在一定逻辑关系的类组织在一起，又可以对外界隐藏
 * 	3、方便编写时间驱动程序
 * 	4、方便编写线程代码
 * @author puremancw
 *
 */
public class InnerClassDemo {
	
	public static void main(String[] args) {
		//成员内部类依附外部类而存在，也就是说，如果要创建成员内部类的对象，前提必须存在一个外部类的对象
		Outter outter = new Outter();
		Outter.Inner inner = outter.new Inner();		//通过Outter对象来创建
		
		Outter.Inner inner1 = outter.getInnerInstance();
	}
}

/**
 * 成员内部类：
 * 		Draw像是类Circle的一个成员，成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）
 * 		当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即默认情况下访问的是成员内部类的成员
 * 		访问外部类的同名成员的形式：
 * 		外部类.this.成员变量
 * 		外部类.this.成员方法
 * 
 * 		成员内部类可以无条件的访问外部类的成员
 * 		外部类访问成员内部类必须先创建一个成员内部类的对象，再通过这个对象的引用来访问
 * 
 * 		成员内部类依附外部类而存在，也就是说，如果要创建成员内部类的对象，前提必须存在一个外部类的对象
 * @author puremancw
 *
 */
class Outter {
	
	private Inner inner = null;
	
	public static int count = 1;
	
	public Outter () {}
	
	public Outter(Inner inner) {
		this.inner = inner;
		getInnerInstance().innerMethod(); 		//先创建成员内部类的对象，再进行访问。
	}
	
	/**
	 * 创建一个成员内部类的对象
	 * @return
	 */
	public Inner getInnerInstance() {
		return new Inner();
	}
	
	class Inner {
		public void innerMethod() {
			System.out.println(inner); 		//外部类的private成员
			System.out.println(count); 		//外部类的静态成员
		}
	}
}

/**
 * 局部内部类：	
 * 		定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内
 * 		
 * 		局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的
 * 	
 * @author puremancw
 *
 */
class People {
	public People() {}
}

class Man {
	public Man() {}
	
	public People getWoman() {
		class Woman extends People {	//局部内部类
			int age = 0;
		}
		return new Woman();
	}
}

/**
 * 静态内部类：
 * 			定义在另一个类里面的类，只不过在类的前面多了一个关键字static。
 * 			静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似
 * 			不能使用外部类的非static成员变量或者方法。
 * @author puremancw
 *
 */
class StaticOutter {
	
	int a = 10;
	
	static int b = 5;
	
	public StaticOutter() {}
	
	static class StaticInner {
        public StaticInner() {
//        	System.out.println(a);			error
        	System.out.println(b);
        }
    }
}
