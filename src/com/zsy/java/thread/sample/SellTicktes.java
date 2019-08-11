package com.zsy.java.thread.sample;

public class SellTicktes extends Thread {

	private static int num = 100 ;
	private static Object obj = new Object() ;

	public SellTicktes(String name) {
		super(name) ;
	}

	@Override
	public void run() {
		// 模拟售票
		while(true) {
			synchronized(obj) {
				if( num > 0 ) {
					try {
						Thread.sleep(100) ;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "正在出售" + (num--) + "张票");
				}
			}
		}
	}
}
