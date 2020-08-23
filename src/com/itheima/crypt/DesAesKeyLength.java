package com.itheima.crypt;

/**
 * 7.DES、AES秘钥长度分析
 */
public class DesAesKeyLength {
	
	public static void main(String[] args) {
		
		String input = "欢迎来到黑马程序员";
		String desPassword = "12345678";// DES秘钥长度8位
		//String desPassword = "il黑马";// 
		
	/*	
	 * 
	 * DES/CBC/NoPadding (56) 
		DES/CBC/PKCS5Padding (56) 
		DES/ECB/NoPadding (56) 
		DES/ECB/PKCS5Padding (56) 
		
		括号里面：bit位数：56位，密码长度是56位
		
		DES秘钥长度8个字节，一个字节占8个bit，一共占8*8=64个bit=64位，
		DES秘钥最后一个字节不参与加密计算，剩余7个字节参与计算，7 * 8 = 56 bit位
		
		*/
		
		//Byte和bit关系：一个字节占8位，1个Byte=8个bit
		System.out.println("DES秘钥字节个数：" + desPassword.getBytes().length);
		
		DESCrypt.encrypt(input, desPassword);
		
		
		
		//*******************************************************
		String aesPassword = "1234567812345678";//AES秘钥长度16
		
		/*AES/CBC/NoPadding (128) 
		AES/CBC/PKCS5Padding (128) 
		AES/ECB/NoPadding (128) 
		AES/ECB/PKCS5Padding (128) 
		
		128个bit位，等于128/8 = 16个字节，AES秘钥长度16个字节
		
		*/
		
		String encrypt = AESCrypt.encrypt(input, aesPassword);
		System.out.println(encrypt);

		
		
	}

}
