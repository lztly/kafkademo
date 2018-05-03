package com.yq.service;

/**
 * Simple to Introduction
 * className: ProducerService
 *
 * @author yqbjtu
 * @version 2018/4/27 8:51
 */
public interface ProducerService {
    void send(String topic, String str);
    void sendJson(String topic, String json);
}
