package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.MoneyRanking;
import com.example.demo.domain.model.Ranking;
import com.example.demo.repository.UserRepository;

@Service
public class RankingService {

	@Autowired
	private UserRepository userRepository;


	public Ranking getRankingInfo() {

		MoneyRanking moneyRanking = userRepository.getMoneyRanking();
		return Ranking.builder().moneyRanking(moneyRanking).build();

	}

}
