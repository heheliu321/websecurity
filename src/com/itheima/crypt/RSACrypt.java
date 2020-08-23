package com.itheima.crypt;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.itheima.crypt.util.Base64;

/**
 * 非对称加密RSA解密和解密
 */
public class RSACrypt {

	private static final String algorithm = "RSA";

	/** 分段加密，每次最大加密长度117字节 */
	private static final int ENCRYPT_MAX_SIZE = 117;
	/** 每次最大解密长度：128字节 */
	private static final int DECRYPT_MAX_SIZE = 128;
	
	private static final String publicStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBFnKLitg+zWbLeepj+fHmtzaGTAmVvTx+qqzhenqjpWufnQQs3CIvmFA95c1XTu1JmXE0oY3mz9BVkmyNvmm5V98pkAjNc6pTA5D9xAPXGAZPcUkMEKAH+vNR301AKOIb8boBZqMqc+O/3LrYYeO8/jKaExIxpQqWQCzeCqTHvwIDAQAB";
	private static final String privateStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIEWcouK2D7NZst56mP58ea3NoZMCZW9PH6qrOF6eqOla5+dBCzcIi+YUD3lzVdO7UmZcTShjebP0FWSbI2+ablX3ymQCM1zqlMDkP3EA9cYBk9xSQwQoAf681HfTUAo4hvxugFmoypz47/cuthh47z+MpoTEjGlCpZALN4KpMe/AgMBAAECgYAftUXZpPdNJeMUJBRBDoou494Oyuqnz13mt0HT+OPbAq0W/diitEfpbP541dPEmGyM4ZX/GZfjlDRWcKsdfiI1vTUiJ5Y0SmQazjAIoHLayJA3ZbH0CrbxSMS7oFuKXOHDDj4iCYsJQVto81NAKPREgAQbuLGmrnz/p4TppVEOEQJBAMG4Cfap+j0ywp2HYHCygLRtlbV9H2IOEYhZE2KboWDU4ikmjfQ8SFftZDgbxSpsbrZSds27pKGjVq7MGoRXeJMCQQCqlvXsP8DaCO0zlMf38xxiuNFNeYV+yHoP6SWArscy+o6bnch/xlmIDr9nfRVgTA804M9S/hQmvnS0xKU160ulAkAYLRK5QP9k8dfN9x43El/zpJWBf+sRvrW7cXp03P1n60mKXzBqIbfZmVvfkL+rirrKcEI5bMigD5V63SgWCiCXAkAIEQlh+YXKCaAFz2RBUkqmVGz5R+TLIPm8pN60Hg+nVnfF3gksGZoEOAZPA3guTIpviq3jE8aqKllkbuQND6pNAkBrPTWuM3xZsHW1VE6EXQsz+SqGgXwXIw+9bERRX06LfcleAkz9qhqRRbvXzGxUQCBlvIEvo6VRnDLM4KT4maom";

