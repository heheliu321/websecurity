package com.itheima.crypt;

/**
 * 1.获取字符ascii编码
 */
public class AsciiDemo {
	
	public static void main(String[] args) {
		char ch = 'A';//字符
		//获取字符ascii编码
		int ascill = ch;
		
		System.out.println(ascill);
		
		
		//获取字符串ascii编码
		String input = "Hello";
		//字符数组
		char[] chArray = input.toCharArray();
		for (char c : chArray) {
			//获取每个字符ascii编码
			int value = c;
			System.out.println(value);
		}
	}

}
