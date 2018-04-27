package com.yq.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.domain.CmdMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by yqbjtu on 2018/4/22.
 */
@Component
@Slf4j
public class CmdSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        CmdMessage cmdMsg = new CmdMessage();
        cmdMsg.setId(System.currentTimeMillis());
        cmdMsg.setMsg(UUID.randomUUID().toString());
        cmdMsg.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(cmdMsg));
        kafkaTemplate.send("topic01", gson.toJson(cmdMsg));
    }

    //发送消息方法
    public void send(String topic, String str) {
        CmdMessage cmdMsg = new CmdMessage();
        cmdMsg.setId(System.currentTimeMillis());
        cmdMsg.setMsg(str);
        cmdMsg.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(cmdMsg));
        kafkaTemplate.send(topic, gson.toJson(cmdMsg));
    }
}
