package com.itheima.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.itheima.crypt.util.Base64;

/**
 * 6.对称加密算法：AES加密解密
 */
public class AESCrypt {
	
	
	
	private static final String ALGORITHM = "AES";
	//private static final String TRANSFORMATION  = "AES/ECB/PKCS5Padding";
	private static final String TRANSFORMATION  = "AES/CBC/PKCS5Padding";
	// private static final String TRANSFORMATION  = "AES/CBC/NoPadding";//不填充：AES原文长度必须是16个字节整数倍

	public static void main(String[] args) {
		String input = "欢迎来到黑马程序员";
		String password = "1234567812345678";//AES秘钥长度16
		
		String encrypt = encrypt(input, password);
		System.out.println("AES加密：" + encrypt);
		System.out.println(encrypt.length());
		String decrypt = decrypt(encrypt, password);
		System.out.println("AES解密：" + decrypt);
		
	}

	/**
	 * AES加密
	 * @param input
	 * @param password
	 */
	public static String encrypt(String input, String password) {
		try {
			//加密算法三部曲
			
			//1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			//秘钥工厂生成秘钥：没有AES参数
			//秘钥规则类
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), ALGORITHM);
			
			//Key key = null;//秘钥对象：通过字符串封装成AES需要的类型
			//2.初始化加密/解密模式
			//cipher.init(Cipher.ENCRYPT_MODE, key);
			IvParameterSpec iv = new IvParameterSpec(password.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			//3.加密/解密
			byte[] encryptBytes = cipher.doFinal(input.getBytes());
			String encrypt = Base64.encode(encryptBytes);
			
			//System.out.println("AES加密：" + new String(encryptBytes));
			//System.out.println(encrypt);
			return encrypt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * AES解密
	 * @param input 密文
	 * @param password
	 * @return
	 */
	public static String decrypt(String input, String password) {
		try {
			//加密算法三部曲
			
			//1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			//秘钥工厂生成秘钥：没有AES参数
			//秘钥规则类
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), ALGORITHM);
			
			//Key key = null;//秘钥对象：通过字符串封装成AES需要的类型
			//2.初始化加密/解密模式
			//cipher.init(Cipher.DECRYPT_MODE, key);
			IvParameterSpec iv = new IvParameterSpec(password.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			//3.加密/解密
			byte[] encryptBytes = cipher.doFinal(Base64.decode(input));
			
			//System.out.println("AES加密：" + new String(encryptBytes));
			//System.out.println(encrypt);
			return new String(encryptBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
