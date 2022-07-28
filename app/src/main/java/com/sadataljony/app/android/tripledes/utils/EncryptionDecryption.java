package com.sadataljony.app.android.tripledes.utils;

import android.util.Log;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecryption {

    public static String encryptUsing3DES(String input, int length, String encryptionKey) {
        try {
            byte[] inputBytes = fillBytes(input, length);
            byte[] key = fillBytes(encryptionKey, 24);// only support 16 or 24
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
            byte[] encrypted = cipher.doFinal(inputBytes);
            String hexString = Hex.encodeHexString(encrypted);
            Log.e("Encrypt Result:", hexString.toUpperCase());
            return hexString.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptUsing3DES(String input, String decryptionKey) {
        try {
            byte[] key = fillBytes(decryptionKey, 24);// only support 16 or 24
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DESede"));
            byte[] encrypted = cipher.doFinal(Hex.decodeHex(input.toCharArray()));
            String hexString = new String(encrypted);
            Log.e("Decrypt Result:", hexString);
            return hexString.replace("\0", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] fillBytes(String key, int length) {
        byte[] keyBytes = new byte[length]; // a Triple DES key is a byte[24] array
        for (int i = 0; i < key.length() && i < keyBytes.length; i++) {
            keyBytes[i] = (byte) key.charAt(i);
        }
        return keyBytes;
    }

}
