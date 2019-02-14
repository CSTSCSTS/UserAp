package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.model.Ranking;
import com.example.demo.service.RankingService;

@Controller
public class RankingController {

	@Autowired
	public RankingService rankingService;

	// ランキング情報を返す
	@GetMapping("/ranking")
	@ResponseBody
	public Ranking getRanking() {
		return rankingService.getRankingInfo();
	}


}
