/**
 * 
 */
package org.pergamum.battlesnek.handlers;

import java.util.ArrayList;
import java.util.Random;

import org.pergamum.battlesnek.SnekHandler;
import org.pergamum.battlesnek.api.Battlesnake;
import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Coordinate;
import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geoffreypeart
 *
 */
@Slf4j
public class BryanSnekHandler implements SnekHandler {

	

	@Override
	public SnekInitResponse initialize() {
		SnekInitResponse response= new SnekInitResponse();
		response.setApiversion("1");
		response.setName("Moldy Snek");
		response.setAuthor("Bryan Peart, and Geoffrey Peart");
		response.setColor("#8a9a5b");
		response.setHead("silly");
		response.setTail("freckled");
		
		return response;
	}

	@Override
	public MoveResponse move(Request request) {
		
		log.debug(request.toString());
		
		ArrayList<String> possibleMoves = new ArrayList<String>();

	
		Board board = request.getBoard();
		
		log.info(board.toString());
		
		Battlesnake you =request.getYou();

		Coordinate foodLocation = findSafeFood(board, you);
		Coordinate ourHead = you.getHead();

		int xDistance = ourHead.xDistance(foodLocation);
		int yDistance = ourHead.yDistance(foodLocation);

		if (xDistance > 0) {
			possibleMoves.add("left");
		} else if (xDistance < 0) {
			possibleMoves.add("right");
		} else {
			// figure out if food is "behind you"
		}

		if (yDistance > 0) {
			possibleMoves.add("down");
		}
		if (yDistance < 0) {
			possibleMoves.add("up");
		} else {
			// figure out if food is "behind you"
		}


		possibleMoves.removeAll(unsafeMove(board, you));

		// Choose a random direction to move in
		log.info(possibleMoves.toString()) ;
		int choice = new Random().nextInt(possibleMoves.size());
		String move = possibleMoves.get(choice);

		log.info("MOVE {}", move);

		MoveResponse response = new MoveResponse();
		response.setMove(move);
		response.setShout("yolo I'm turning [{"+move+"}]");
		
		return response;
		
	}

	@Override
	public void start(Request request) {
		log.debug(request.toString());

	}

	@Override
	public void end(Request request) {
		log.debug(request.toString());
	}
	
	
	
	/**
	 * Food Search Algorithm - create ordered list of "nearest food" - iterate
	 * through list until we find a "safe" food - return safe food
	 *
	 * @param board    current state of board
	 * @param ourSnake current state of our snake
	 * @return location of nearest safe fruit.
	 */
	private Coordinate findSafeFood(Board board, Battlesnake ourSnake) {
		
		Coordinate[]  foods = board.getFood();

		Coordinate ourHead = ourSnake.getHead();

		Coordinate response = null;
		
		int shortestDistance = Integer.MAX_VALUE;

		for (Coordinate food : foods) {
			int distance = food.distanceBetweenCoordinates(ourHead);
			if (distance < shortestDistance) {
				response = food;
				shortestDistance = distance;
			}
		}

		return response;
	}
	
	private ArrayList<String> unsafeMove(Board board, Battlesnake ourSnake) {
		ArrayList<String> notPossibleMoves = new ArrayList<String>();

		Coordinate ourHead = ourSnake.getHead();

		String faceing = ourSnake.findFacing();


		if (ourHead.getX() == 0) {
			notPossibleMoves.add("left");
		}
		if (ourHead.getY() == 0) {
			notPossibleMoves.add("down");
		}

		if (ourHead.getX() == 11) {
			notPossibleMoves.add("up");
		}
		if (ourHead.getY() == 11) {
			notPossibleMoves.add("right");
		}

		switch (faceing) {
		case "left":
			notPossibleMoves.add("right");
			break;
		case "right":
			notPossibleMoves.add("left");
			break;
		case "up":
			notPossibleMoves.add("down");
			break;
		case "down":
			notPossibleMoves.add("up");
			break;
		default:

			break;
		}

		return notPossibleMoves;
	}
	
}
