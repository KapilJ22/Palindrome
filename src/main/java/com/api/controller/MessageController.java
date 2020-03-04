package com.api.controller;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.domain.Message;
import com.api.dto.error.ErrorDetail;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.MessageRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(value = "messages", description = "Message API")
public class MessageController {

	@Inject
	private MessageRepository messageRepository;
	private Message message;

	@RequestMapping(value="/messages", method=RequestMethod.POST)
	@ApiOperation(value = "Creates a new Message", notes="The newly created message Id will be sent in the location response header",
					response = Void.class)
	@ApiResponses(value = {@ApiResponse(code=201, message="Message Created Successfully", response=Void.class),
			@ApiResponse(code=500, message="Error creating Message", response=ErrorDetail.class) } )
	public ResponseEntity<?> createMessage(@Valid @RequestBody Message message) {
		System.out.println("Message: " + message);
		System.out.println("Message: " + message.getMessage());

		StringBuilder input1 = new StringBuilder();

		// append a string into StringBuilder input1
		input1.append(message.getMessage());

		// reverse StringBuilder input1
		input1 = input1.reverse();

		System.out.println("input1 " + input1);
		if(message.getMessage().equals(input1.toString())){
			message.setisPalindrome(true);
		}
		else message.setisPalindrome(false);
		message = messageRepository.save(message);
		System.out.println("Message ID: " + message.getId());
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newMessageUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(message.getId()).toUri();
		responseHeaders.setLocation(newMessageUri);
		
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/messages/{messageId}", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves given Message", response=Message.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="", response=Message.class),
			@ApiResponse(code=404, message="Unable to find Message", response=ErrorDetail.class) } )
	public ResponseEntity<?> getMessage(@PathVariable Long messageId) {
		verifyMessage(messageId);
		Message p = messageRepository.findOne(messageId);
		return new ResponseEntity<> (p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/messages", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the messages", response=Message.class, responseContainer="List")
	public ResponseEntity<Iterable<Message>> getAllMessages() {
		Iterable<Message> allMessages = messageRepository.findAll();
		return new ResponseEntity<>(allMessages, HttpStatus.OK);
	}

	@RequestMapping(value="/messages/{messageId}", method=RequestMethod.PUT)
	@ApiOperation(value = "Updates given Message", response=Void.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),  
			@ApiResponse(code=404, message="Unable to find Message", response=ErrorDetail.class) } )
	public ResponseEntity<Void> updateMessage(@RequestBody Message message, @PathVariable Long messageId) {
		verifyMessage(messageId);
		messageRepository.save(message);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/messages/{messageId}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Deletes given Message", response=Void.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),  
			@ApiResponse(code=404, message="Unable to find Message", response=ErrorDetail.class) } )
	public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
		verifyMessage(messageId);
		messageRepository.delete(messageId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	protected void verifyMessage(Long messageId) throws ResourceNotFoundException {
		Message message = messageRepository.findOne(messageId);
		if(message == null) {
			throw new ResourceNotFoundException("Message with id " + messageId + " not found");
		}
	}
}
