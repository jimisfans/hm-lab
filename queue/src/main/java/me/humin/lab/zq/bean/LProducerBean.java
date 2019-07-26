package me.humin.lab.zq.bean;

import me.humin.lab.zq.impl.LProducerImpl;

/**
 * @author: humin
 * @date: 2019-07-26
 */
public class LProducerBean<T> {

    private LProducerImpl producer;

    public void setProducer(LProducerImpl producer) {
        this.producer = producer;
    }

    public void send(LMessage<T> message) {
        producer.send(message);
    }

}
