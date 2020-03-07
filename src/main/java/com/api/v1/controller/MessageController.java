package com.api.v1.controller;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import com.api.service.MessageService;
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

@RestController("MessageControllerV1")
@RequestMapping("/v1/")
@Api(value = "messages", description = "Message API")
public class MessageController {

	@Inject
	private MessageRepository messageRepository;
	@Inject
	MessageService messageService;

	private Message message;

	@RequestMapping(value="/messages/{messageText}", method=RequestMethod.POST)
	@ApiOperation(value = "Creates a new Message", notes="The newly created message Id will be sent in the location response header",
					response = Void.class)
	@ApiResponses(value = {@ApiResponse(code=201, message="Message Created Successfully", response=Void.class),
			@ApiResponse(code=500, message="Error creating Message", response=ErrorDetail.class) } )
	public ResponseEntity<?> createMessage(@PathVariable String messageText ) {


		message = messageService.createMessage(messageText);
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newMessageUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(message.getId()).toUri();
		responseHeaders.setLocation(newMessageUri);

		return new ResponseEntity<>(message, responseHeaders, HttpStatus.CREATED);
	}

//	private Boolean isPalindrome(String messageText){
//		StringBuilder reverseText = new StringBuilder();
//		reverseText.append(messageText);
//		reverseText = reverseText.reverse();
//
//		if(messageText.equals(reverseText.toString())){
//			return true;
//		}
//		else return false;
//
//	}

	@RequestMapping(value="/messages/{messageId}", method=RequestMethod.GET)
	@ApiOperation(value = "Retrieves given Message", response=Message.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="", response=Message.class),
			@ApiResponse(code=404, message="Unable to find Message", response=ErrorDetail.class) } )
	public ResponseEntity<?> getMessage(@PathVariable Integer messageId) {
		verifyMessage(messageId);
		Optional<Message> p = messageRepository.findById(messageId);
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
	public ResponseEntity<Void> updateMessage(@RequestBody Message message, @PathVariable Integer messageId) {
		verifyMessage(messageId);
		messageRepository.save(message);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/messages/{messageId}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Deletes given Message", response=Void.class)
	@ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),  
			@ApiResponse(code=404, message="Unable to find Message", response=ErrorDetail.class) } )
	public ResponseEntity<Void> deleteMessage(@PathVariable Integer messageId) {
		verifyMessage(messageId);
		messageRepository.delete(message);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	protected void verifyMessage(Integer messageId) throws ResourceNotFoundException {
		Optional<Message> message = messageRepository.findById(messageId);
		if(message == null) {
			throw new ResourceNotFoundException("Message with id " + messageId + " not found");
		}
	}
}
