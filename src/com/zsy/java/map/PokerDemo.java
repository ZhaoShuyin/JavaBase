package com.zsy.java.map;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 模拟斗地主: 洗牌 , 发牌 , 看牌 
 * 
 * 分析:
 * 		1: 定义一个ArrayList<String>集合对象,用来作为牌盒
 * 		2: 定义两个数组:   一个是花色数组 , 一个是数字数组
 * 		3: 遍历数组生成牌
 * 		4: 把生成的牌添加到牌盒中
 * 		5: 在添加大小王
 * 		6: 洗牌
 * 		7: 发牌
 * 		8: 看牌	
 */
public class PokerDemo {
	
	public static void main(String[] args) {
		
		// 定义一个ArrayList<String>集合对象,用来作为牌盒
		ArrayList<String> pokerBox = new ArrayList<String>() ;
		
		// 定义两个数组:   一个是花色数组 , 一个是数字数组
		String[] colors = {"♥" , "♠" , "♣" , "♦"} ;
		String[] nums = {"A" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10" , "J" , "Q" , "K"} ;
		
		//遍历数组生成牌
		for(String color : colors) {
			for(String num : nums){
				String poker = color.concat(num) ;
				pokerBox.add(poker) ;
			}
		}
		
		// 在添加大小王
		pokerBox.add("小王") ;
		pokerBox.add("大王") ;
		
		// 洗牌
		Collections.shuffle(pokerBox) ;
		
		// 发牌
		// 定义集合
		ArrayList<String> zhourunfaList = new ArrayList<String>() ;
		ArrayList<String> zhouxinxinList = new ArrayList<String>() ;
		ArrayList<String> liudehuaList = new ArrayList<String>() ;
		ArrayList<String> dipaiList = new ArrayList<String>() ;
		
		for(int x = 0 ; x < pokerBox.size() ; x++){
			
			if(x >= pokerBox.size() - 3) {
				dipaiList.add(pokerBox.get(x)) ;
			}else if( x % 3 == 0) {
				zhourunfaList.add(pokerBox.get(x)) ;
			}else if(x % 3 == 1) {
				zhouxinxinList.add(pokerBox.get(x)) ;
			}else if( x % 3 == 2) {
				liudehuaList.add(pokerBox.get(x)) ;
			}
			
		}
		
		// 看牌
		lookPoker("周润发" , zhourunfaList) ;
		lookPoker("周星星" , zhouxinxinList) ;
		lookPoker("刘德华" , liudehuaList) ;
		lookPoker("底牌" , dipaiList) ;
		
	}
	
	/**
	 * 看牌的方法
	 */
	private static void lookPoker(String player, ArrayList<String> list) {
		System.out.print(player + "的牌是: ");
		for(int x = 0 ; x < list.size() ; x++) {
			if(x == list.size() - 1){
				System.out.println(list.get(x));
			}else {
				System.out.print(list.get(x) + "\t");
			} 
		}
	}

}
