package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoneyRanking {

 public List<MoneyRankingItem> moneyRankingList;

 @Builder
	public static class MoneyRankingItem {

		public String username;
	 public BigDecimal money;
	}


}
