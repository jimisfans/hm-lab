package me.humin.lab.zq.impl;

import me.humin.lab.zq.IProducer;
import me.humin.lab.zq.Message;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class LProducerImpl implements IProducer {

    private LQueue localQueue;

    public void setLocalQueue(LQueue localQueue) {
        this.localQueue = localQueue;
    }

    @Override
    public void send(Message message) {
        this.localQueue.send(message);
    }

}
