package com.shiva.polls.util;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shiva.polls.model.Choice;
import com.shiva.polls.model.Poll;
import com.shiva.polls.model.User;
import com.shiva.polls.payload.ChoiceResponse;
import com.shiva.polls.payload.PollResponse;
import com.shiva.polls.payload.UserSummary;

public class ModelMapper {
	
	public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
		PollResponse pollResponse = new PollResponse();
		
		pollResponse.setId(poll.getId());
		pollResponse.setQuestion(poll.getQuestion());
		pollResponse.setCreationDateAndTime(poll.getCreatedAt());
		pollResponse.setExpirationDateAndTime(poll.getExpirationDateTime());
		Instant now = Instant.now();
		pollResponse.setIsExpired(poll.getExpirationDateTime().isBefore(now));
		
		List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
			ChoiceResponse choiceResponse = new ChoiceResponse();
			choiceResponse.setId(choice.getId());
			choiceResponse.setText(choice.getText());
			if(choiceVotesMap.containsKey(choice.getId())) {
				choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
			} else {
				choiceResponse.setVoteCount(0);
			}
			return choiceResponse;
		}).collect(Collectors.toList());
		
		pollResponse.setChoices(choiceResponses);
		UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
		pollResponse.setCreatedBy(creatorSummary);
		
		if(userVote != null) {
			pollResponse.setSelectedChoice(userVote);
		}
		
		long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
		pollResponse.setTotalVotes(totalVotes);
		return pollResponse;
	}

}
