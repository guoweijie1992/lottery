package com.hzsmk.common.util;

import java.security.MessageDigest;

/**
 * Created by YS-GZD-1495 on 2018/2/7.
 */
public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String MD5Encode(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes("UTF-8"));
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes("UTF-8"));
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 16位MD5加密
     *
     * @param s
     * @return
     */
    public final static String MD5TO16(String s) {
       return md5Password(s).substring(8,24);
    }

    public static void main(String[] args) {
//        String temp1 = "{\"ver\":\"V1.0\",\"orgNo\":\"331099101258\",\"orgName\":\"台州恩泽医疗中心（集团）恩泽医院\",\"id\":\"\",\"inPut\":[{\"AAC002\":\"33108219970207127X\"}]}64ebfa93a991ca2e9d02adb3ea51658e20190925 09:21:31mcri5m5xfwwpra8ghs31nvmpuodk1vixf16b37700d282f193dcc54fe793e4399";
//        String temp2 = "{\"ver\":\"V1.0\",\"orgNo\":\"331099101258\",\"orgName\":\"台州恩泽医疗中心（集团）恩泽医院\",\"id\":\"\",\"inPut\":[{\"AAC002\":\"33108219970207127X\"}]}64ebfa93a991ca2e9d02adb3ea51658e20190925 09:21:31mcri5m5xfwwpra8ghs31nvmpuodk1vixf16b37700d282f193dcc54fe793e4399";
//        System.out.println(temp2);
//        System.out.println(md5Password(temp1).toUpperCase());
//        System.out.println(md5Password(temp2).toUpperCase());
//        for (int i = 0; i < temp1.length(); i++) {
//            if (temp1.charAt(i) != temp2.charAt(i)) {
//                System.out.println(i + " " + temp1.charAt(i) + " " + temp2.charAt(i));
//            }
//        }

//        StringBuffer b = new StringBuffer();
//        b.append("merchant=").append("smk").append("&");
//        b.append("secret=").append("smk323536627").append("&");
//        b.append("time=").append("202103101400").append("&");
//        b.append("token").append("1D23334BAEA6EAA111A2450841273596DCC34CF2D55FF048A2FB8F11DD0D4BF540A6A9DF5B06FA73311ED68A4D4B0D43C35125B8EFF55930E9351FA2A555EFA3682D25C723100E3C").append("&");
//        b.append("type=").append("getUser");
//        String signStr = MD5Util.MD5Encode(b.toString(), "UTF-8");
//        System.out.println(signStr);


        String signStr = MD5Util.MD5Encode("merchant=society&secret=society789456325&time=20210312100702&token=asdfasdfadsfasdfasdf&type=smk", "UTF-8");
//        String signStr = MD5Util.MD5Encode("merchant=society&secret=society789456325&time=20210312100702&tokenasdfasdfadsfasdfasdf&type=smk", "UTF-8");
        System.out.println(signStr);


    }
}
