package org.pergamum.battlesnek.api;

import java.util.Arrays;

import org.pergamum.battlesnek.util.CellContent;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	private int height;
	private int width;
	private Coordinate[] food;
	private Coordinate[] hazards;
	private Battlesnake[] snakes;

	@JsonIgnore
	private CellContent[][] cellContent;

	public String visualizeBoard() {
		if (height <= 0) {
			return "error board too short";
		}
		if (width <= 0) {
			return "error board to narrow";
		}

		String[][] boardVis = new String[width][height];
		Arrays.stream(boardVis).forEach(column -> Arrays.fill(column, " "));
		// Arrays.fill(boardVis[width], "\n");

		Arrays.stream(food).forEach(foodItem -> boardVis[foodItem.getX()][foodItem.getY()] = "f");

		Arrays.stream(hazards).forEach(hazard -> boardVis[hazard.getX()][hazard.getY()] = "h");


		Arrays.stream(snakes).forEach(snake -> {
			String id = snake.getId();
			Arrays.stream(snake.getBody())
					.forEach(bodyPart -> boardVis[bodyPart.getX()][bodyPart.getY()] = id.substring(0, 1));
			Coordinate head = snake.getHead();
			boardVis[head.getX()][head.getY()] = "&";
		});

		Battlesnake you = snakes[0];

		Arrays.stream(you.getBody()).forEach(bodyPart -> boardVis[bodyPart.getX()][bodyPart.getY()] = "I");

		boardVis[you.getHead().getX()][you.getHead().getY()] = "&";

		StringBuilder sb = new StringBuilder();

		for (String[] row : boardVis) {
			sb.append("| ");
			for (String item : row) {
				sb.append(item);

			}
			sb.append("|\n");
		}

		return sb.toString();
		// return Arrays.deepToString(boardVis);
	}

	public CellContent whatIsInThatSquare(Coordinate c)
	{
		if(cellContent == null)
		{
			initBoardContents();
		}
		if(c.getX()< 0 || 
		c.getX()>=width ||
		c.getY()<0 ||
		c.getY()>=height)
		{
			return CellContent.EDGE;
		}
		else
		{
			return cellContent[c.getX()][c.getY()];
		}
	}

	private void initBoardContents() {
		cellContent = new CellContent[height][width];
		
		Arrays.stream(cellContent).forEach(row -> Arrays.fill(row, CellContent.EMPTY));
		
		Arrays.stream(food).forEach(foodItem -> cellContent[foodItem.getX()][foodItem.getY()] = CellContent.FOOD);

		Arrays.stream(hazards).forEach(hazard -> cellContent[hazard.getX()][hazard.getY()] = CellContent.HAZARD);

		Arrays.stream(snakes).forEach(snake -> {
			
			Arrays.stream(snake.getBody())
					.forEach(bodyPart -> cellContent[bodyPart.getX()][bodyPart.getY()] = CellContent.SNAKE);
			
		});	
	}
}
