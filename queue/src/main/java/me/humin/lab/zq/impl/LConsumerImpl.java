package me.humin.lab.zq.impl;

import me.humin.lab.zq.IConsumer;
import me.humin.lab.zq.IMessageListener;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class LConsumerImpl implements IConsumer {

    private LQueue localQueue;

    public void setLocalQueue(LQueue localQueue) {
        this.localQueue = localQueue;
    }

    public void subscribe(String topic, IMessageListener listener) {
        this.localQueue.subscribe(topic, listener);
    }

    @Override
    public final void unsubscribe(String topic) {
        this.localQueue.unsubscribe(topic);
    }

}
