package com.shecc.app.zeratul.queue.impl;

import com.shecc.app.zeratul.queue.IConsumer;
import com.shecc.app.zeratul.queue.IMessageListener;
import com.shecc.app.zeratul.queue.exception.ZQException;
import com.shecc.app.zeratul.queue.util.ZQUtil;

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
        if (localQueue == null) {
            throw new ZQException("queue is null");
        }
        this.localQueue = localQueue;
    }

    @Override
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
