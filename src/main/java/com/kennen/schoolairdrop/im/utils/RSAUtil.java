package com.kennen.schoolairdrop.im.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author kennen
 * @date 2021/2/24 12:04
 */
public class RSAUtil {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 获取cipher对象
     */
    public static final String CIPHER_INSTANCE = "RSA/ECB/PKCS1PADDING";

    /**
     * 公钥加密
     *
     * @param publicK       公钥
     * @param textToEncrypt 需要加密的原文
     * @return 密文
     */
    public static String encryptWithPublicKey(String publicK, String textToEncrypt) {
        String encoded = "";
        byte[] encrypted;
        try {
            PublicKey pubKey = getFromString(publicK);
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            encrypted = cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8));
            encoded = Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }

    /**
     * 去除公钥中头部和尾部
     */
    public static PublicKey getFromString(String keyStr) {
        try {
            String pubKeyDER = keyStr.replace("-----BEGIN PUBLIC KEY-----", "");
            pubKeyDER = pubKeyDER.replace("-----END PUBLIC KEY-----", "");

            byte[] encoded = Base64.decodeBase64(pubKeyDER);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);

            return kf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
