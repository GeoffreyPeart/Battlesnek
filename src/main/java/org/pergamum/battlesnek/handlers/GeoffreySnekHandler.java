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
public class GeoffreySnekHandler implements SnekHandler {

	

	@Override
	public SnekInitResponse initialize() {
		SnekInitResponse response= new SnekInitResponse();
		response.setApiversion("1");
		response.setName("Phil the Cheese Snek");
		response.setAuthor("Bryan Peart, and Geoffrey Peart");
		response.setColor("#f08a45");
		response.setHead("smile");
		response.setTail("ghost");
		
		return response;
	}

	@Override
	public MoveResponse move(Request request) {
		
		log.debug(request.toString());
		
		ArrayList<String> possibleMoves = new ArrayList<String>();

		possibleMoves.add("up");
		possibleMoves.add("down");
		possibleMoves.add("left");
		possibleMoves.add("right");
	
		Board board = request.getBoard();
		
		log.info("\n"+ board.visualizeBoard()+"\n");
		
		Battlesnake you =request.getYou();

		Coordinate foodLocation = findSafeFood(board, you);
		Coordinate ourHead = you.getHead();

		ArrayList<String> preferedMove = new ArrayList<String>();
		
		
		ArrayList<String> unsafeMoves = unsafeMove(board, you);
		preferedMove = findPreferedMove(foodLocation, ourHead);
		preferedMove.removeAll(unsafeMoves);
		possibleMoves.removeAll(unsafeMoves);
		
		ArrayList<String> moves;
		
		if(preferedMove.size()>0)
		{
			moves = preferedMove;
		}
		else
		{
			moves = possibleMoves;
		}

		//possibleMoves.contains(preferedMove)
		// Choose a random direction to move in
		log.info(moves.toString()) ;
		int choice = new Random().nextInt(moves.size());
		String move = moves.get(choice);

		log.info("MOVE {}", move);

		MoveResponse response = new MoveResponse();
		response.setMove(move);
		response.setShout("moving [{"+move+"}]");
		
		return response;
		
	}

	private ArrayList<String> findPreferedMove(Coordinate foodLocation, Coordinate ourHead) {
		
		ArrayList<String> preferedMoves = new ArrayList<String>();
		
		int xDistance = ourHead.xDistance(foodLocation);
		int yDistance = ourHead.yDistance(foodLocation);

		
		if (xDistance > 0) {
			preferedMoves.add("left");
		} else if (xDistance < 0) {
			preferedMoves.add("right");
		} else {
			// figure out if food is "behind you"
		}

		if (yDistance > 0) {
			preferedMoves.add("down");
		}
		if (yDistance < 0) {
			preferedMoves.add("up");
		} else {
			// figure out if food is "behind you"
		}
		
		return preferedMoves;
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
