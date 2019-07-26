package me.humin.lab.zq.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.humin.lab.zq.Message;

/**
 * @author: humin
 * @date: 2019-07-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LMessage<T> extends Message {

    private T content;

}
