package com.shiva.polls.payload;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PollResponse {
	
	private Long id;
	
	private String question;
	
	private List<ChoiceResponse> choices;
	
	private UserSummary createdBy;
	
	private Instant creationDateAndTime;
	
	private Instant expirationDateAndTime;
	
	private Boolean isExpired;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long selectedChoice;
	
	private Long totalVotes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<ChoiceResponse> getChoices() {
		return choices;
	}

	public void setChoices(List<ChoiceResponse> choices) {
		this.choices = choices;
	}

	public UserSummary getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserSummary createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreationDateAndTime() {
		return creationDateAndTime;
	}

	public void setCreationDateAndTime(Instant creationDateAndTime) {
		this.creationDateAndTime = creationDateAndTime;
	}

	public Instant getExpirationDateAndTime() {
		return expirationDateAndTime;
	}

	public void setExpirationDateAndTime(Instant expirationDateAndTime) {
		this.expirationDateAndTime = expirationDateAndTime;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

	public Long getSelectedChoice() {
		return selectedChoice;
	}

	public void setSelectedChoice(Long selectedChoice) {
		this.selectedChoice = selectedChoice;
	}

	public Long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}

}
