package me.humin.lab.zq.impl;

import me.humin.lab.zq.IProducer;
import me.humin.lab.zq.Message;
import me.humin.lab.zq.exception.ZQException;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class LProducerImpl implements IProducer {

    private LQueue localQueue;

    public void setLocalQueue(LQueue localQueue) {
        if (this.localQueue == null) {
            throw new ZQException("queue is null");
        }
        this.localQueue = localQueue;
    }

    @Override
    public void send(Message message) {
        if (this.localQueue == null) {
            throw new ZQException("queue is null");
        }
        this.localQueue.send(message);
    }

}
