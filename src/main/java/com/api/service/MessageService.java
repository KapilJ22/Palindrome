package com.api.service;


import com.api.domain.Message;
import com.api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    // @Override
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    public Message createMessage(String messageText) {
        Message message = new Message(0, messageText, isPalindrome(messageText));
        message = messageRepository.save(message);
        return message;
    }

    private Boolean isPalindrome(String messageText) {
        StringBuilder reverseText = new StringBuilder();
        reverseText.append(messageText);
        reverseText = reverseText.reverse();
        return messageText.equals(reverseText.toString())? true : false;

    }

    public Message saveMessage(Message message) {
        message.setisPalindrome(isPalindrome(message.getMessageText()));
        return messageRepository.save(message);
    }
}