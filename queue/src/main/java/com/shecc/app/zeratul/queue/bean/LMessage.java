package com.shecc.app.zeratul.queue.bean;

import com.shecc.app.zeratul.queue.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: humin
 * @date: 2019-07-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LMessage<T> extends Message {

    private T content;

}
