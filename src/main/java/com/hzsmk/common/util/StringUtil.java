package com.hzsmk.common.util;

import com.hzsmk.common.base.Consts;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * string工具类
 *
 * @author jiangjh
 * @date 2019/11/22 10:39
 */
public final class StringUtil {

    /**
     * 返回不带 - 的UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 密码加密, md5
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        if (Objects.isNull(password)) {
            return null;
        }
        try {
            return DigestUtils.md5DigestAsHex(password.getBytes(Consts.DEFAULT_CHARSET));
        } catch (Exception e) {
            throw new RuntimeException("Encrypt password error");
        }
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = buf.length; i < j; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (StringUtils.isBlank(hexStr)) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0, j = hexStr.length(); i < j / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i << 1, (i << 1) + 1), 16);
            int low = Integer.parseInt(hexStr.substring((i << 1) + 1, (i << 1) + 2), 16);
            result[i] = (byte) ((high << 4) + low);
        }
        return result;
    }

    /**
     * 格式化jsonString
     * 对于有的对象如文件对象等, 使用o.toString.对象地址
     *
     * @param o
     * @return
     */
    public static String toJsonString(Object o) {
        try {
            return JsonUtil.toJsonString(o);
        } catch (Exception e) {
            return o.toString();
        }
    }

    /**
     * 进行证件号脱敏
     * 保留前后1位
     *
     * @param cardNo
     * @return
     */
    public static String encryCardNo(String cardNo) {
        if (StringUtils.isNotBlank(cardNo)) {
            if (cardNo.length() <= 2) {
                return cardNo;
            } else {
                // 前后各保存1位
                StringBuilder sb = new StringBuilder();
                sb.append(cardNo.charAt(0));
                for (int i = 0; i < cardNo.length() - 2; i++) {
                    sb.append("*");
                }
                sb.append(cardNo.charAt(cardNo.length() - 1));
                return sb.toString();
            }
        } else {
            return cardNo;
        }
    }

    /**
     * 加密
     *
     * @param privateKey
     * @param content
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String privateKey, String content)
            throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        byte[] data = content.getBytes("UTF-8");
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        int MAX_ENCRYPT_BLOCK = 117;

        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        //Base64加密
        return Base64.encodeBase64String(encryptedData);
    }

    /**
     * 替换str中 '< img' 变成 '<img'
     *
     * @param str
     * @return
     */
    public static String replaceImg(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.replaceAll("< img", "<img");
        } else {
            return str;
        }
    }

    /**
     * 进制转换
     *
     * @param num
     * @param fromRadix
     * @param toRadix
     * @return
     */
    public static String changeRadix(String num, int fromRadix, int toRadix) {
        return new BigInteger(num, fromRadix).toString(toRadix).toUpperCase();
    }

    /**
     * 截取str(如果str长度小于length,全部返回)
     *
     * @param str
     * @param length
     * @return
     */
    public static String subStr(String str, int length) {
        if (StringUtils.isNotBlank(str)) {
            if (str.length() > length) {
                return str.substring(0, length);
            } else {
                return str;
            }
        } else {
            return str;
        }
    }

    /**
     * 微信过滤表情
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            source = emojiMatcher.replaceAll("");
            return source;
        }
        return source;
    }

}
