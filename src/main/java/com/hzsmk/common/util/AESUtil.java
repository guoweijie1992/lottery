package com.hzsmk.common.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

/**
 * AES工具类
 *
 * @author jiangjh
 */
@Slf4j
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CHAR_SET = "UTF-8";

    /**
     * 算法/模式/补码方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
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
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            byte[] byteContent = content.getBytes(CHAR_SET);
            byte[] encryptByte = cipher.doFinal(byteContent);
            return StringUtil.parseByte2HexStr(encryptByte);
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
    public static String decrypt(String encryptContent, String password) {
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
            cipher.init(Cipher.DECRYPT_MODE, spec);
            //执行解密操作
            byte[] result = cipher.doFinal(StringUtil.parseHexStr2Byte(encryptContent));
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            log.error("AES decryption operation has exception,content:{},password:{},exception:{}", encryptContent, password, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析参数
     * 先解密,而后转化为map
     *
     * @param param
     * @param password
     * @return
     */
    public static Map parse(String param, String password) {
        try {
            param = decrypt(param, password);
            return JsonUtil.parse(param, Map.class);
        } catch (Exception e) {
            log.error("parse param error, param:{}", param);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析参数
     * 先解密而后转化为对应类型
     *
     * @param param
     * @param password
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parse(String param, String password, Class<T> clazz) {
        try {
            param = decrypt(param, password);
            return JsonUtil.parse(param, clazz);
        } catch (Exception e) {
            log.error("parse param error, param:{},clazz:{}", param, clazz.getName());
            throw new RuntimeException(e);
        }
    }
}
