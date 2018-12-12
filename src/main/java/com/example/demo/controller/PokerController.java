package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.PokerPlayingInfo;
import com.example.demo.service.PokerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PokerController {

	@Autowired
	public PokerService pokerService;

	@RequestMapping("/")
	public String getPokerStart() {
		return "index";
	}

	@PostMapping("/config")
	@ResponseBody
	public PokerPlayingInfo postPokerStart(boolean jokerIncluded) {
		return pokerService.pokerPrepare(jokerIncluded);
	}

	@PostMapping("/play")
	@ResponseBody
	public PokerPlayingInfo handChange(String jsonPlayerHands, String jsonDeck, String jsonComputerHands) throws IOException {

		ObjectMapper o = new ObjectMapper();

		List<Card> playerHands = o.readValue(jsonPlayerHands, new TypeReference<List<Card>>(){});
		List<Card> deck = o.readValue(jsonDeck, new TypeReference<List<Card>>(){});
		List<Card> computerHands = o.readValue(jsonComputerHands, new TypeReference<List<Card>>(){});

		return pokerService.handChangeAfterProcess(PokerPlayingInfo.builder()
				.deck(deck)
				.playerHands(playerHands)
				.computerHands(computerHands)
				.build());

	}

}
