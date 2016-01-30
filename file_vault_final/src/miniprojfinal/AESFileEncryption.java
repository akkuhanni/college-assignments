package miniprojfinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESFileEncryption {
    public static void encrypt(String source,String direc) throws Exception
    {

		// file to be encrypted
                System.out.println(source);
                String strPath = source.substring(source.lastIndexOf(File.separator)+1, source.length());
		FileInputStream inFile = new FileInputStream(source);
              

		// encrypted file
                String path = direc+File.separator+strPath;
                System.out.println(path);
                // Use relative path for Unix systems
                //File outFile = new File(path);
                // Works for both Windows and Linux
                //outFile.getParentFile().mkdirs(); 
                //f.createNewFile();
		FileOutputStream outFile = new FileOutputStream(path+".des");
                String path1 = path+".des";
                System.out.println(path1);
		// password to encrypt the file
		String password = "mini";

		// password, iv and salt should be transferred to the other end
		// in a secure manner

		// salt is used for encoding
		// writing it to a file
		// salt should be transferred to the recipient securely
		// for decryption
		byte[] salt = new byte[8];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);
		FileOutputStream saltOutFile = new FileOutputStream("salt.enc");
		saltOutFile.write(salt);
		saltOutFile.close();
                
		SecretKeyFactory factory = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 16384,
				128);
		SecretKey secretKey = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
                
		//
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
                
		// iv adds randomness to the text and just makes the mechanism more
		// secure
		// used while initializing the cipher
		// file to store the iv
		FileOutputStream ivOutFile = new FileOutputStream("iv.enc");
		byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
		ivOutFile.write(iv);
		ivOutFile.close();
                
		//file encryption
		byte[] input = new byte[64];
		int bytesRead;

		while ((bytesRead = inFile.read(input)) != -1) {
			byte[] output = cipher.update(input, 0, bytesRead);
			if (output != null)
				outFile.write(output);
		}
                
		byte[] output = cipher.doFinal();
		if (output != null)
			outFile.write(output);
		inFile.close();
		outFile.flush();
		outFile.close();
		System.out.println("File Encrypted.");
		
	}

}