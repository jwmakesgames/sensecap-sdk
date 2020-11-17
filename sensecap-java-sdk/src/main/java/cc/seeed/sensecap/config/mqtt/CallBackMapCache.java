package cc.seeed.sensecap.config.mqtt;

import cc.seeed.sensecap.model.callback.TelemetryDataCallback;

import java.util.*;

/**
 * @Author AG
 * @Description
 * @Date 2050/7/20 10:08
 * @Version V1.0
 * 用于缓存MQTT对象
 */
public class CallBackMapCache {

    private static final int MAX_CAPACITY = 1000;// 最大容量
    private static final Hashtable<String, List<Map<String, TelemetryDataCallback>>> map = new Hashtable<>();

    private static CallBackMapCache cache = null;

    private CallBackMapCache() {
    }

    ;

    /**
     * 获取该类实例
     */
    public static CallBackMapCache getSimpleCache() {
        if (cache == null) {
            cache = new CallBackMapCache();
        }
        return cache;
    }

    /**
     * 检查是否存在在key
     *
     * @param key 键名称
     * @return true 或 false
     */
    public boolean contains(String key) {

        return map.contains(key);
    }

    /**
     * 删除指定key
     *
     * @param key 键名称
     */
    public void remove(String key) {

        map.remove(key);
    }

    /**
     * 清空缓存
     */
    public void rmoveAll() {
        map.clear();
    }

    /**
     * 根据指定的键值获取对应的val
     *
     * @param key 指定的键值
     * @return 返回该key对应的Object值
     */
    public List<Map<String, TelemetryDataCallback>> get(String key) {
        return map.get(key);
    }

    /**
     * 获取所有缓存
     *
     * @return Map集合
     */
    public Map<String, List<Map<String, TelemetryDataCallback>>> getAll() {
        Map<String, List<Map<String, TelemetryDataCallback>>> hashMap = new HashMap<>();
        Enumeration<String> keys = map.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            List<Map<String, TelemetryDataCallback>> val = map.get(key);
            hashMap.put(key, val);
        }
        return hashMap;
    }

    /**
     * 将key映射到val加入缓存对象中
     *
     * @param key 名称
     * @param val 该名称对应的Object数据
     */
    public void put(String key, List<Map<String, TelemetryDataCallback>> val) {
        if (val == null) {
            throw new NullPointerException();
        }
        if (map.size() >= MAX_CAPACITY) {
            map.clear();
            map.put(key, val);
        }
        if (map.containsKey(key)) {
            return;
        }
        map.put(key, val);
    }
}
