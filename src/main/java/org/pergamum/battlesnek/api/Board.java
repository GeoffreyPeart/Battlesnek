package org.pergamum.battlesnek.api;

import java.util.Arrays;

import javax.swing.tree.AbstractLayoutCache;

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
	
	public String visualizeBoard()
	{
		if(height <=0)
		{
			return "error board too short";
		}
		if (width <= 0)
		{
			return "error board to narrow";
		}
		
		String[][] boardVis = new String[width+1][height];
		Arrays.stream(boardVis).forEach(column -> Arrays.fill(column, " "));
		Arrays.fill(boardVis[width], "\n");
		
		Arrays.stream(food).forEach(foodItem -> boardVis[foodItem.getX()][foodItem.getY()] = "f"); 
		
		Arrays.stream(hazards).forEach(hazard -> boardVis[hazard.getX()][hazard.getY()] = "h");
		
		Arrays.stream(snakes).forEach(snake -> { 
			String id = snake.getId();
			Arrays.stream(snake.getBody()).forEach(bodyPart -> boardVis[bodyPart.getX()][bodyPart.getY()]=id);
			Coordinate head = snake.getHead();
			boardVis[head.getX()][head.getY()]="&";
		});
		
		Battlesnake you = snakes[0];
		
		Arrays.stream(you.getBody()).forEach(bodyPart -> boardVis[bodyPart.getX()][bodyPart.getY()]="I");
		
		boardVis[you.getHead().getX()][you.getHead().getY()]="&";
		
		
		
		return Arrays.deepToString(boardVis);
	}
}
