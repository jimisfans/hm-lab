package me.humin.lab.queue.impl;

import me.humin.lab.queue.IConsumer;
import me.humin.lab.queue.IMessageListener;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ConsumerImpl implements IConsumer {

    private ZeratulQueue localQueue;

    public void setLocalQueue(ZeratulQueue localQueue) {
        this.localQueue = localQueue;
    }

    @Override
    public void subscribe(String topic, IMessageListener listener) {
        this.localQueue.subscribe(topic, listener);
    }

    @Override
    public void unsubscribe(String topic) {
        this.localQueue.unsubscribe(topic);
    }
}
