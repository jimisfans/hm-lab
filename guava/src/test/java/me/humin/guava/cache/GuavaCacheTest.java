package me.humin.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.time.Duration;

/**
 * @Author: humin
 * @Date: 2019-07-03
 */
public class GuavaCacheTest {


    @Test
    public void base() throws InterruptedException {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofMillis(1000))
                .build();
        cache.put("test", CacheMeta.builder().obj("test1").build());
        Thread.sleep(500L);
        System.out.println(cache.getIfPresent("test"));
        Thread.sleep(499L);
        System.out.println(cache.getIfPresent("test"));
        Thread.sleep(500L);
        System.out.println(cache.getIfPresent("test"));
    }

}
