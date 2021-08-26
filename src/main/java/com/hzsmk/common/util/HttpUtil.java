package com.hzsmk.common.util;

import com.hzsmk.common.base.Consts;
import com.hzsmk.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author jiangjh
 */
@Slf4j
public class HttpUtil {

    /**
     * restTemplate配置,可以访问https
     */
    private static RestTemplate restTemplate = new RestTemplate(generateHttpsRequestFactory());

    /**
     * postJson时, 默认的Content-Type为application/json
     */
    private static Map<String, String> DEFAULT_HEADER = new HashMap<>(8);

    static {
        DEFAULT_HEADER.put("Content-Type", "application/json");
    }

    private static HttpComponentsClientHttpRequestFactory generateHttpsRequestFactory() {
        try {
            TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory connectionSocketFactory =
                    new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
            CloseableHttpClient httpClient = httpClientBuilder.build();
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setHttpClient(httpClient);
            factory.setConnectTimeout(10 * 1000);
            factory.setReadTimeout(30 * 1000);
            return factory;
        } catch (Exception e) {
            log.error("创建HttpsRestTemplate失败", e);
            throw new RuntimeException("创建HttpsRestTemplate失败", e);
        }
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra.getRequest();
    }



    /**
     * 获取ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (StringUtils.isBlank(ipAddresses) || Consts.UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipAddresses) || Consts.UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipAddresses) || Consts.UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ipAddresses) || Consts.UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (StringUtils.isNotBlank(ipAddresses)) {
            ip = ipAddresses.split(",")[0];
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (StringUtils.isBlank(ip) || Consts.UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 发送普通post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, Object> params) {
        return postHttplientJsonHeader(url, params, null);
    }

    /**
     * 发送普通get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, Map<String, Object> params) {
        return getHttplient(url, params, null);
    }

    /**
     * 发送application/json请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String postJson(String url, Map<String, Object> params) {
        Map<String, String> header = new HashMap<>(16);
        header.put("Content-Type", "application/json");
        return postHttplientJsonHeader(url, params, header);
    }

    /**
     * 发送post请求,自定义header
     *
     * @param url
     * @param params
     * @param header
     * @return
     */
    public static String postHttplientJsonHeader(String url, Map<String, Object> params, Map<String, String> header) {
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(header)) {
            // 填充header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> entry = new HttpEntity<>(params, headers);
        log.info("流水号:{},调用外部接口,url:{},参数:{},header:{}", TLocalHelper.getSeq(), url, JsonUtil.toJsonString(params), header);
        ResponseEntity<String> result = restTemplate.postForEntity(url, entry, String.class);
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), result.getBody());
        return result.getBody();
    }

    public static String postHttplientStringHeader(String url, String info, Map<String, String> header) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
        if (!CollectionUtils.isEmpty(header)) {
            // 填充header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
// 中文乱码，主要是 StringHttpMessageConverter的默认编码为ISO导致的
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter converter : list) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(Charset.forName("UTF-8"));
                break;
            }
        }
        HttpEntity<String> entry = new HttpEntity<>(info, headers);
        log.info("流水号:{},调用外部接口,url:{},参数:{},header:{}", TLocalHelper.getSeq(), url, info, header);
        ResponseEntity<String> result = restTemplate.postForEntity(url, entry, String.class);
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), result.getBody());
        return result.getBody();
    }


    public static String postHttplientMultiValueHeader(String urlName,String url, Map<String, String> params, Map<String, String> header) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        if (!CollectionUtils.isEmpty(header)) {
            // 填充header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<MultiValueMap<String, String>> entry = new HttpEntity<MultiValueMap<String, String>>( popHeaders(params), headers);
        log.info("流水号:{},调用外部接口,接口名称:{},url:{},参数:{},header:{}", TLocalHelper.getSeq(), urlName,url, JsonUtil.toJsonString(params), header);
        ResponseEntity<String> result = restTemplate.postForEntity(url, entry, String.class);
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), result.getBody());
        return result.getBody();
    }

    protected static MultiValueMap<String, String> popHeaders( Map<String, String> params) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            map.add(entry.getKey(),entry.getValue());
        }
        return map;
    }


    /**
     * 发送get请求
     *
     * @param url
     * @param params
     * @param header
     * @return
     */
    public static String getHttplient(String url, Map<String, Object> params, Map<String, String> header) {
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(header)) {
            // 填充header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> entry = new HttpEntity<>(params, headers);
        log.info("流水号:{},调用外部接口,url:{},参数:{},header:{}", TLocalHelper.getSeq(), url, JsonUtil.toJsonString(params), header);
        String result = restTemplate.getForObject(url, String.class);
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), result);
        return result;
    }


    public static String getHttplienturi(URI url, Map<String, Object> params, Map<String, String> header) {
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(header)) {
            // 填充header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> entry = new HttpEntity<>(params, headers);
        log.info("流水号:{},调用外部接口,url:{},参数:{},header:{}", TLocalHelper.getSeq(), url, JsonUtil.toJsonString(params), header);
        String result = restTemplate.getForObject(url, String.class);
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), result);
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @param params
     * @param header
     * @return
     */
    public static String getHttplient1(String url, Map<String, Object> params, Map<String, String> header) {
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(header)) {
            // 填充header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> entry = new HttpEntity<>(params, headers);
        log.info("流水号:{},调用外部接口,url:{},参数:{},header:{}", TLocalHelper.getSeq(), url, JsonUtil.toJsonString(params), header);
//        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class, entry);
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class, entry);
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), result.getBody());
        return result.getBody();
    }

    /**
     * 发送post请求,发送纯文本格式
     *
     * @param url
     * @param param
     * @return
     */
    public static String postHttpTextPlain(String url, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("ContentType", "text/plain");
        HttpEntity<String> entry = new HttpEntity<>(param, headers);
        log.info("流水号:{},调用外部接口,url:{},参数:{},header:{}", TLocalHelper.getSeq(), url, param,"text/plain");
        ResponseEntity<String> result = HttpUtil.restTemplate.postForEntity(url, entry, String.class);
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), result);
        return result.getBody();
    }

    /**
     * post multipart/form-data
     *
     * @param urlName
     * @param url
     * @param params
     * @return
     */
    public static String postMultiFormdata(String urlName, String url, Map<String, Object> params) {
       /* log.info("流水号:{},调用外部接口,接口名称:{},url:{},参数:{}", TLocalHelper.getSeq(), urlName, url, JsonUtil.toJsonString(params));
        String res = cn.hutool.http.HttpUtil.createPost(url)
                .form(params)
                .header(Header.CONTENT_TYPE, "multipart/form-data")
                .execute()
                .body();
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), res);
        return res;*/
        log.info("流水号:{},调用外部接口,接口名称:{},url:{},参数:{}", TLocalHelper.getSeq(), urlName, url, JsonUtil.toJsonString(params));
        String ret = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap map = new LinkedMultiValueMap();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                map.add(entry.getKey(),entry.getValue());
            }
            HttpEntity entity = new HttpEntity(map, headers);
            HttpEntity<String> response = restTemplate.postForEntity(
                    url, entity, String.class);
            ret = response.getBody();
        } catch (Exception e) {
            log.error(TLocalHelper.getSeq() + "httpClient 异常", e);
            throw new BusinessException("httpClient");
        }
        log.info("流水号:{},返回结果:{}", TLocalHelper.getSeq(), ret);
        return ret;
    }
}
