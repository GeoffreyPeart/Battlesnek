package org.pergamum.battlesnek.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class EndRequest {

	private Game game;
	private int turn;
	private Board board;
	private Battlesnake you;
}
