package me.humin.lab.zq.bean;

import me.humin.lab.zq.IConsumer;
import me.humin.lab.zq.IMessageListener;
import me.humin.lab.zq.exception.ZQException;

/**
 * @author: humin
 * @date: 2019-07-26
 */
public class LConsumerBean<T> {

    private IConsumer consumer;

    public void setConsumer(IConsumer consumer) {
        this.consumer = consumer;
    }

    public void subscribe(String topic, LMessageListener<T> lListener) {
        IMessageListener listener = message -> {
            if (message instanceof LMessage) {
                return lListener.consume((LMessage<T>) message);
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
