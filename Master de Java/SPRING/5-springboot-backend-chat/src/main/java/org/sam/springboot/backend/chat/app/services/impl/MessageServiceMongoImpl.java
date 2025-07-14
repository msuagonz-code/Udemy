package org.sam.springboot.backend.chat.app.services.impl;

import org.sam.springboot.backend.chat.app.models.Message;
import org.sam.springboot.backend.chat.app.repositories.MessageRepository;
import org.sam.springboot.backend.chat.app.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceMongoImpl implements MessageService {

    private final MessageRepository repository;

    public MessageServiceMongoImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Message> findAll() {
        return repository.findFirst10ByOrderByDateAsc();
    }

    @Override
    public void save(Message message) {
        repository.save(message);
    }
}
