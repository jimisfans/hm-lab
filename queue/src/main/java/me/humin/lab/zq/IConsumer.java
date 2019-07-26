package me.humin.lab.zq;

/**
 * @author: humin
 * @date: 2019-07-24
 */
public interface IConsumer {

    void subscribe(String topic, IMessageListener listener);

    void unsubscribe(String topic);

}
