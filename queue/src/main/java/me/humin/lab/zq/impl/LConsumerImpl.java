package me.humin.lab.zq.impl;

import me.humin.lab.zq.IConsumer;
import me.humin.lab.zq.IMessageListener;
import me.humin.lab.zq.exception.ZQException;
import me.humin.lab.zq.util.ZQUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class LConsumerImpl implements IConsumer {

    private LQueue localQueue;

    private Map<String, IMessageListener> listenerMap = new ConcurrentHashMap<>();

    public void setLocalQueue(LQueue localQueue) {
        this.localQueue = localQueue;
    }

    public void subscribe(String topic, IMessageListener listener) {
        if (this.localQueue == null) {
            throw new ZQException("queue is null");
        }
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is null");
        }
        listenerMap.put(topic, listener);
        this.localQueue.subscribe(topic, listenerMap.get(topic));
    }

    @Override
    public void unsubscribe(String topic) {
        if (this.localQueue == null) {
            throw new ZQException("queue is null");
        }
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is null");
        }
        this.localQueue.unsubscribe(topic, listenerMap.get(topic));
    }

}
