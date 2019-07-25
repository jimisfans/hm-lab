package me.humin.lab.queue.impl;

import me.humin.lab.queue.Message;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ZQMessage<T> extends Message {

    private T body;

    @Override
    public T getBody() {
        return body;
    }

    @Override
    public void setBody(Object body) {
        this.body = (T) body;
    }

}
