package me.humin.lab.queue.impl;

import me.humin.lab.queue.IMessageListener;
import me.humin.lab.queue.Message;
import me.humin.lab.queue.exception.ZQException;
import me.humin.lab.queue.util.ZQUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ZeratulQueue {

    private Executor executor;

    private Map<String,  IMessageListener> subscribeMap = new ConcurrentHashMap<>();

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void subscribe(String topic, IMessageListener listener) {
        subscribeMap.put(topic, listener);
    }

    public void unsubscribe(String topic) {
        subscribeMap.remove(topic);
    }

    public void send(Message message) {
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
