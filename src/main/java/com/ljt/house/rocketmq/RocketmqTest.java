//package com.ljt.house.rocketmq;
//
//import com.ljt.house.domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * @Description:消息发送类
// * @Author：jintao.li
// * @Classname：RocketmqTest
// * @Date：2023/1/2 20:29
// */
//@Controller
//@RequestMapping("/rocketTest")
//public class RocketmqTest {
//
//    @Autowired
//    private RocketMqHelper rocketMqHelper;
//
//    @RequestMapping("/contextLoads")
//    @ResponseBody
//    public String contextLoads(){
//        User user = User.builder().username("李锦").password("123123").id(111L).build();
//       // Message<User> build = MessageBuilder.withPayload(user).build();
//        //System.out.println(build+"--------------------------");
//        rocketMqHelper.asyncSend("user_add",MessageBuilder.withPayload(user).build());
//        //rocketMqHelper.asyncSend("user_add",MessageBuilder.withPayload("第二次发送").build());
//        return "ok";
//    }
//}
