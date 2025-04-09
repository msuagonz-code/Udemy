package org.sam.springboot.backend.chat.app.controllers;

import org.sam.springboot.backend.chat.app.models.Message;
import org.sam.springboot.backend.chat.app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    private String[] colors = {"#aeaed1","#bb9dbd","#df6882","#f3be7c","#7e98e8","#8cb66d", "#405065"};
    private final MessageService service;

    @Autowired
    private SimpMessagingTemplate webSocket;

    public ChatController(@Qualifier("messageServiceMongoImpl") MessageService service) {
        this.service = service;
    }

    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message receiveMessage(Message message){
        message.setDate(new Date().getTime());
        //message.setText("Recibido por el broker: "+ message.getText());
        if(message.getType().equals("NEW_USER")){
            message.setText("nuevo usuario conectado");
            message.setColor(this.colors[new Random().nextInt(colors.length)]);
        }else{
            service.save(message);
        }
        return  message;
    }

    @MessageMapping("/writing")
    @SendTo("/chat/writing")
    public String isWriting(String username){
        return username.concat(" está escribiendo...");
    }

    @MessageMapping("/history")
    public void getHistoryMessages(String clientId){
        webSocket.convertAndSend("/chat/history/".concat(clientId), service.findAll());
    }
}
