package main;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
   public static void main(String[]args){
   Scanner in=new Scanner(System.in);
   }
   public static byte[] encrypt(String content, String password) {  
     try { 
             KeyGenerator kgen = KeyGenerator.getInstance("AES");  
             kgen.init(128, new SecureRandom(password.getBytes()));  
             SecretKey secretKey = kgen.generateKey();  
             byte[] enCodeFormat = secretKey.getEncoded();  
             SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
             Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
             byte[] byteContent = content.getBytes("utf-8");  
             cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
             byte[] result = cipher.doFinal(byteContent);  
             return result; // 加密   
     } catch (NoSuchAlgorithmException e) {  
             e.printStackTrace();  
     } catch (NoSuchPaddingException e) {  
             e.printStackTrace();  
     } catch (InvalidKeyException e) {  
             e.printStackTrace();  
     } catch (UnsupportedEncodingException e) {  
             e.printStackTrace();  
     } catch (IllegalBlockSizeException e) {  
             e.printStackTrace();  
     } catch (BadPaddingException e) {  
             e.printStackTrace();  
     }  
     return null;  
}  
}
