package com.yq;

import com.yq.producer.CmdSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by yqbjtu on 2018/4/22.
 */
@SpringBootApplication
public class KafkaDemoApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaDemoApplication.class, args);

        CmdSender sender = context.getBean(CmdSender.class);

        for (int i = 0; i < 3; i++) {
            //调用消息发送类中的消息发送方法
            sender.send();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
