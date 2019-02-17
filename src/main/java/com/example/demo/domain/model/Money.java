package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Money {

	public int userId;
	public BigDecimal money;
	public LocalDateTime updateDate;


	public void plusMoney(BigDecimal betMoney) {
   money = money.add(betMoney);
	}

 public void minusMoney(BigDecimal betMoney) {
 	 money = money.subtract(betMoney);
	}

}
