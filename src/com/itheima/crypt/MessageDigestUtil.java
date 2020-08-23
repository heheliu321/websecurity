package com.itheima.crypt;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * 消息摘要工具类:核心类MessageDigest
 */
public class MessageDigestUtil {
	
	public static void main(String[] args) {
		//String input = "hello";//不论原文长度多长，加密密文长度固定
	/*	String input = "黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马马黑马黑马黑马";;//不论原文长度多长，加密密文长度固定
		md5(input);*/
		
		//******************读取文件md5摘要信息******************
		/*String md5File = md5File("apache-tomcat-9.0.1.zip");
		System.out.println("文件md5值：" + md5File);*/
		
		String input = "黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马马黑马黑马黑马";;//不论原文长度多长，加密密文长度固定
		String sha1 = sha1(input);
		System.out.println(sha1);
		
		String sha256 = sha256(input);
		System.out.println(sha256);
		
		
	}
	
	public static String sha1(String input){
		try {
			//消息摘要2部曲
			//1.创建MessageDigest对象
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			//2.调用digest方法
			byte[] digest = messageDigest.digest(input.getBytes());
			System.out.println("sha1 字节长度：" + digest.length);
			StringBuilder hex = toHex(digest);
			System.out.println("sha1 转成16进制字节长度：" + hex.toString().getBytes().length);
			return hex.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sha256(String input){
		try {
			//消息摘要2部曲
			//1.创建MessageDigest对象
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			//2.调用digest方法
			byte[] digest = messageDigest.digest(input.getBytes());
			System.out.println("SHA-256 字节长度：" + digest.length);
			StringBuilder hex = toHex(digest);
			System.out.println("SHA-256 转成16进制字节长度：" + hex.toString().getBytes().length);
			return hex.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 读取文件md5摘要
	 * @param filePath
	 * @return
	 */
	public static String md5File(String filePath) {
		//创建消息摘要对象
		try {
			//文件输入流
			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[1024];
			int len = 0;
			//Byte数组输出流
			ByteArrayOutputStream baso = new ByteArrayOutputStream();
			while((len = fis.read(buffer)) != -1){
				baso.write(buffer, 0, len);
			}
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] md5 = messageDigest.digest(baso.toByteArray());
			
			StringBuilder stringBuilder = toHex(md5);
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * MD5加密：
	 * 1.密文长度16个字节
	 * 2.转成16进制字符串32个字节
	 * @param input
	 * @return
	 */
	public static String md5(String input) {
		//创建消息摘要对象
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] md5 = messageDigest.digest(input.getBytes());
			
			//没有转成16进制，原始密文长度
			System.out.println("md5密文长度：" + md5.length);
			
			StringBuilder stringBuilder = toHex(md5);
			System.out.println(stringBuilder.toString());
			
			System.out.println("tomcat md5值长度：" + "13a66f1118984cf9b45bbabae73d1d6d".getBytes().length);
			System.out.println("我们的 md5值长度：" + stringBuilder.toString().getBytes().length);
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转成16进制字符串
	 * @param input
	 * @return
	 */
	public static StringBuilder toHex(byte[] input) {
		//转成16进制
		StringBuilder stringBuilder = new StringBuilder();
		//System.out.println("md5加密："+Base64.encode(md5));
		for (byte b : input) {
			int value = b & 0xff;//转成16进制
			String hexString = Integer.toHexString(value);
			//16进制字符串长度不是2位数，前面补零
			if(hexString.length() == 1){
				stringBuilder.append("0");
			}
			//System.out.println(hexString);
			stringBuilder.append(Integer.toHexString(value));
		}
		return stringBuilder;
	}

}
