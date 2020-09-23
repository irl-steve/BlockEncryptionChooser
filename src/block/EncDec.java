package block;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

//
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncDec {
	private static Cipher encryptCipher;
	private static Cipher decryptCipher;
	private static final byte[] iv = { 11, 22, 33, 44, 99, 88, 77, 66, 55, 12, 34, 56, 78, 90, 19, 28 };

	public static void main(String[] args) {
		String clearTextFile = "C:\\Users\\Stephen\\Documents\\CS616\\source.txt";
		String cipherTextFile = "C:\\Users\\Stephen\\Documents\\CS616\\cipher.txt";
		String clearTextNewFile = "C:\\Users\\Stephen\\Documents\\CS616\\source-new.txt";
		
		boolean isInvalid;
	    String alg = "DES";
	    Scanner scan = new Scanner(System.in);
	    do {
	      isInvalid = false;
	      System.out.println("Welcome please choose the algorithm to use:\n 1. Advanced Encryption Standard (AES)\n 2. Data Encryption Standard (DES)\n 3. Triple DES (DESede)\n 4. Rivest–Shamir–Adleman (RSA)" );
	      int algChoice = scan.nextInt();
	      switch(algChoice){
	        case 1:
	          alg = "AES";
	          break;
	        case 2:  
	          alg = "DES";
	          break;
	        case 3:  
	          alg = "DESede";
	          break;
	        case 4:  
	          alg = "RSA";
	          break;
	        default: 
	          System.out.println("Please enter a valid choice");
	          isInvalid = true;
	      }
	    } while (isInvalid);

	    String mode = "ECB";
	    String pad = "NoPadding";
	    do {
	      isInvalid = false;
	      System.out.println("Please enter your choice of mode \n 1. CBC \n 2.ECB \n");
	      int modeChoice = scan.nextInt();
	      switch(modeChoice){
	        case 1:
	          mode = "CBC";
	          break;
	        case 2:  
	          mode = "ECB";
	          break;
	        default: 
	          System.out.println("Please enter a valid choice");
	          isInvalid = true;
	      }
	    } while (isInvalid);

	    do {
	      isInvalid = false;
	      System.out.println("Please enter your choice of padding\n 1. NoPadding\n 2. PKCS5Padding\n");
	      int padChoice = scan.nextInt();
	      switch(padChoice){
	        case 1:
	          pad = "NoPadding";
	          break;
	        case 2:  
	          pad = "PKCS5Padding";
	          break;
	        default: 
	          System.out.println("Please enter a valid choice");
	          isInvalid = true;
	      }
	    } while (isInvalid);
	    

	    String cipherChoice = alg + "/" + mode + "/" + pad;

		try {
			// create SecretKey using KeyGenerator
			SecretKey key = KeyGenerator.getInstance(alg).generateKey();
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
			
					
			// get Cipher instance and initiate in encrypt mode
			encryptCipher = Cipher.getInstance(cipherChoice);
			if (mode == "ECB") {
				encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			} else {
				encryptCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			}
			// get Cipher instance and initiate in decrypt mode
			decryptCipher = Cipher.getInstance(cipherChoice);
			if (mode == "ECB") {
				decryptCipher.init(Cipher.DECRYPT_MODE, key);
			} else {
				decryptCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			}
			// method to encrypt clear text file to encrypted file
			encrypt(new FileInputStream(clearTextFile), new FileOutputStream(cipherTextFile));

			// method to decrypt encrypted file to clear text file
			decrypt(new FileInputStream(cipherTextFile), new FileOutputStream(clearTextNewFile));
			System.out.println("DONE");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IOException e) {
			e.printStackTrace();
		}

	}

	private static void encrypt(InputStream is, OutputStream os) throws IOException {

		// create CipherOutputStream to encrypt the data using encryptCipher
		os = new CipherOutputStream(os, encryptCipher);
		writeData(is, os);
	}

	private static void decrypt(InputStream is, OutputStream os) throws IOException {

		// create CipherOutputStream to decrypt the data using decryptCipher
		is = new CipherInputStream(is, decryptCipher);
		writeData(is, os);
	}

	// utility method to read data from input stream and write to output stream
	private static void writeData(InputStream is, OutputStream os) throws IOException {
		byte[] buf = new byte[1024];
		int numRead = 0;
		// read and write operation
		while ((numRead = is.read(buf)) >= 0) {
			os.write(buf, 0, numRead);
		}
		os.close();
		is.close();
	}

}
