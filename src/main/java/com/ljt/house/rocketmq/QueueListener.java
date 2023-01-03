package com.ljt.house.rocketmq;

import com.ljt.house.domain.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Description:消息消费类
 * @Author：jintao.li
 * @Classname：QueueListener
 * @Date：2023/1/2 20:46
 */
@Component
//${rocketmq.producer.group}  是从nacos配置中心拿值
@RocketMQMessageListener(consumerGroup = "${rocketmq.producer.group}", topic = "user_add")
public class QueueListener implements RocketMQListener<User> {

    @Override
    public void onMessage(User user) {
        //发送消息过后监听完事自动打印
        System.out.println("接受到消息内容，开始消费:"+ user+"---------"+
            "ID:"+user.getId()+"------"+"name:"+user.getUsername());
}
}
