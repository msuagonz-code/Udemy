package org.sam.springboot.backend.chat.app.services;

import org.sam.springboot.backend.chat.app.models.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    void save(Message message);
}
