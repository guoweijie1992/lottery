package com.hzsmk.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 调用旅游卡接口,AES加密util
 *
 * @author jiangjh
 * @date 2020/9/21 10:37
 */
@Slf4j
public class RAESOperator {

    /**
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private String ivParameter = "1234567ab2345678";

    private static RAESOperator instance = null;

    private RAESOperator() {

    }

    public static RAESOperator getInstance() {
        if (instance == null) {
            instance = new RAESOperator();
        }
        return instance;
    }


    /**
     * 加密
     *
     * @param sSrc
     * @param sKey
     * @return
     */
    public String Encrypt(String sSrc, String sKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            //return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。 JDK 不推荐使用
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            log.error("Encrypt2 have exception:", ex);
            return null;
        }
    }


    /**
     * 加密
     *
     * @param content
     * @param password
     * @return
     */
    public byte[] EncryptByKey(String content, String password) {
        try {
            // 这里是128bit密钥，所以是 16 byte,如果是其它长度的 除以8 ，修改，这里简单演示一下
            byte[] newkey = new byte[16];
            for (int i = 0; i < newkey.length && i < password.getBytes().length; i++) {
                newkey[i] = password.getBytes()[i];
            }
            SecretKeySpec key = new SecretKeySpec(newkey, "AES");
            // 创建密码器，AES默认是/ECB/PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(content.getBytes("utf-8"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密成16进制字符串
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public String encryptToHex(String content, String password) {
        return parseByte2HexStr(EncryptByKey(content, password));
    }

    /**
     * 解密
     *
     * @param sSrc
     * @param sKey
     * @return
     */
    public String decrypt(String sSrc, String sKey) {
        return decrypt(sSrc, sKey, ivParameter);
    }

    /**
     * 解密
     *
     * @param sSrc 密文
     * @param key  密钥
     * @param iv   偏移量
     * @return
     */
    public String decrypt(String sSrc, String key, String iv) {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);
            // byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密 JDK 不推荐使用
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            log.error("Decrypt2 have exception:", ex);
            return null;
        }
    }


    /**
     * 解密
     *
     * @param sSrc
     * @param sKey
     * @param ivStr
     * @return
     */
    public String decryptForWeixin(String sSrc, String sKey, String ivStr) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(Base64.decodeBase64(sKey), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivStr));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decodeBase64(sSrc);
            // byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密 JDK 不推荐使用
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            log.error("Decrypt2 have exception:", ex);
            return null;
        }
    }

    public String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
