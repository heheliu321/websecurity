package com.itheima.crypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 对称加密应用实战
 */
public class DESUage {
	
	private static final String password = "12345678";
	private static final String fileName = "contacts.dat";
	
	public static void main(String[] args) {
		
		try {
			//获取服务器返回json
			HttpURLConnection conn = (HttpURLConnection) new URL("http://120.77.241.119/Contacts/list").openConnection();
			InputStream is = conn.getInputStream();
			//转成字符串
			String result = Util.inputStream2String(is);
			//System.out.println(result);
			
			Reader in = new FileReader(fileName);
			//解密缓存内容
			//1.获取缓存数据
			BufferedReader br = new BufferedReader(in);
			String readLine = br.readLine();
			br.close();
			System.out.println(readLine);
			//2.解密
			String decrypt = DESCrypt.decrypt(readLine, password);
			System.out.println("解密缓存数据：" + decrypt);
			//encryptFile(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密文件
	 * @param result
	 * @throws IOException
	 */
	public static void encryptFile(String result) throws IOException {
		Writer out= new FileWriter(fileName);
		//缓存到本地：文件、数据库
		BufferedWriter bw = new BufferedWriter(out);
		bw.write(DESCrypt.encrypt(result, password));//加密存储
		
		bw.flush();
		bw.close();
	}

}
