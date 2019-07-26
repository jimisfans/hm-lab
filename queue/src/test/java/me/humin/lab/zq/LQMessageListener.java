package me.humin.lab.zq;

import me.humin.lab.zq.bean.LMessage;
import me.humin.lab.zq.bean.LMessageListener;

/**
 * @author: humin
 * @date: 2019-07-26
 */
public class LQMessageListener implements LMessageListener<LQBean> {

    @Override
    public boolean consume(LMessage<LQBean> message) {
        LQBean bean = message.getContent();
        String topic = message.getTopic();
        System.out.println("topic: " + topic + ", bean: " + bean);
        return true;
    }
}
