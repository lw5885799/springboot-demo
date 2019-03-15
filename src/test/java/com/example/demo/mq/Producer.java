package com.example.demo.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description:
 * @date:2019-3-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Producer {

        @Test
        public void producerTest(){
            DefaultMQProducer producer = new
                    DefaultMQProducer("please_rename_unique_group_name");
            try {
                //Instantiate with a producer group name.
                // Specify name server addresses.
                producer.setNamesrvAddr("39.105.164.67:9876");
                //关闭VIP通道
                producer.setVipChannelEnabled(false);
                //Launch the instance.
                producer.start();
                for (int i = 0; i < 20; i++) {
                    //Create a message instance, specifying topic, tag and message body.
                    Message msg = new Message("TopicTest6" /* Topic */,
                            "TagA" /* Tag */,
                            ("Hello RocketMQ " +
                                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                    );
                    //Call send message to deliver message to one of brokers.
                    SendResult sendResult = producer.send(msg);
                    System.out.printf("%s%n", sendResult);
                }
                //Shut down once the producer instance is not longer in use.

            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                producer.shutdown();
            }
        }
}
