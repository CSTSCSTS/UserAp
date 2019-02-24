package com.example.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

	@Autowired
	protected MessageSource messageSource;

/**
 * 所持金情報を返す
 * @return
 * @throws LoginSessionTimeOutException セッションタイムアウトエラー
 */
@GetMapping("/bet")
	@ResponseBody
	public Money getMoney() throws LoginSessionTimeOutException {

		if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
    throw new LoginSessionTimeOutException(messageSource.getMessage("login.session.timeout", null, Locale.JAPAN));
  }

		return moneyService.getMoney(loginSession.getUserId().get());
	}

/**
 * ベット額が所持金を超えていないかチェックして、ポーカーの初期情報(山札・プレイヤーとCPUの手札)を返す。
 * @param betMoney ベット額
 * @param jokerIncluded ジョーカーを含んでいるかどうか
 * @return
 * @throws LoginSessionTimeOutException セッションタイムアウトエラー
 * @throws IllegalBetException ベット額が所持金を超えているエラー
 */
@PostMapping("/config")
	@ResponseBody
	public PokerPlayingInfo postPokerStart(BigDecimal betMoney, boolean jokerIncluded) throws LoginSessionTimeOutException, IllegalBetException {

		 if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
     throw new LoginSessionTimeOutException(messageSource.getMessage("login.session.timeout", null, Locale.JAPAN));
		 }
		 return pokerService.pokerPrepare(loginSession.getUserId().get(), betMoney, jokerIncluded);
	}

/**
 * 手札交換・役判定を勝者判定を実施
 * @param jsonPlayerHands プレイヤーの手札
 * @param jsonDeck 山札
 * @param jsonComputerHands  CPUの手札
 * @return
 * @throws IOException
 * @throws LoginSessionTimeOutException セッションタイムアウトエラー
 */
@PostMapping("/play")
	@ResponseBody
	public PokerPlayingInfo handChange(String jsonPlayerHands, String jsonDeck, String jsonComputerHands) throws IOException, LoginSessionTimeOutException {
		if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
   throw new LoginSessionTimeOutException(messageSource.getMessage("login.session.timeout", null, Locale.JAPAN));
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

/**
 * 勝敗に応じて所持金を更新する
 * @param betMoney ベット額
 * @param winner 勝者
 * @return
 * @throws LoginSessionTimeOutException セッションタイムアウトエラー
 */
@PostMapping("/result")
	@ResponseBody
	public Money result(BigDecimal betMoney, Winner winner) throws LoginSessionTimeOutException {

		if(!loginSession.getUserId().isPresent() || !loginSession.getUserName().isPresent()) {
   throw new LoginSessionTimeOutException(messageSource.getMessage("login.session.timeout", null, Locale.JAPAN));
 }
		 return moneyService.update(loginSession.getUserId().get(), betMoney , winner);
	}

}
