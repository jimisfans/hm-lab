package com.shecc.app.zeratul.queue;

/**
 * @author: humin
 * @date: 2019-07-24
 */
public interface IMessageListener {

    boolean consume(Message message);

}
