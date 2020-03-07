package com.api.service;




       import com.api.domain.Message;
       import com.api.repository.MessageRepository;

        import org.springframework.beans.factory.annotation.Autowired;

       import org.springframework.stereotype.Service;



@Service
public class  MessageService {

    @Autowired
    MessageRepository messageRepository;

   // @Override
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    public Message createMessage(String messageText ) {
        Message message = new Message(0,messageText,isPalindrome(messageText));
        message = messageRepository.save(message);
        return message;
    }

    private Boolean isPalindrome(String messageText){
        StringBuilder reverseText = new StringBuilder();
        reverseText.append(messageText);
        reverseText = reverseText.reverse();

        if(messageText.equals(reverseText.toString())){
            return true;
        }
        else return false;

    }
}