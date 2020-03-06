package com.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
