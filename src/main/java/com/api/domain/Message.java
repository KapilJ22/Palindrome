package com.api.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "MESSAGE_ID")
    private Integer id;

    @Column(name = "MESSAGE")
    @NotEmpty
    private String messageText;


    @Column(name = "IS_PALINDROME")
    //@NotNull
    private Boolean isPalindrome;

    public Message() {

    }

    public Message(int id, String text, boolean b) {
        this.id = id;
        this.messageText = text;
        this.isPalindrome = b;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getisPalindrome() {
        return isPalindrome;
    }

    public void setisPalindrome(Boolean isPalindrome) {
        this.isPalindrome = isPalindrome;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

}
