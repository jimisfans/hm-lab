package com.shecc.app.zeratul.queue;

import com.shecc.app.zeratul.queue.bean.LConsumerBean;
import com.shecc.app.zeratul.queue.bean.LProducerBean;
import com.shecc.app.zeratul.queue.impl.LConsumerImpl;
import com.shecc.app.zeratul.queue.bean.LMessage;
import com.shecc.app.zeratul.queue.impl.LProducerImpl;
import com.shecc.app.zeratul.queue.impl.LQueue;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ConsumerTest {

    private LProducerBean producer;

    private LConsumerBean consumer;

    @Test
    public void test() {
        init();
        String topic = "test";
        consumer.subscribe(topic, new LQMessageListener());
        for (int i = 0; i < 100; i++) {
            LMessage<LQBean> message = new LMessage<>();
            message.setContent(LQBean.builder().meta(i + "").build());
            message.setTopic(topic);
            producer.send(message);
        }
    }

    private void init() {
        LQueue queue = initQueue();
        producer = initProducer(queue);
        consumer = initConsumer(queue);
    }

    private LProducerBean initProducer(LQueue queue) {
        LProducerImpl producer = new LProducerImpl();
        producer.setLocalQueue(queue);
        LProducerBean res = new LProducerBean();
        res.setProducer(producer);
        return res;
    }

    private LConsumerBean initConsumer(LQueue queue) {
        LConsumerImpl consumer = new LConsumerImpl();
        consumer.setLocalQueue(queue);
        LConsumerBean res = new LConsumerBean();
        res.setConsumer(consumer);
        return res;
    }

    private LQueue initQueue() {
        LQueue queue = new LQueue();
        Executor executor = Executors.newScheduledThreadPool(4);
        queue.setExecutor(executor);
        return queue;
    }

}
