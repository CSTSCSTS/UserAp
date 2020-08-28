package com.example.demo.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyRanking {
 public List<MoneyRankingItem> moneyRankingList;
}
