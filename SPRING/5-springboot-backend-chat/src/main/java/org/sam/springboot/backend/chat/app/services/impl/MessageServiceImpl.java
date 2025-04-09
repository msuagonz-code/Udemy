package org.sam.springboot.backend.chat.app.services.impl;

import org.sam.springboot.backend.chat.app.models.Message;
import org.sam.springboot.backend.chat.app.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final List<Message> messages = new ArrayList<>();

    @Override
    public List<Message> findAll() {
        return messages;
    }

    @Override
    public void save(Message message) {
        messages.add(message);
    }
}
