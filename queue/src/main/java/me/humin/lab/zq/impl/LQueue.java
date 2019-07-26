package me.humin.lab.zq.impl;

import com.google.common.collect.Sets;
import me.humin.lab.zq.IMessageListener;
import me.humin.lab.zq.Message;
import me.humin.lab.zq.exception.ZQException;
import me.humin.lab.zq.util.ZQUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class LQueue {

    private static Logger logger = LoggerFactory.getLogger(LQueue.class);

    private Executor executor;

    private Map<String, Set<IMessageListener>> subscribeMap = new ConcurrentHashMap<>();

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void subscribe(String topic, IMessageListener listener) {
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is empty");
        }
        if (listener == null) {
            throw new ZQException("listener is null");
        }
        put(topic, listener);
    }

    public void unsubscribe(String topic, IMessageListener listener) {
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is empty");
        }
        Set<IMessageListener> listenerSet = subscribeMap.get(topic);
        listenerSet.remove(listener);
        if (listenerSet.isEmpty()) {
            subscribeMap.remove(topic);
        }
    }

    public void send(Message message) {
        if (message == null) {
            throw new ZQException("message is empty");
        }
        String topic = message.getTopic();
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is empty");
        }
        Set<IMessageListener> listeners = subscribeMap.get(topic);
        if (ZQUtil.isNotEmpty(listeners)) {
            listeners.forEach(it -> {executor.execute(()-> it.consume(message));});
        } else {
            logger.warn("listener not found for topic:{}", topic);
        }
    }

    private void put(String topic, IMessageListener listener) {
        Set<IMessageListener> listeners = subscribeMap.computeIfAbsent(topic, k -> Sets.newConcurrentHashSet());
        listeners.add(listener);
    }

}
