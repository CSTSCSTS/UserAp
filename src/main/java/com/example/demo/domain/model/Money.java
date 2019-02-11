package com.example.demo.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Money {

	public int userId;
	public BigDecimal money;


	public void plusMoney(BigDecimal betMoney) {
   money = money.add(betMoney);
	}

 public void minusMoney(BigDecimal betMoney) {
 	 money = money.subtract(betMoney);
	}

}
