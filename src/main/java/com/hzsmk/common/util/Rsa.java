package com.hzsmk.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class Rsa {

    protected static byte[] resultBytes;

    /**
     * 1024大小秘钥
     */
    public final static int keysize_1024 = 1024;

    /**
     * 2048大小密钥
     */
    public final static int keysize_2048 = 2048;

    public final static String Public_Key = "publicKey";

    public final static String Private_Key = "privateKey";

    public static final String KEY_ALGORITHM = "RSA";

    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    public static final String SIGNATURE_256 = "SHA256withRSA";

    /**
     * @param keysize keysize_1024 /keysize_2048
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> createPubKeyAndPriKey(int keysize) throws NoSuchAlgorithmException {

        // 生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        // 初始化密钥对生成器，密钥大小为1024位
        keyPairGen.initialize(keysize);

        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 得到私钥和公钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        Map<String, String> map = new HashMap<String, String>();
        map.put(Private_Key, getKeyString(privateKey));
        map.put(Public_Key, getKeyString(publicKey));
        return map;
    }

    /**
     * 得到密钥字符串（经过base64编码）
     *
     * @return
     */
    private static String getKeyString(Key key) {
        byte[] keyBytes = key.getEncoded();
        return new Base64().encodeToString(keyBytes);
    }

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = new Base64().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = new Base64().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 公钥加密
     *
     * @param publicKey 公钥
     * @param enStr     待加密字符串
     * @return Base64 加密字符串
     * @throws Exception
     */
    public static String encrypt(String publicKey, String enStr) throws Exception {
        // Cipher负责完成加密或解密工作，基于RSA
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        // 根据公钥，对Cipher对象进行初始化
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        // 加密，结果保存进resultBytes，并返回
        byte[] resultBytes = cipher.doFinal(enStr.getBytes());
        return Base64.encodeBase64String(resultBytes);
    }

    /**
     * 私钥 解密
     *
     * @param privateKey 私钥
     * @param deStr      使用Base64加密过的加密字符串
     * @return 字符串
     * @throws Exception
     */
    public static String decrypt(String privateKey, String deStr) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);

        // 根据私钥对Cipher对象进行初始化
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));

        // 解密并将结果保存进resultBytes
        byte[] decBytes = cipher.doFinal(Base64.decodeBase64(deStr));
        return new String(decBytes);
    }

    /**
     * 私钥签名
     *
     * @param privateKey
     * @param data
     * @return
     * @throws Exception
     */
    public static String sign(String privateKey, String data) throws Exception {
        PrivateKey privateK = getPrivateKey(privateKey);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data.getBytes("utf-8"));
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 私钥签名
     *
     * @param privateKey
     * @param data
     * @param type       加签类型
     * @return
     * @throws Exception
     */
    public static String sign(String privateKey, String data, String type) throws Exception {
        PrivateKey privateK = getPrivateKey(privateKey);
        Signature signature = Signature.getInstance(type);
        signature.initSign(privateK);
        signature.update(data.getBytes());
        return Base64.encodeBase64String(signature.sign());
    }

    public static String signSHA256(String privateKey, String data) throws Exception {
        PrivateKey privateK = getPrivateKey(privateKey);
        Signature signature = Signature.getInstance(SIGNATURE_256);
        signature.initSign(privateK);
        signature.update(data.getBytes());
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * 私钥签名
     *
     * @param privateKey
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] signGetByte(String privateKey, String data) throws Exception {
        PrivateKey privateK = getPrivateKey(privateKey);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data.getBytes());
        return signature.sign();
    }

    /**
     * 公钥验签
     *
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(String data, String publicKey, String sign) throws Exception {
        PublicKey publicK = getPublicKey(publicKey);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data.getBytes());
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * 公钥验签
     *
     * @param data
     * @param publicKey
     * @param sign
     * @param type      验签类型
     * @return
     * @throws Exception
     */
    public static boolean verify(String data, String publicKey, String sign, String type) throws Exception {
        PublicKey publicK = getPublicKey(publicKey);
        Signature signature = Signature.getInstance(type);
        signature.initVerify(publicK);
        signature.update(data.getBytes());
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * 公钥验签
     *
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verifySHA256(String data, String publicKey, String sign) throws Exception {
        PublicKey publicK = getPublicKey(publicKey);
        Signature signature = Signature.getInstance(SIGNATURE_256);
        signature.initVerify(publicK);
        signature.update(data.getBytes());
        return signature.verify(Base64.decodeBase64(sign));
    }


    /**
     * 公钥加密
     *
     * @param publicKey 公钥
     * @param enStr     待加密字符串
     * @return Base64 加密字符串
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public static String encryptPart(String publicKey, String enStr) throws Exception {
        // Cipher负责完成加密或解密工作，基于RSA
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        // 根据公钥，对Cipher对象进行初始化
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        // 加密，结果保存进resultBytes，并返回
        byte[] bydata = enStr.getBytes();
        int inputLen = bydata.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        int i = 0;
        for (; inputLen - offset > 0; offset = ++i * 256) {
            byte cache[];
            if (inputLen - offset > 256) {
                cache = cipher.doFinal(bydata, offset, 256);
            } else {
                cache = cipher.doFinal(bydata, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
        }
        byte[] encryData = out.toByteArray();
        out.close();
        return Base64.encodeBase64String(encryData);
    }

    public static void main(String[] args) throws Exception {
        /*Map<String, Object> params = new HashMap<>();
        String time = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDO+RYZAbjz2JFF8Xow4dgDcUgTJH7qm1Lv98L8n4jJOldM/vVsSb7BGNt7V2oCZ55jebK76SBA0Vm53jLE0ejE0P3ygOaIuESAEXya+DUFIvrhSZ9xOw7OMLnVrWON9hpFPNR0KvHe+212h3C1ZRp4i3dihz4KTX9KDGuQH6Ex1X7agVJmJiXXpTu95GxtIZvKNx5p2hRlWidM5h82EN7C3hDSy5YZfORblmjzQkqqy6S2CJkXXXcrm+xGWzS7ABClo33BMvOgsbcrbX2HR5P0PlEyeQR+MkRlitleCFyqE1owPorjuiT44aX64HGymrCUG1AeHS94dXq7DYfPa5VXAgMBAAECggEAU7fjXVgaBOBr2rvTbiRcKQBQZBXWFHWhdZllUKQDQ0oK+AUFJrkppJe2Z8yy/6gEJD6csm+Y8T79KTacmIpDuiDDJ61GErVeV+M64MBXSe2dRzVtffKiiXqiILFS3KBYffseAKNCVKyk2VXtnCH6NBpNvxfvW2F/hGdDtvdkT3BOkRY/9cgFqEn+BPCTgADCYFVFSt/KbblD/7ahYdhJqQppdneEMro6QZbBp3hMe6M6oK/bUVxyztLoN7QH3muuHUMfekvj7Jqu+UzMB6UUeX1GkBAmaAnGPzBZrMiB/9rqFrL8Uct13SAgSfb4hwoYWOSjTIf3L2upd9ae/twuoQKBgQD14SrPkh9Vj3sbWHvdk6YNZ/oeb2Z2FJIOiM3K9cUt+dgm6tMm20F+BPb/702vcji/ZFvcZCb8ku1U8rkh9LCGwCCLxtwoe0OIRZ8DlFlqBufOto652ZS+uY0AU/j28ronkBflg2FSgyhKDJQyAjWvn2vScxUmq8I7y3ZRKeMIMQKBgQDXffXrmQgVh6nRwlBJFMFiDA/O7bQWFmrF8mn/jccaeCdj1ntNFWXQWxMSKEa7EhpLnhO4aQKKmHjoK4ZXoBoXU0WTrpNXZq/sUmItsTZ0pfIG8AGFouxv7KZWJU0R1MZBgsrsNpn44ToagM2cSnU22ihY/SIrUevbWNoodyEcBwKBgFM6rOsmY0TrFJjlEsimOgc/swKqYtyFigsxc7fICb7OW8SQqHj2ruyhcZqgvoMs/tiKeC3aGPhWI9AleVVtJnP2hQ3LeqqZTvrJ1lG33axuOflXAmkWpasJ1BjQU+4Dq8/Ijs458q+jhffK2j8zUEjqhezu17IHYfArpxlCPH+BAoGBAL34RkbnRtAsI9JOWhbDHGRCO+FxflQGeSR+O2HV7BNyn08Ncu+WkIYo89wK86fRIqtM9ReaQ2pJVjVewXUYKEgt2eWCflQLq8VLL1AWrOoSrRXemYjlA0j8N/szsQiqKgjws9GfpQdaDq30pM5GeaTcl7szlN7ZvnJFlzUbKG4bAoGBAMbokREKqXEEwnsrje0Ofts+uSV+n9/h04rYz0uIz0Nljf9sGkD4csrxJMbH10GRMgoAW5J+W6v753qRvBn20rhNIRCf0bAO/Uwu8XdI5dfbnEwn6N5EVD6f3sR0hJJRhQwsojFODekOOOxOJkUf68hLUsGpCLldsuKtZe6QrcQO";
        String qrcode = "SRC5EIPLREYSOYWGZVCWJ5FZ8JEJX0K8X3VXM1Y7OFK1FIGVEZJD22TT4X057P7UDO6EISDS3XCEQPFX9LA9A4P68ZOYVKJ84J8B0P";
        params.put("merchant", "westLake");
        params.put("time", time);
        params.put("qrcode", qrcode);
        params.put("sign", signSHA256(privateKey, "merchant=westLake&qrcode=" + qrcode + "&time=" + time));
        System.out.println(JsonUtil.toJsonString(params));
        String result = HttpUtil.postJson("http://115.236.162.166:18081/smk_hztalent/in/query/hzTalent/qrcode", params);
        System.out.println(result);*/

//        Map<String, Object> params = new HashMap<>();
//        long time = System.currentTimeMillis();
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCClLtFYNecebqIt/hGjV+6j/eL2c43ZUOgmYmzlKBQJGyY8CY43gICycNd76UscrdGEo7trju+Amrnb4DxyGKboVxR/7zoNQtiQ4oZZWiLz3HLBws59EaFXVkQcMCZllgNlOiKH4EcB19iVVSdWFbsOGe2R3NxNLJ+zZvNlWo73KrBAzHuiYifjyCG7bwZGm7NnlgzWJqr5dZ00BJoUDg2bIzBoIwp5ykvQihhOMSxLy5ybnFEjfFIoFMX0aZkqOX8rp/IjqzWb6hrPDNjV7KhIE/dCfXoKfr+LgFLW4MnU84E3dpSZYTkJnXPdjwFAWOiwlhn5nSIAsh9vL5JVQprAgMBAAECggEAc6hGorGe52JOHJBN4DeRxY2EZaM56ojPuGylsaB/ydwpL+DtCcthnpSUbRw4gUDuIauIWu7KzYYYp/p0DDM/W7c1LQ/FgyqQda16oPnX+pV3pasMf31kBPXkUBJ+FMs8HHN67/PtiCTjkBYiRucavklLWwC4OFnDi1qTmCiyGmgZtPUDQAMYw7cczRVsEikvJzdAMccLLW7gGe0+ywaEsTGufOrzBdafEFKd/luldy3ETgouJjp/4NZhhnE2om4+8M+6Zea7narNXuLkBAnjWCnLHQnDtauIz9z+JjoY87JyYxH2cwzPg09zxOLjhUwkmLtzECPbIw7EUrwJ2dNBqQKBgQC/9g9eiDEWM79yTHQ3dFLvL4g1jcVqhvifVFphc1QuCsz2hU2TLOzDp1ibOoGe0c2Xa7smjYSSSIYn/LE5ofCFRhXZv5mJrA7k2QI3kIE9OQvOBi5gkTzLsUx59S3zW4ACiYvKpYNbRroCqvVcZyHqsPU/L6O1FAGKxybExhKDVQKBgQCuJKhUqujekKDYn3xAdYgPfqhPbHGk5fYxM0kR41MbhqBpuHzMpPXk6npLGpkEoi8ZgNx9xTHoy9TGwbFm0lZb6QiTfJfK9Hl0480Zp5UtRvaRw+za2f0Cy4e0ppIeSpIjuTiJeOhp7Jm3RkqnCGK4laUgDHDvU5KQn4Xw4MzWvwKBgQCiUDYIsdIHuvPbesgeB88EjkV3Oww9mijp2FKf6fu4/sr2fBrvImKEwaDLiLoHh9F2gtJ5s9nwM0C02Rcl60dL+coKF9eEsKbbXJ9iSl7tXKodp95ZKxWkCqPmz6juCGWCP7mjPTaH3JL2JIZZfp8QKlcE+YxMVcFEDkc6xVzsDQKBgB4bFEbe7TddBZGtsMtsBkO3f92LP/JkFzXWSfGfvURq99yPGnQ+qIwUxWOqi32zlDTyhCvyJXi7lGNwJP1Dq85j8SmeRpe9wCmnIFQpFq8nL4Vvu8jyGr2a9jETAvvdpHoskUui7XsyfjijHFDYMEj9BfkObY/SgOojygIkuwT9AoGAbZaMEzI1fAxfIFQmqukWRL9y1d3/kWV64eFXj8So0bdITbRrPexl1jXUs4TdrPMt4G932xdKsz6g0NLgOKk+OiK3ZWWW5nAto35S782HgiyOdtOjZ7L5vfE1d8Yra4lJ2sOdWDJqjL4ns9qLAoxjzp3mx3+Geyw/QDzNItjPXQM=\n";
//        params.put("merchant", "zdwx");
//        params.put("time", time);
//        params.put("day","20200531");
//        params.put("sign", signSHA256(privateKey, "merchant=zdwx&time=" + time));
//        System.out.println(JsonUtil.toJsonString(params));
//        String result = HttpUtil.postJson("https://smkmp.96225.com/smk_hztalent/in/query/hzTalent/getUsageData", params);
//        System.out.println(result);

//        Map<String, Object> params = new HashMap<>();
//        long time = System.currentTimeMillis();
//        String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC62DQmxezc9l6qzRBe97Xa1ub3VIWA2L4Ep6vTzJB9sV92JzK6B6lEy8793HHxi08IdCgKa3MQCdJKqmSiYAnM8RgV4HEf6ttH1e9HzFCdcGeSiq6eMkePD9NI6I6xEBFQqlJ/hjn1wgqoMIiop1yvKg4T4/YObxR6LvQlnyF9S0t7iWzRLIYOWdITkPkEAlK7S8PCdgQSd7lDQzc4hkhDrGwenwRSIciGzVMQwLmLpdGTcF0od4k2/UQQioPlCcg1mBtUENOvXJzPEqA3+iOgx2LVGbcTEoPjcntMoYzs8AUGUFGCgWaKPYPHRlH9fbM02OShAwsVod8QFiADDwr3AgMBAAECggEBAK9q9qOEZVoacEyZVAplNDYuVw/UKbcLRnMmXDrwLqPjWEWcjZJ8SN4PHtnWEp/884lzTHhj4tYm8mkpSmvsEKd5nuxDGNIndBIgd5MH7pOxiXvaehCKEgbti6dCt1zjQe5iyityAI6Ly+eWEWeOIYVOzYK9HjD09tuzo4KCwTVYUzvzNEz8Wit6bMb++OUjB0DRSTPpLQLTs5TidH65CQFxGClUEZjVjts/tVIV9XjFntg/YINb8FhgrLmZ0jh4BCJr2LrQ1+aadonqpjwD9vP2FrenRyOnjwxBRqLKtjMwmTyZzl3sTSoQ1qaguBWP5WYMw2O7yV8dys6ymN+KuAkCgYEA9T0ZCmbGkTAo2VYKhSbaAwFP/bqDaQB6QrIFupzAB0ZiCFUM/oVQAVEeC1dNwd+ZYQCPkoSo+O+L8MFCeseDwlMtlwTD3D41Cksu8EZrN98XYBv3ob96DCaWreAfp6l5WN7oV6I1NAFJslVL2MtRkDg9HUTzHNGgNuYRcFiWxfUCgYEAwwsh4FQJSTNdjUTuG2iClU0lsSGfFMs/eFIZIScZfRRddawWT31UbGzjzk4pY235wpOK+abr3CFgfKT+EYkGcEls3wJLC6gdeD5LKWkjblFZn+aWkMCQYXRBDEL/2cOQ+4rR/2Twsj14VwEFCMlsmg5gv/sRk/FWidXPNycDDbsCgYEAoT75/vfSqdYDldtX8XK5N+6d/NOs4dZ5jO7mi2vTazUg8h+R+qwHDcabPZO6wbXWcIxg2lK1k3BWEAfSQU572MK+ICT27xJL56fFhiCXENZz1TKuYMC4a9LXkh3iUmhBLGjsVeqGltod7c7dnv8Ycv43WM5kF1fRwycE4lytTh0CgYBbtT1NfFYNbDB9vz5x9rcHuomReuyQ22xRh9C3DOpoLI4xSSQIZjau8JhApfHCBbDdM4CyGDFohO0YToAEALjdTTj6ttpQddC9fBTdOMlnEqsgi0yabWC14fK/8DTa0KC4FbLd8jlWpZ6S0jDqy8LbwQzu1/+Q33ZAQx82oIpIBwKBgQCJeYRHaOtDjboBIwXyQmjBdTpbK4I6DXkq1e6bBUHGeFJnBjmZacBXlSwCj3+xF7qeMXWOg0CFaIoogSYqS57jfKJD5bUUpEqLmq2S/JzCbUvMIcQhd5tWoXoE30898LWSZogY/E2mSkOMojhvYp/vq0NbOm/t/lYh2R2AHbNang==";
//        params.put("merchant", "zhubao");
//        params.put("time", time);
//        params.put("mobile", "13156709825");
//        params.put("sign", signSHA256(privateKey, "merchant=zhubao&time=" + time+"&mobile=13156709825"));
//        System.out.println(JsonUtil.toJsonString(params));
//        String result = HttpUtil.postJson("https://smkmp.96225.com/smk_hztalent/in/query/hztalent/sendMessage", params);
//        System.out.println(result);


//        Map<String, String> pubKeyAndPriKey = createPubKeyAndPriKey(1024);
//        System.out.println(pubKeyAndPriKey.get("privateKey"));
//        System.out.println(pubKeyAndPriKey.get("publicKey"));

//        Map<String, Object> params = new HashMap<>();
//        long time = System.currentTimeMillis();
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDVEzOLL3Ovu7raAPpyv59tZciQJ3h2Ga3060aGcdvIVLxorf9H7Pcgnis6yi0/gw2CVdo2xeqx06XPU9CV8ZI/KiZBQPbrD8xE48uZfhEGAukPVEImV20UwXPyM1AMgNhW9AScdOPvVgX0rYsbBgOPcNdyNAy1lbjSyqkkhLRGOnlcy+28xot/eB11C7bXY5UCd/BxMbSu9uhO2V8F2nucRpc7/1Ny8JnwZsV5DekTx8+VZVPRmJ8nRozaOUZJ0VH4Eofz7ODqHpTBzzZgwZGPf9TvnvayqaZS3oS87E44r3qd5l2rwioWc9y9yFly72J5QhRBdNoQRz0K4M6T0k4ZAgMBAAECggEAc/U5LdwrJ04RSCcUJXKnfs/PsLPqWMts4kdQYEaCh6mlx4o5zZjmfPfXKu1wZooZxf6W/QwEx7YZHJXOhGutXXbAzu5d8lrsnekimmwDM7RNxavjPB6EHLGTOhI21DcihV/TSppKmavFHf18IL7LHTsZ5Oryp7fNl9n25wrZWQXnSlfYmy7jsBHInIL91B6pMNSzEGSVN12vg+MFELWlJOxfMIhWXPHAFwMqYLrm9Jx5EGskE0nwIvJ5x2tjbV/9vKz7lqE48HPD8wcwecBVe+iQ7CGTMcluNa2wKHlCfR/fGf/w/IbBgEn3KnWTFWLjA8F856lRlaZ50AiRJxMKCQKBgQDwUdXv/fiePa1aqx3o1VVSgUBYIT7M3TbcGbXG749XvoqLh95GJZ0li9mmQLXHeXUunt1ZvYC9TC5o+5i6tXCvfpA1Io6+rWJZG1UV1w95PuA1NSzlNVOFWYF+UlGQS1GP5vWm6AikYPbcmUI0ynpSesxtgDwvJ7p3QrJMRWcUowKBgQDi+kk+6VfVO8fp5AtU03gJDtzemldTDfqgM1c87qOHA87+ec51eFWAA+Mk2SFIEIAm74Y1Jd19ExN0fYh6v+SU47+P0RbRtKtvLjawX1ANk0opBLeGTdvOb9bcT5PQ0PDJEQNTaEwE4EHCe69KAQBsAe4S/H5EnoEXVNVPcpuCEwKBgEKtAeVnGn6U8C7ywQajgdu1Pqv09V6Tla/1diiuuJ+InxndZX8i2nnbVlS5KXYhrbB0pUdLu5dJtqqr+5D53DzkqZAEeYRPyLSeT7oShBmIp1471av2Yqjhz3rDzhtxujxpwQhA4nzw5i0IXIUXFF8j8OoEpS4UILkMCTn+G35vAoGBAI/Yyx7Bx1ZuIo5KZhwGQSaMctHowrryOwnYP8tv+dcRRNfl3kB4WMder0+lBACl5HHIjyeTrcEcm9HR1PLmNjld9QNyIaIZfCRkB2jTnupblD5WrPDC2zEDlAnfrPBD2rWywIbVrIfSkBpvRHIEHH5W4sjJmERop6tfqihFm+RdAoGATlgWeNGrvgRAey11/sgWRkTQDz4ZeiGJ2gfq3CgHL6tYcyrpUmIBV/el7pB+jEqFVhu1H4SP3VsFx3ClCvf7lu0Wn48+TcVPqhl5DpNKkGMCWpZGO8M9J1uim99ZZC0AWFbVGt+a8MBUHfPjM4VnggbdsLxYZeLcPFVhe8nSnMo=";
//        params.put("merchant", "zheliban");
//        params.put("time", time);
//        params.put("idcard", "341623199601129019-1");
//        params.put("sign", signSHA256(privateKey, "merchant=zheliban&time=" + time + "&idcard=" + "341623199601129019-1"));
//        System.out.println(JsonUtil.toJsonString(params));
//        String result = HttpUtil.postJson("https://smkmp.96225.com/smk_hztalent/in/query/hzTalent/talentUser", params);
//        System.out.println(result);

        Map<String, Object> params = new HashMap<>();
        long time = System.currentTimeMillis();
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDIAEx/WF+3b6jT7zGJ2FG7r98nShcjLy3W07MaThii4cM6/C/QAxKnlvlGSLicDuYuYX6nEZY+haFOVJDCpCUCyfWwRm8ORXkm4NnzMRWKI1cHV9EzL6VwK/p//OkKNdhGi9bl9jfoU/g/vaCIqYpDA6Vrrh4v12gcZvk4sotygRMKZuXscUjLxpTHGFQpdULLk0DimeZsSsuVsHb3EQPGq2k9vL+Nv6EQz5x3kow+P6T5QAhg1Abh0qdPKU0/vdu09r6TvSfY5638T7vFwdYgG8t+bYcwWFe/ebjQpziyHYsFoFUBcLFAOrMVTR0PhK7y+ne11tvr3gW9K8H8PCcZAgMBAAECggEAJ8B3c/A6xhj5c3ZdYEqQpD9mmUaOpr2f3M0jEh1npCg/R6AMWMnjkU/ep+uYVdxYP+u84rQKKs8gqpMAWs+JU66UmL7HIA0K8YFLkJy27Ufs6la4C/ZyaYM0PPAl2ZOuMwdkZTy1DAZ0lya3m7Im+v7kiCyJb3TfCpYk/vHt6YoHuKrekljaRQw/BmttL4FH9QSNoW4DQ3gYsZ0wk8oaVEAqVcN83SrCuxHARkvsYsjGoYtck8rdYZzx0FQKunlL8JqB+rH+qL28hRKEBEQ3OFvzfIv+DdZNbzpe3SPp1HQhCWHsUsfDvuSx7wkm60QS/wRx5p71W0DVGCNuu1l9AQKBgQD6R2sJPKUP8sC67iFPKCOprhsiez6k3M437C4ySuHAFFJoY5oBcPN+Ae2EL7dCfyLAqPgbK0Wd6LifBDX7Wi5flBq/jSYzFT7rsD1YVfcEfJW3RDRL36yfO225v3vHG/46kFjA7WVupkBa2u6k6Z+n5jMdrz/cSIhY7s2Vy776SQKBgQDMkqpHGnxNA4CkOJZpxHbAus0V6foyKahIKTdi9VHZ9OINRjD/lVs2eohpVCfNrtE9QbzmHIeCKKfSBoWEeTF+/+mBiY7LC9E4KPODBfnWxswqJoCPbu3tJ+DMGMegbpTPWPXd9bKxgpUJbLzsK89xgihNMOfP6pe92Imq0WdGUQKBgBLoSE7HVRTyWtsEWGHZf3A3Jfeh5eagg7e30PmdrJZNMriOJblMJijzzlS5IhgU8LQrT6sFxtmaFjB1KfrWIkC4OJkV+bfMOBeoFcpRnDqrisfwkB7XQ/w3ih+JNrmhBMC53OqeRJZqWtxCQ9sThysw+dWZQrLKi/pj+AYEAWZ5AoGBAIMj0fxawiwsfp7Hl8SdqPLLzQSrRTQ6+CGqKlNJp/rsvKx9vpNZD2vRCPlcdVepJYD7TzjEOSn7HfnJLHZLqqsbKnlb0SngnXI+7juj7RD9x4uNuwQ3vFY3JigwCmiR+tsg0BxFkROx/hBYoJNVUloVcfhpzXC/lh6hXT8flfDBAoGAJvJ6qrGR4UlknVmC94hBPQGSqBxGIPSOlurYglFtTp7unIfHi9P0acNjUFyJdFyZpV3D0ZFSSDGlwf3LRb31v56jPXJNAKlpgb5uzKaTDbs4QYDLKmyrr4FlKT9DTH9I00BIC2ZA1fWb7jX5SgyDx2fZVZQ7Mgcs0d4jgJ8nUrE=";
        params.put("merchant", "zzb");
        params.put("time", time);
        params.put("startMonth", "202001");
        params.put("endMonth", "202101");
        params.put("sign", signSHA256(privateKey, "merchant=zzb&time=" + time + "&startMonth=202001&endMonth=202101"));
        System.out.println(JsonUtil.toJsonString(params));
        String result = HttpUtil.postJson("http://localhost:8090/smk_hztalent/in/query/hzTalent/talentMonthPv", params);
        System.out.println(result);
    }
}
