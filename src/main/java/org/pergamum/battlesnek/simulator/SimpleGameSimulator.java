package org.pergamum.battlesnek.simulator;

import java.util.Arrays;

import org.pergamum.battlesnek.api.Battlesnake;
import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Coordinate;

public class SimpleGameSimulator implements GameSimulator{

	/**
	 * 
	 */
	public Board advanceBoard(Board board, Coordinate moveInto, boolean growSnake) {
		Board returnBoard = board.copy(); // TODO deep copy or else

		// moved us
		Battlesnake you = returnBoard.getSnakes()[0];
		Coordinate[] yourBody = you.getBody();
		Coordinate[] tempBody;

		int offset;
		if (growSnake) {
			offset = 1;
			Coordinate[] food = returnBoard.getFood();
			Coordinate[] newFood = new Coordinate[food.length - 1];

			int f = 0;
			int nf = 0;
			while (nf < food.length)
			{
				if(food[f].equals(moveInto))
				{
					// skip
				}
				else
				{
					newFood[nf]=food[f];
					nf++;
				}
				f++;
			}
			returnBoard.setFood(newFood);
			
			tempBody = Arrays.copyOfRange(yourBody, 1, yourBody.length);
		} else {
			offset = 0;
			tempBody = Arrays.copyOfRange(yourBody, 1, yourBody.length - 1);

		}

		Coordinate[] newBody = new Coordinate[yourBody.length + offset];

		newBody[0] = moveInto;

		for (int i = 0; i < tempBody.length; i++) {
			newBody[i + 1] = tempBody[i];
		}

		Battlesnake newYou = new Battlesnake(you.getId(), you.getName(), you.getHealth() - 1, newBody, you.getLatency(),
				newBody[0], newBody[newBody.length - 1], newBody.length, "", "");

		returnBoard.getSnakes()[0] = newYou;

		// move the rest of them
		// TODO

		return returnBoard;
	}
}
