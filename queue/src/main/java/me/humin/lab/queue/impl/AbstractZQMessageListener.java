package me.humin.lab.queue.impl;

import me.humin.lab.queue.IMessageListener;
import me.humin.lab.queue.Message;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public abstract class AbstractZQMessageListener<T> implements IMessageListener {

    abstract boolean consumeZQMessage(ZQMessage<T> message);

    @Override
    public boolean consume(Message message) {
        return this.consumeZQMessage((ZQMessage<T>) message);
    }
}
