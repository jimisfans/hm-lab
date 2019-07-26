package me.humin.lab.zq.bean;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public interface LMessageListener<T> {

    boolean consume(LMessage<T> message);

}
