package com.hzsmk.common.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * AES/CBC/PKCS7Padding 工具类
 *
 * @author jiangjh
 */
@Slf4j
public class AESCbcPkcs7Util {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CHAR_SET = "UTF-8";

    /**
     * 算法/模式/补码方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @param iv       偏移量
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password, String iv) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("AES encryption password can not be null or empty !");
        }
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec spec = new SecretKeySpec(password.getBytes(CHAR_SET), KEY_ALGORITHM);
            //初始化为加密密码器
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, spec, ivspec);
            byte[] byteContent = content.getBytes(CHAR_SET);
            byte[] encryptByte = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(encryptByte);
        } catch (Exception e) {
            log.error("AES encryption operation has exception,content:{},password:{},exception:{}", content, password, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * AES解密操作
     *
     * @param encryptContent 加密的密文
     * @param password       解密的密钥
     * @return
     */
    public static String decrypt(String encryptContent, String password, String iv) {
        if (StringUtils.isBlank(encryptContent)) {
            return encryptContent;
        }
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("AES encryption password can not be null or empty !");
        }
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec spec = new SecretKeySpec(password.getBytes(CHAR_SET), KEY_ALGORITHM);
            //设置为解密模式
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, spec, ivspec);
            //执行解密操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptContent));
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            log.error("AES decryption operation has exception,content:{},password:{},exception:{}", encryptContent, password, e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String content = "{\"thirdUserId\":\"第三方应用用户ID\",\"name\":\"真实姓名\",\"phone\":\"手机号\",\"idCard\":\"证件号\",\"userLevel\":\"用户等级\"}";
        String pass = "18c176f0206d1ef2";
        String iv = "55d5ab6273554fdd";
        String result = encrypt(content, pass, iv);
        System.out.println(result);
        String r2 = decrypt(result, pass, iv);
        System.out.println(r2);
    }
}
