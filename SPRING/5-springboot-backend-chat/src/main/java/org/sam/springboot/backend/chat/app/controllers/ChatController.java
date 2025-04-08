package org.sam.springboot.backend.chat.app.controllers;

import org.sam.springboot.backend.chat.app.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    private String[] colors = {"#aeaed1","#bb9dbd","#df6882","#f3be7c","#7e98e8","#8cb66d", "#405065"};

    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message receiveMessage(Message message){
        message.setDate(new Date().getTime());
        //message.setText("Recibido por el broker: "+ message.getText());
        if(message.getType().equals("NEW_USER")){
            message.setText("nuevo usuario conectado");
            message.setColor(this.colors[new Random().nextInt(colors.length)]);
        }
        return  message;
    }
}
