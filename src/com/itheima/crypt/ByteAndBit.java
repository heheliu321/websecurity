package com.itheima.crypt;

import java.io.UnsupportedEncodingException;

/**
 * 4.Byte和bit
 */
public class ByteAndBit {
	
	public static void main(String[] args) {
		
		String input = "ABc";
		//获取字符串字节
		byte[] bytes = input.getBytes();
		System.out.println("一个英文字母占用"+bytes.length+"字节");
		for (byte b : bytes) {
			System.out.print(b + " ");
			//获取每一个字节占用位数：转成二进制
			String binary = Integer.toBinaryString(b);
			System.out.println(binary);
		}
		
		//中文Byte和bit：utf-8每一个中文占用3个字节，gbk每一个中文占用2个字节

		String input2 = "我";
		//获取字符串字节
		byte[] bytes2 = input2.getBytes();
		try {
			byte[] bytes3 = input2.getBytes("GBK");
			System.out.println("一个中文字母占用"+bytes2.length+"字节");
			for (byte b : bytes2) {
				System.out.print(b + " ");
				//获取每一个字节占用位数：转成二进制
				String binary = Integer.toBinaryString(b);
				System.out.println(binary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
