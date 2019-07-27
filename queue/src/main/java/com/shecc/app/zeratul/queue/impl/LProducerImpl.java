package com.shecc.app.zeratul.queue.impl;

import com.shecc.app.zeratul.queue.IProducer;
import com.shecc.app.zeratul.queue.Message;
import com.shecc.app.zeratul.queue.exception.ZQException;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class LProducerImpl implements IProducer {

    private LQueue localQueue;

    public void setLocalQueue(LQueue localQueue) {
        if (localQueue == null) {
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
