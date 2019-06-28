package db;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Cipher {
	
	private final static String KEY = "aes256-test-key!";
	private final static String IV = "aes256-test-key!";
	
	private static AES256Cipher instance = null;
	
	public static AES256Cipher getInstance() {
		if (instance == null) {
			instance = new AES256Cipher();
		}
		return instance;
	}
	
	private AES256Cipher() {
		
	}
	
	public String encryption(String plainText) { // 암호화
		
		String encryptionText = null;
		
		try {
			SecretKey keySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(IV.getBytes("UTF-8"));

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			
			byte[] cipherBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
			
			encryptionText = new String(Base64.encodeBase64(cipherBytes));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encryptionText;
	}
	
	public String decryption(String encryptionText) { // 복호화

		String decryptionText = null;

		try {
			SecretKey keySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(IV.getBytes("UTF-8"));

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

			byte[] base64decoded = Base64.decodeBase64(encryptionText.getBytes("UTF-8"));
			decryptionText = new String(cipher.doFinal(base64decoded), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return decryptionText;
	}
	
	/*public static void main(String[] args) {
		
		String password = "1";
		System.out.println("비밀번호: " + password);
		
		String encryptionText = AES256Cipher.getInstance().encryption(password);
		System.out.println("암 호 화: " + encryptionText);
		
		String plainText = AES256Cipher.getInstance().decryption(encryptionText);
		System.out.println("복 호 화: " + plainText);
		
	}*/
}
