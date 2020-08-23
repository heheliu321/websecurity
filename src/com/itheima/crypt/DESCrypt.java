package com.itheima.crypt;

import java.security.Key;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.itheima.crypt.util.Base64;

/**
 * 对称加密：DES加密和解密
 */
public class DESCrypt {
	
	//1FylY3LQOEYE73WwIPSKyrI9zQHBhUUSypzLX1R06wo=

	/**
	 * 如果不写工作模式和填充模式，默认使用的是：ECB/PKCS5Padding
	 * CBC模式：要求init方法需求添加额外参数
	 */
	private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";// 算法/工作模式/填充模式
	//private static final String TRANSFORMATION = "DES/CBC/NoPadding";//不填充：DES原文长度必须是8个byte整数倍
	private static final String ALGORITHM = "DES";// 算法

	public static void main(String[] args) {
		// 原文
		String input = "欢迎来到黑马程序员";//
		System.out.println("原文byte数组长度：" + input.getBytes().length);
		// 秘钥
		String password = "12345678";// DES秘钥长度8位
		
		//DES加密密文长度8的整数倍

		// 加密算法思路：通过查看api文档封装公司自己的加密算法
		// 加密算法核心类：Cipher

		String encrypt = encrypt(input, password);
		System.out.println("DES加密：" + encrypt);
		System.out.println(encrypt.length());

		String decrypt = decrypt(encrypt, password);
		System.out.println("DES解密：" + new String(decrypt));
	}

	/**
	 * 5.DES加密
	 * 
	 * @param input
	 * @param password
	 */
	/*public static byte[] encrypt(String input, String password) {
		try {
			// 加密算法三
	 * @return
	 */
	public static String encrypt(String input, String password) {
		try {
			// 加密算法三部曲
			// 1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			// 秘钥工厂
			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
			KeySpec keySpec = new DESKeySpec(password.getBytes());// 秘钥规则对象
			Key key = skf.generateSecret(keySpec);// 秘钥对象
			// 2.初始化模式：加密/解密
			//cipher.init(Cipher.ENCRYPT_MODE, key);
			IvParameterSpec iv = new IvParameterSpec(password.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			// 3.加密/解密
			byte[] encryptBytes = cipher.doFinal(input.getBytes());
			//System.out.println("没有Base64编码长度="+encryptBytes.length);

			// System.out.println("DES加密："+new String(encryptBytes));

			return Base64.encode(encryptBytes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DES解密
	 * 
	 * @param input
	 * @param password
	 */
	public static String decrypt(String input, String password) {
		try {
			// 加密算法三部曲
			// 1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			// 秘钥工厂
			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
			KeySpec keySpec = new DESKeySpec(password.getBytes());// 秘钥规则对象
			Key key = skf.generateSecret(keySpec);// 秘钥对象
			// 2.初始化模式：加密/解密
			IvParameterSpec iv = new IvParameterSpec(password.getBytes());
			//cipher.init(Cipher.DECRYPT_MODE, key);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			// 3.加密/解密
			byte[] encryptBytes = cipher.doFinal(Base64.decode(input));//Base64解密

			// System.out.println("DES加密："+new String(encryptBytes));

			return new String(encryptBytes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
