package me.humin.lab.zq.impl;

import me.humin.lab.zq.IMessageListener;
import me.humin.lab.zq.Message;
import me.humin.lab.zq.exception.ZQException;
import me.humin.lab.zq.util.ZQUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class LQueue {

    private Executor executor;

    private Map<String, IMessageListener> subscribeMap = new ConcurrentHashMap<>();

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void subscribe(String topic, IMessageListener listener) {
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is empty");
        }
        subscribeMap.put(topic, listener);
    }

    public void unsubscribe(String topic) {
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is empty");
        }
        subscribeMap.remove(topic);
    }

    public void send(Message message) {
        if (message == null) {
            throw new ZQException("message is empty");
        }
        String topic = message.getTopic();
        if (ZQUtil.isEmpty(topic)) {
            throw new ZQException("topic is empty");
        }
        IMessageListener listener = subscribeMap.get(topic);
        if (listener != null) {
            executor.execute(()-> listener.consume(message));
        }
    }

}
