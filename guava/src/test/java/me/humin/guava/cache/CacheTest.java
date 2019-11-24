package me.humin.guava.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @Author: humin
 * @Date: 2019-07-03
 */
public class CacheTest {

    @Test
    public void test() throws Exception {
//        expireWriteTest();
//        expireAccessTest();
//        maxWeightTest();

//        maxSizeTest(20);
//        maxSizeTest(50);
//        maxSizeTest(100);
        listener();

    }

    private void listener() throws InterruptedException {
        Cache<String, CacheMeta> cache = CacheBuilder.newBuilder()
                .removalListener((RemovalListener<String, CacheMeta>) notification -> {
                    System.out.println(notification.getCause());
                    System.out.println(notification.getKey());
                    System.out.println(notification.getValue());
                })
                .build();
        cache.put("1", CacheMeta.builder().obj("test1").build());
        cache.put("1", CacheMeta.builder().obj("test2").build());
        cache.invalidate("1");
    }

    public void expireWriteTest() throws InterruptedException {
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

    public void expireAccessTest() throws InterruptedException {
        Cache<String, CacheMeta> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(Duration.ofMillis(1000))
                .build();
        cache.put("1", CacheMeta.builder().obj("1").build());
        try {
            String key = "2";
            cache.get(key, () -> CacheMeta.builder().obj(key).build());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Thread.sleep(900L);
        System.out.println(cache.getIfPresent("1"));
        Thread.sleep(900L);
        System.out.println(cache.getIfPresent("1"));
        Thread.sleep(900L);
        System.out.println(cache.getIfPresent("1"));
        cache.put("1", CacheMeta.builder().obj("2").build());
        System.out.println(cache.getIfPresent("1"));
        Thread.sleep(900L);
        System.out.println(cache.getIfPresent("1"));
        Thread.sleep(90L);
        System.out.println(cache.getIfPresent("1"));
        Thread.sleep(1000L);
        System.out.println(cache.getIfPresent("1"));

    }

    public void maxWeightTest() {
        Cache<String, CacheMeta> cache = CacheBuilder.newBuilder()
            .maximumWeight(1024 * 1024)
                .weigher((Weigher<String, CacheMeta>) (key, value) -> key.getBytes().length + value.getObj().getBytes().length)
         .build();
        for (int i = 0; i < 100000; i++) {
            String str = of1024WeightStr(i);
            cache.put(i + "", CacheMeta.builder().obj(str).build());
            System.out.println(cache.size());
        }
        System.out.println("finally size:" + cache.size());
        Map<String, CacheMeta> map = cache.asMap();
        System.out.println(map.keySet().stream().map(it ->
            it.getBytes().length + map.get(it).getObj().getBytes().length
        ).collect(Collectors.summarizingDouble(Integer::doubleValue)));
    }

    public void maxSizeTest(final int size) {
        Cache<String, CacheMeta> cache = CacheBuilder.newBuilder()
                .maximumSize(size)
                .build();
        for (int i = 0; i < size; i++) {
            cache.put(i + "", CacheMeta.builder().obj(i + "").build());
        }
        System.out.println(cache.size());
        System.out.println(cache.asMap().keySet().stream().map(Integer::valueOf).sorted().collect(Collectors.toList()));
        for (int i = size; i < size * 2; i++) {
            cache.put(i + "", CacheMeta.builder().obj(i + "").build());
        }
        System.out.println(cache.size());
        System.out.println(cache.asMap().keySet().stream().map(Integer::valueOf).sorted().collect(Collectors.toList()));
        System.out.println("-----------------------");
    }


    private String of1024WeightStr(int x) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 1024) {
            builder.append(x);
        }
        return builder.toString();
    }

}
