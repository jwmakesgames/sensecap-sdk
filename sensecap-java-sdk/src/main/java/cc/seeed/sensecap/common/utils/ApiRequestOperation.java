package cc.seeed.sensecap.common.utils;

import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.resp.HttpResponseMessage;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/14 11:09
 * @Version V1.0
 */
public class ApiRequestOperation {

    Log logger = LogFactory.getLog(this.getClass());
    private Map<String, Integer> map;
    private String domain;
    private String token;

    public ApiRequestOperation(String domain, String token) {
        this.domain = domain;
        this.token = token;
    }

    public HttpResponseMessage getOperation(String path) throws IOException {
        return getOperation(path, null);
    }

    public HttpResponseMessage getOperation(String path, Map<String, String> query) throws IOException {

        try {
            logger.info("domain: " + domain + "path:" + path + " ,token: " + token);
            String key = path + "-" + token;
            RateLimiter rateLimiter = CacheUtils.caches.get(path + "-" + token);
            rateLimiter.acquire(1);
            Map<String, String> headers = Maps.newHashMapWithExpectedSize(1);
            headers.put("Authorization", token);
            HttpResponse response = HttpUtils.doGet(domain, path, "GET", headers, query);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 429) {
                Map<String, Integer> stringIntegerMap = CacheUtils.timesCaches.get(key);
                int times = stringIntegerMap.get(key);
                if (times > 0) {
                    times--;
                    stringIntegerMap.put(key, times);
                    CacheUtils.timesCaches.put(key, stringIntegerMap);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return getOperation(path, query);
                }
            }
            logger.info("getOperation resp code: " + statusCode);
            String resp = EntityUtils.toString(response.getEntity());
            HttpResponseMessage httpResponseMessage = JSON.parseObject(resp, HttpResponseMessage.class);
            return httpResponseMessage;
        } catch (ExecutionException e) {
            throw new BaseException(e.getMessage());
        }

    }


    public HttpResponseMessage postOperation(String path) throws IOException {
        return postOperation(path, null, null);
    }

    public HttpResponseMessage postOperation(String path, Map<String, String> body) throws IOException {
        return postOperation(path, body, null);
    }

    public HttpResponseMessage postOperation(Map<String, String> query, String path) throws IOException {
        return postOperation(path, null, query);
    }

    public HttpResponseMessage postOperation(String path, Map<String, String> body, Map<String, String> query) throws IOException {
        try {
            String key = path + "-" + token;
            RateLimiter rateLimiter = CacheUtils.caches.get(path + "-" + token);
            rateLimiter.acquire(1);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", token);
            HttpResponse response = HttpUtils.doPost(domain, path, "POST", headers, query, body);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 429) {
                Map<String, Integer> stringIntegerMap = CacheUtils.timesCaches.get(key);
                int times = stringIntegerMap.get(key);
                if (times > 0) {
                    times--;
                    stringIntegerMap.put(key, times);
                    CacheUtils.timesCaches.put(key, stringIntegerMap);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return getOperation(path, query);
                }
            }
            String resp = EntityUtils.toString(response.getEntity());
            HttpResponseMessage httpResponseMessage = JSON.parseObject(resp, HttpResponseMessage.class);
            return httpResponseMessage;
        } catch (ExecutionException e) {
            throw new BaseException(e.getMessage());
        }
    }


    public HttpResponseMessage postJsonOperation(String path, String body) throws IOException {
        return postJsonOperation(path, body, null);
    }

    public HttpResponseMessage postJsonOperation(String path, String body, Map<String, String> query) throws IOException {
        try {
            String key = path + "-" + token;
            RateLimiter rateLimiter = CacheUtils.caches.get(path + "-" + token);
            rateLimiter.acquire(1);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", token);
            headers.put("content-type", "application/json; charset=utf-8");
            HttpResponse response = HttpUtils.doPost(domain, path, "POST", headers, query, body);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 429) {
                Map<String, Integer> stringIntegerMap = CacheUtils.timesCaches.get(key);
                int times = stringIntegerMap.get(key);
                if (times > 0) {
                    times--;
                    stringIntegerMap.put(key, times);
                    CacheUtils.timesCaches.put(key, stringIntegerMap);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return getOperation(path, query);
                }
            }
            String resp = EntityUtils.toString(response.getEntity());
            HttpResponseMessage httpResponseMessage = JSON.parseObject(resp, HttpResponseMessage.class);
            return httpResponseMessage;
        } catch (ExecutionException e) {
            throw new BaseException(e.getMessage());
        }
    }

}
