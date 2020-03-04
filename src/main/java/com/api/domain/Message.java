package com.api.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Message {

	@Id
	@GeneratedValue
	@Column(name="MESSAGE_ID")
	private Long id;
	
	@Column(name="MESSAGE")
	@NotEmpty
	private String message;


	@Column(name="IS_PALINDROME")
	//@NotNull
	private Boolean isPalindrome;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="MESSAGE_ID")
//	@OrderBy
//	@Size(min=2, max = 6)
//	private Set<Option> options;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getisPalindrome() {
		return isPalindrome;
	}
	public void setisPalindrome(Boolean isPalindrome) {
		this.isPalindrome = isPalindrome;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

//	public Set<Option> getOptions() {
//		return options;
//	}
//	public void setOptions(Set<Option> options) {
//		this.options = options;
//	}
//
//	@Override
//	public String toString() {
//		return getId() + ", " + getMessage() + ", " + getOptions();
//	}
//
}
