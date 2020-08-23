package com.itheima.crypt;

import com.itheima.crypt.util.Base64;

/**
 * 6.Base46编码和解密
 */
public class Base64Demo {

	public static void main(String[] args) {
		// 原文
		String input = "黑马";
		// 秘钥
		String password = "12345678";// DES秘钥长度8位
		printBytes(input);
		
		System.out.println("原文byte数组长度：" + input.getBytes().length);
		
		String encrypt = DESCrypt.encrypt(input, password);
		
		System.out.println("DES加密密文：" + encrypt);
		//System.out.println("DES加密密文Base64编码：" + Base64.encode(encrypt));
		printBytes(new String(encrypt));
		
		//加密后密文长读发生改变，在编码表找不到对应字符，乱码
		
		System.out.println("DES密文byte数组长度：" + encrypt.getBytes().length);
		
		//密文希望变成可见：ABC-Z
		
	}
	
	public static void printBytes(String input){
		byte[] bytes = input.getBytes();
		for (byte b : bytes) {
			System.out.print(b + " ");
		}
	}

}
 