	public static void main(String[] args) {
		try {
			/*// 秘钥对生成器
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
			// 秘钥对称对象
			KeyPair keyPair = keyPairGenerator.generateKeyPair();

			// 生成秘钥对：公钥和私钥
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();*/
			
			//创建秘钥工厂对象
			KeyFactory kf = KeyFactory.getInstance(algorithm);
			
			PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(privateStr)));
			PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(Base64.decode(publicStr)));
			// 转成字符串
			String privateKeyStr = Base64.encode(privateKey.getEncoded());
			String publicKeyStr = Base64.encode(publicKey.getEncoded());
			System.out.println("公钥：" + publicKeyStr);
			System.out.println("私钥：" + privateKeyStr);

			// 原文
			String input = "黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马黑马马黑马黑马黑马";
			;
			System.out.println("原文byte长度：" + input.getBytes().length);

			// RSA每次加密最大长度117字节
			String encryptByPublicKey = encryptByPublicKey(publicKey, input);
			System.out.println("公钥加密=" + encryptByPublicKey);
			String result = encryptByPrivateKey(privateKey, input);
			System.out.println("私钥加密=" + result);

			// 公钥解密
			String decryptByPublicKey = decryptByPublicKey(publicKey, result);
			System.out.println("公钥解密：" + decryptByPublicKey);
			String decryptByPrivateKey = decryptByPrivateKey(privateKey, encryptByPublicKey);
			System.out.println("私钥解密：" + decryptByPrivateKey);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PublicKey getPublicKey(){
		try {
			KeyFactory kf = KeyFactory.getInstance(algorithm);
			return kf.generatePublic(new X509EncodedKeySpec(Base64.decode(publicStr)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PrivateKey getPrivateKey(){
		try {
			KeyFactory kf = KeyFactory.getInstance(algorithm);
			
			PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(privateStr)));
			return privateKey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥加密
	 */
	public static String encryptByPrivateKey(PrivateKey privateKey, String input) {
		try {
			// 私钥加密
			// Cipher加密解密三部曲
			// 1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(algorithm);
			// 2.初始化模式：加密/解密
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			// 3.加密/解密
			int offset = 0;
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baso = new ByteArrayOutputStream();
			while (input.getBytes().length - offset > 0) {
				if (input.getBytes().length - offset >= ENCRYPT_MAX_SIZE) {
					// 加密完整117长度
					buffer = cipher.doFinal(input.getBytes(), offset, ENCRYPT_MAX_SIZE);
					// 重新计算偏移量
					offset += ENCRYPT_MAX_SIZE;
				} else {
					// 剩余最后一块
					buffer = cipher.doFinal(input.getBytes(), offset, input.getBytes().length - offset);
					offset = input.getBytes().length;
				}
				baso.write(buffer);
			}
			// byte[] privateEncrypt = cipher.doFinal(input.getBytes());
			String result = Base64.encode(baso.toByteArray());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分段加密，每次最大加密长度117字节 公钥加密
	 */
	public static String encryptByPublicKey(PublicKey publicKey, String input) {
		try {
			// 私钥加密
			// Cipher加密解密三部曲
			// 1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(algorithm);
			// 2.初始化模式：加密/解密
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			// 3.加密/解密
			int offset = 0;
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baso = new ByteArrayOutputStream();
			while (input.getBytes().length - offset > 0) {
				if (input.getBytes().length - offset >= ENCRYPT_MAX_SIZE) {
					// 加密完整117长度
					buffer = cipher.doFinal(input.getBytes(), offset, ENCRYPT_MAX_SIZE);
					// 重新计算偏移量
					offset += ENCRYPT_MAX_SIZE;
				} else {
					// 剩余最后一块
					buffer = cipher.doFinal(input.getBytes(), offset, input.getBytes().length - offset);
					offset = input.getBytes().length;
				}
				baso.write(buffer);
			}
			// byte[] privateEncrypt = cipher.doFinal(input.getBytes());
			String result = Base64.encode(baso.toByteArray());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公钥解密
	 * 
	 * @param input
	 *            密文（base64编码）
	 */
	public static String decryptByPublicKey(PublicKey publicKey, String input) {
		try {
			byte[] inputDecode = Base64.decode(input);
			// 私钥加密
			// Cipher加密解密三部曲
			// 1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(algorithm);
			// 2.初始化模式：加密/解密
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			// 3.加密/解密
			int offset = 0;
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baso = new ByteArrayOutputStream();
			while (inputDecode.length - offset > 0) {
				if (inputDecode.length - offset >= DECRYPT_MAX_SIZE) {
					// 加密完整117长度
					buffer = cipher.doFinal(inputDecode, offset, DECRYPT_MAX_SIZE);
					// 重新计算偏移量
					offset += DECRYPT_MAX_SIZE;
				} else {
					// 剩余最后一块
					buffer = cipher.doFinal(inputDecode, offset, inputDecode.length - offset);
					offset = inputDecode.length;
				}
				baso.write(buffer);
			}
			//byte[] result = cipher.doFinal(inputDecode);
			return baso.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 私钥解密
	 * @param privateKey
	 * @param input
	 * @return
	 */
	public static String decryptByPrivateKey(PrivateKey privateKey, String input) {
		try {
			byte[] inputDecode = Base64.decode(input);
			// 私钥加密
			// Cipher加密解密三部曲
			// 1.创建Cipher对象
			Cipher cipher = Cipher.getInstance(algorithm);
			// 2.初始化模式：加密/解密
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			// 3.加密/解密
			int offset = 0;
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baso = new ByteArrayOutputStream();
			while (inputDecode.length - offset > 0) {
				if (inputDecode.length - offset >= DECRYPT_MAX_SIZE) {
					// 加密完整117长度
					buffer = cipher.doFinal(inputDecode, offset, DECRYPT_MAX_SIZE);
					// 重新计算偏移量
					offset += DECRYPT_MAX_SIZE;
				} else {
					// 剩余最后一块
					buffer = cipher.doFinal(inputDecode, offset, inputDecode.length - offset);
					offset = inputDecode.length;
				}
				baso.write(buffer);
			}
			//byte[] result = cipher.doFinal(inputDecode);
			return baso.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
