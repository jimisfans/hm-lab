package me.humin.lab.queue;

import java.util.List;

/**
 * @author: humin
 * @date: 2019-07-24
 */
public class Message {

    private String topic;

    private List<String> tags;

    private Object body;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
