package com.example.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.model.Card;
import com.example.demo.domain.model.LoginSession;
import com.example.demo.domain.model.Money;
import com.example.demo.domain.model.PokerPlayingInfo;
import com.example.demo.domain.model.PokerPlayingInfo.Winner;
import com.example.demo.exception.IllegalBetException;
import com.example.demo.exception.LoginSessionTimeOutException;
import com.example.demo.exception.NotFoundMoneyException;
import com.example.demo.service.MoneyService;
import com.example.demo.service.PokerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PokerController {

	@Autowired
	public PokerService pokerService;

	@Autowired
	public MoneyService moneyService;

	@Autowired
	public LoginSession loginSession;

	// ベット画面を表示する
	@GetMapping("/bet")
	@ResponseBody
	public Money getMoney() throws LoginSessionTimeOutException, NotFoundMoneyException {

		if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
    throw new LoginSessionTimeOutException("ログインセッションがタイムアウトしました");
  }

		return moneyService.getMoney(loginSession.getUserId().get());
	}

	// ポーカーの初期情報(山札・プレイヤーとCPUの手札)を返す。
	@PostMapping("/config")
	@ResponseBody
	public PokerPlayingInfo postPokerStart(BigDecimal betMoney, boolean jokerIncluded) throws LoginSessionTimeOutException, IllegalBetException, NotFoundMoneyException {

		 if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
     throw new LoginSessionTimeOutException("ログインセッションがタイムアウトしました");
		 }
		 return pokerService.pokerPrepare(loginSession.getUserId().get(), betMoney, jokerIncluded);
	}

	// ポーカーの情報(山札・手札 + 役や勝者)を返す
	@PostMapping("/play")
	@ResponseBody
	public PokerPlayingInfo handChange(String jsonPlayerHands, String jsonDeck, String jsonComputerHands) throws IOException, LoginSessionTimeOutException {
		if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
   throw new LoginSessionTimeOutException("ログインセッションがタイムアウトしました");
 }

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

	// 勝者に応じて所持金を更新するリクエストに反応する。
	@PostMapping("/result")
	@ResponseBody
	public Money result(BigDecimal betMoney, Winner winner) throws LoginSessionTimeOutException {

		if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
   throw new LoginSessionTimeOutException("ログインセッションがタイムアウトしました");
 }
		 return moneyService.update(loginSession.getUserId().get(), betMoney , winner);
	}

}
