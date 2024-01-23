package com.example.springboot.http.common;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

public class AES {

    private static final String KEY_ALGORITHM            = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";//默认加密模式 AES/ECB/PKCS5Padding

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            Cipher cipher   = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM));
            byte[] doFinal = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(doFinal));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        try {
            Cipher cipher   = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM));
            byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(doFinal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static class PKCS5Padding {
        private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认加密模式 AES/ECB/PKCS5Padding


        /**
         * AES 加密操作
         *
         * @param content 待加密内容
         * @param key     加密密码
         * @return 返回Base64转码后的加密数据
         */
        public static String encrypt(String content, String key) {
            if (StringUtils.isBlank(content)) {
                return null;
            }
            try {
                Cipher cipher   = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
                byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
                cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM));
                byte[] doFinal = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
                return new String(Base64.getEncoder().encode(doFinal));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        /**
         * AES 解密操作
         *
         * @param content
         * @param key
         * @return
         */
        public static String decrypt(String content, String key) {
            try {
                Cipher cipher   = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
                byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
                cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM));
                byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(content));
                return new String(doFinal);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

    }
}
