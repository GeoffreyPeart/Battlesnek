package org.pergamum.battlesnek.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int height;
	private int width;
	private Coordinate[] food;
	private Coordinate[] hazards;
	private Battlesnake[] snakes;
}
