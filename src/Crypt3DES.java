import javax.crypto.Cipher;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.*;
import java.nio.charset.Charset;

import javax.crypto.SecretKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Crypt3DES {

	SecretKey key;
	Cipher desCipher;
	AlgorithmParameters parameters;
	
	/*constructor*/
	public Crypt3DES(SecretKey key) {
		this.key = key;
		try {
			this.desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			this.desCipher.init(Cipher.ENCRYPT_MODE, this.key);
			this.parameters = desCipher.getParameters();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
        	return;
		}
		catch (NoSuchPaddingException e) {
			e.printStackTrace();
        	return;
		}
		catch (InvalidKeyException e) {
        	e.printStackTrace();
        	return;
        }
	}
	
	/*cripteaza mesajul message cu cheia key data in constructor*/
	public String encryptMessage(String message) {
		try {
		    
			
			byte[] byteDataToEncrypt = message.getBytes();
			byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt);
			String strCipherText = new BASE64Encoder().encode(byteCipherText);
			//System.out.println("Cipher Text generated using 3DES with CBC mode and PKCS5 Padding is: " + strCipherText);
			
			return strCipherText;
		}
        catch (IllegalBlockSizeException e) {
        	e.printStackTrace();
        	return null;
        }
        catch(BadPaddingException e) {
        	e.printStackTrace();
        	return null;
        }
		
	}

	public String decryptMessage2(String message) {
		return message;
	}
	
	/**/
	public String decryptMessage(String message) {
		try {
			
			this.desCipher.init(Cipher.DECRYPT_MODE, key, this.parameters);
			
			
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] byteDataToEncrypt = decoder.decodeBuffer(message);
			
			System.out.println("aici! " + message);
			System.out.println("byte " + byteDataToEncrypt.toString());
			byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt); 
			System.out.println("byte " + byteCipherText.toString());
			String strDecryptedText = new String(byteCipherText, "UTF-8");
	   		
	   		return strDecryptedText;
		}
        catch (InvalidKeyException e) {
        	e.printStackTrace();
        	return null;
        }
		catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
        	return null;
		}
		catch (IllegalBlockSizeException e) {
        	e.printStackTrace();
        	return null;
        }
        catch(BadPaddingException e) {
        	e.printStackTrace();
        	return null;
        }
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
        	return null;
		}
		catch (IOException e) {
			e.printStackTrace();
        	return null;
		}
	}
}
