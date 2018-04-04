/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.social.controller;

import com.social.entity.Message;
import com.social.entity.OutputMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author iguijarm
 */

@RestController
public class MessageController {
    
    private final SimpMessagingTemplate template;
    
    @Autowired
    MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }
    
    @MessageMapping("/send/message")
    public void OnRecivedMessage(String message) {
        System.out.println("Message: " + message);
        this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
    }
}
