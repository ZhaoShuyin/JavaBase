package com.zsy.java.eenum.sample;

/**
 * 注意事项:
 *
 * 		1: 定义枚举类要用关键字enum
 * 		2: 所有枚举类都是Enum的子类
 * 		3: 枚举类的第一行上必须是枚举项，最后一个枚举项后的分号是可以省略的，但是如果枚举类有其他的东西，这个分号就不能省略。建议不要省略
 * 		4: 枚举类可以有构造器，但必须是private的，它默认的也是private的。枚举项的用法比较特殊：枚举(“”);
 * 		5: 枚举类也可以有抽象方法，但是枚举项必须重写该方法
 * 		6: 枚举在switch语句中的使用
 * 				byte , short , int , char
 * 				JDK1.5以后可以取枚举
 * 				JDK1.7以后可以去字符串
 */
public enum Direction {

	FONT("前"){
		@Override
		public void show() {
			System.out.println(name);
		}
	} , BACK("后"){
		@Override
		public void show() {
			System.out.println(name);
		}
	}  , LEFT("左"){
		@Override
		public void show() {
			System.out.println(name);
		}
	}  , RIGHT("右"){
		@Override
		public void show() {
			System.out.println(name);
		}
	} ;

	public String name ;

	/**
	 * 私有化构造方法
	 */
	private Direction(String name) {
		this.name = name ;
	}

	/**
	 * 抽象方法
	 */
	public abstract void show() ;

}
