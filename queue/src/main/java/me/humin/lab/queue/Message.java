package me.humin.lab.queue;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: humin
 * @date: 2019-07-24
 */
@Data
@Builder
@ToString
public class Message<T> {

    private String topic;

    private List<String> tags;

    private T body;

}
