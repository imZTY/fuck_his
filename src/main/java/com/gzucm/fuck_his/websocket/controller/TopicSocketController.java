package com.gzucm.fuck_his.websocket.controller;

import com.gzucm.fuck_his.websocket.model.RequestMessage;
import com.gzucm.fuck_his.websocket.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 广播信息，凡是订阅了/topic/getResponse路径的信息都能接受到
 */
@Controller
public class TopicSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate ;


    @RequestMapping("/topic")
    public String topic(){
        return "topic";
    }


    @MessageMapping("/refreshQueue")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/call/waitQueue")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public ResponseMessage say(RequestMessage message) throws Exception {
        System.out.println("发送信息-----------------------" + message.getMessage());

        return new ResponseMessage("Welcome, " + message.getMessage() + "!");
    }

}
