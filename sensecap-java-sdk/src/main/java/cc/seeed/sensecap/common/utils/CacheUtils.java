package cc.seeed.sensecap.common.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/7 17:08
 * @Version V1.0
 */
public class CacheUtils {

    //  不同的令牌桶, 每天自动清理缓存
    public static LoadingCache<String, RateLimiter> caches = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<String, RateLimiter>() {
                @Override
                public RateLimiter load(String key) throws Exception {
                    //  初始化 (限流每秒两个令牌响应)
                    return RateLimiter.create(1);
                }
            });


    //   缓存
    public static LoadingCache<String, Map<String, Integer>> timesCaches = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<String, Map<String, Integer>>() {
                @Override
                public Map<String, Integer> load(String key) throws Exception {
                    //  初始化 (限流每秒两个令牌响应)
                    Map<String, Integer> map = Maps.newHashMapWithExpectedSize(1);
                    map.put(key, 3);
                    return map;
                }
            });
}
