package com.itheima.crypt;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import com.itheima.crypt.util.Base64;

/**
 * 数字签名
 */
public class SignatureDemo {
	
	private static final String ALGORITHM = "SHA256withRSA";

	public static void main(String[] args) {
		String input = "name=iPhone&price=6888&count=1";
		String sign = sign(input);
		System.out.println("签名="+sign);
		
		
		String input2 = "name=iPhone&price=600&count=1";
		boolean verify = verify(input2, sign);
		System.out.println("校验结果：" + verify);
	}

	/**
	 * 校验数字签名
	 * @param input 原文
	 * @param sign 签名
	 * @return
	 */
	public static boolean verify(String input, String sign) {
		try {
			//******************校验签名信息：四部曲******************
			//1.创建数字签名对象
			Signature signature = Signature.getInstance(ALGORITHM);
			PublicKey publicKey = RSACrypt.getPublicKey();
			//2.初始化校验
			signature.initVerify(publicKey);
			//3.传入原文
			signature.update(input.getBytes());
			//4.开始校验
			boolean verify = signature.verify(Base64.decode(sign));
			//System.out.println("校验结果="+verify);
			return verify;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String sign(String input) {
		try {
			//******************签名四部曲******************
			//1.创建数字签名对象
			Signature signature = Signature.getInstance(ALGORITHM);
			PrivateKey privateKey = RSACrypt.getPrivateKey();
			//2.初始化签名
			signature.initSign(privateKey);
			//3.传入原文
			signature.update(input.getBytes());
			//4.开始签名
			byte[] sign = signature.sign();
			String encode = Base64.encode(sign);
			
		
			return encode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
