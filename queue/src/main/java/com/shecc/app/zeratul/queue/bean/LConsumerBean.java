package com.shecc.app.zeratul.queue.bean;

import com.shecc.app.zeratul.queue.IConsumer;
import com.shecc.app.zeratul.queue.IMessageListener;
import com.shecc.app.zeratul.queue.exception.ZQException;

/**
 * @author: humin
 * @date: 2019-07-26
 */
public class LConsumerBean {

    private IConsumer consumer;

    public void setConsumer(IConsumer consumer) {
        this.consumer = consumer;
    }

    public void subscribe(String topic, LMessageListener lListener) {
        IMessageListener listener = message -> {
            if (message instanceof LMessage) {
                return lListener.consume((LMessage) message);
            } else {
                throw new ZQException("wrong message type");
            }
        };
        consumer.subscribe(topic, listener);
    }

    public void unsubscribe(String topic) {
        consumer.unsubscribe(topic);
    }

}
