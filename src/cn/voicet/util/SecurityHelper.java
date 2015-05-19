package cn.voicet.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityHelper {

	/**
	 * 加密
	 * 
	 * @param text
	 *            待加密内容
	 * @param key
	 *            公钥
	 * @return
	 */
	public static String DESEncrypt(String text, String key) {
		try {
			// 进行3-DES加密后的内容的字节
			DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey skey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			byte[] encryptedData = cipher.doFinal(text.getBytes());
			// 进行3-DES加密后的内容进行BASE64编码
			BASE64Encoder base64en = new BASE64Encoder();
			return base64en.encode(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return text;
		}
	}

	/**
	 * 解密
	 * 
	 * @param text
	 *            待解密内容
	 * @param key
	 *            公钥
	 * @return
	 */
	public static String DESDecrypt(String text, String key) {
		try {
			// 进行3-DES加密后的内容进行BASE64解码
			BASE64Decoder base64Decode = new BASE64Decoder();
			byte[] base64DValue = base64Decode.decodeBuffer(text);
			// 进行3-DES解密后的内容的字节
			DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey skey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			byte[] encryptedData = cipher.doFinal(base64DValue);
			return new String(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return text;
		}
	}

	/**
	 * 测试加密后的密文
	 */
	public static void main(String[] args) {
		String banks[] = { "govadmin", "begin110" };
		String key = "8a!2e4b4%1b6e2&ba5.-011b?720f-=+";
		for (String a : banks) {
			a = DESEncrypt(a, key);
			System.out.println(a);
		}
	}
}
