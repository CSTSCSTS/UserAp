package com.example.demo.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokerPlayingInfo {

	public List<Card> deck;
	public List<Card> playerHands;
	public List<Card> computerHands;
	public Role playerRole;
	public Role computerRole;
	private Winner winner;
	private boolean isFinishedChange;

	public enum Winner {
		PLAYER, CPU, NOTHING;
	}

}
