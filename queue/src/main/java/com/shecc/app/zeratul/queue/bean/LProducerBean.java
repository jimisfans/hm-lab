package com.shecc.app.zeratul.queue.bean;

import com.shecc.app.zeratul.queue.impl.LProducerImpl;

/**
 * @author: humin
 * @date: 2019-07-26
 */
public class LProducerBean {

    private LProducerImpl producer;

    public void setProducer(LProducerImpl producer) {
        this.producer = producer;
    }

    public void send(LMessage message) {
        producer.send(message);
    }

    public void send(String topic, Object content) {
        LMessage message = new LMessage();
        message.setTopic(topic);
        message.setContent(content);
        send(message);
    }

}
