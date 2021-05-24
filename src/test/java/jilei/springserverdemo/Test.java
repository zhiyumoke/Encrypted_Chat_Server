package jilei.springserverdemo;

import jilei.springserverdemo.security.AES;

import javax.crypto.SecretKey;

public class Test {
    public static void main(String[] args) {
        SecretKey key= AES.GenerateAESKey(128);
        byte[] iv=AES.GenerateAESIv();
        String keyStr=AES.SecretKeyToString(key);
        String ivStr=AES.IvToString(iv);
        System.out.println(keyStr);
        System.out.println(ivStr);
    }
}
