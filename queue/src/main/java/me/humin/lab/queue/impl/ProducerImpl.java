package me.humin.lab.queue.impl;

import me.humin.lab.queue.IProducer;
import me.humin.lab.queue.Message;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ProducerImpl implements IProducer {

    private ZeratulQueue localQueue;

    public void setLocalQueue(ZeratulQueue localQueue) {
        this.localQueue = localQueue;
    }

    @Override
    public void send(Message msg) {
        localQueue.send(msg);
    }

}
