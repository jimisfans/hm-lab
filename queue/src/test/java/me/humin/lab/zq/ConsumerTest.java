package me.humin.lab.zq;

import me.humin.lab.zq.bean.LConsumerBean;
import me.humin.lab.zq.bean.LProducerBean;
import me.humin.lab.zq.impl.LConsumerImpl;
import me.humin.lab.zq.bean.LMessage;
import me.humin.lab.zq.impl.LProducerImpl;
import me.humin.lab.zq.impl.LQueue;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ConsumerTest {

    private LProducerBean<LQBean> producer;

    private LConsumerBean<LQBean> consumer;

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

    private LProducerBean<LQBean> initProducer(LQueue queue) {
        LProducerImpl producer = new LProducerImpl();
        producer.setLocalQueue(queue);
        LProducerBean<LQBean> res = new LProducerBean<>();
        res.setProducer(producer);
        return res;
    }

    private LConsumerBean<LQBean> initConsumer(LQueue queue) {
        LConsumerImpl consumer = new LConsumerImpl();
        consumer.setLocalQueue(queue);
        LConsumerBean<LQBean> res = new LConsumerBean<>();
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
