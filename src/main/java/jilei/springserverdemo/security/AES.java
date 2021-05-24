package jilei.springserverdemo.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AES {

    public static void main(String[] args) {
        String input = "hello sss";

        SecretKey key = AES.GenerateAESKey(128);
        byte[] ivBytes = AES.GenerateAESIv();
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        //转成字符串
        String keyStr = AES.SecretKeyToString(key);
        String ivStr = Base64.encodeBase64String(ivBytes);

        //转成key和iv
        SecretKey newKey = AES.StringToSecretkey(keyStr);
        IvParameterSpec newIv = new IvParameterSpec(Base64.decodeBase64(ivStr));

        String cipherText = AES.AESEncrypt(input, key, iv);
        String plainText = AES.AESDecrypt(cipherText, newKey, newIv);
        System.out.println(plainText);
    }

    /**
     * 生成AES秘钥
     *
     * @param n 秘钥长度，一般设128字节
     * @return SecretKey
     */
    public static SecretKey GenerateAESKey(int n) {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert keyGenerator != null;
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    /**
     * 生成AES向量
     *
     * @return byte[] Iv
     */
    public static byte[] GenerateAESIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    /**
     * AES解密
     */
    public static String AESDecrypt(String cipherText, SecretKey key, IvParameterSpec iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.decodeBase64(cipherText));
            return new String(plainText);
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES加密
     */
    public static String AESEncrypt(String plainText, SecretKey key, IvParameterSpec iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            return Base64.encodeBase64String(cipherText);
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 密钥转字符串
     */
    public static String SecretKeyToString(SecretKey key) {
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 字符串转密钥
     */
    public static SecretKey StringToSecretkey(String keyStr) {
        byte[] encodedKey = Base64.decodeBase64(keyStr);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static String IvToString(byte[] ivBytes) {
        return Base64.encodeBase64String(ivBytes);
    }

    public static IvParameterSpec StringToIv(String iv) {
        return new IvParameterSpec(Base64.decodeBase64(iv));
    }
}
