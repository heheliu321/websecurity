package com.itheima.crypt;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 演示登录使用时间戳，结合数字签名算法
 */
public class MessageDigestUsage {
	
	public static void main(String[] args) {
		
		try {
			String url = "http://120.77.241.119/User/login?";
			String password = "12345678";//用户输入明文密码
			String md5 = MessageDigestUtil.md5(password);
			System.out.println(md5);
			//String registerUrl = "http://120.77.241.119/User/register?username=itheima&password="+md5;
			String params = "username=itheima&password="+md5;
			URLConnection conn = new URL(url + params).openConnection();
			System.out.println(conn.getURL().toString());;
			InputStream is = conn.getInputStream();
			String result = Util.inputStream2String(is);
			System.out.println("登录结果：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
