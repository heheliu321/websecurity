package com.itheima.crypt;

/**
 * 2.凯撒加密算法
 */
public class KaiserDemo {
	
	public static void main(String[] args) {
		//demo1();
		
		//多个字符，凯撒加密
		String input = "Hello I love You!";
		//秘钥
		int key = 3;
		String encrypt = encrypt(input, key);
		System.out.println("凯撒加密：" + encrypt);
		
		//解密
		String decrypt = decrypt(encrypt, key);
		System.out.println("凯撒解密：" + decrypt);
		
	}

	/**
	 * 凯撒加密
	 * @param input
	 * @param key
	 * @return 
	 */
	public static String encrypt(String input, int key) {
		//获取字符数组
		char[] charArray = input.toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : charArray) {
			//遍历每一个字符，获取对应的ascii编码
			int ascill = c;
			ascill += key;
			//获取ascii对应的字符
			char result = (char) ascill;
			//System.out.print(result);
			stringBuilder.append(result);
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 凯撒解密
	 */
	public static String decrypt(String input, int key) {
		//获取字符数组
		char[] charArray = input.toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		for (char c : charArray) {
			//遍历每一个字符，获取对应的ascii编码
			int ascill = c;
			ascill -= key;//逆过来
			//获取ascii对应的字符
			char result = (char) ascill;
			//System.out.print(result);
			stringBuilder.append(result);
		}
		return stringBuilder.toString();
	}


	/**
	 * 偏移单个字符
	 */
	public static void demo1() {
		//凯撒加密底层机制：对字符偏移一定的位数，A-> 1 = B,f->g
		char ch = 'A';
		//获取支付ascii编码
		int ascii = ch;
		//偏移
		//ascii = ascii + 1;
		ascii += 2;
		
		System.out.println(ascii);
		
		//获取ascii编码对应的字符
		char result = (char) ascii;
		System.out.println("A偏移2位：" + result);
	}

}
