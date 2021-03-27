package org.pergamum.battlesnek.handlers;

import org.pergamum.battlesnek.SnekHandler;
import org.pergamum.battlesnek.api.Battlesnake;
import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Coordinate;
import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.util.BoardDirection;
import org.pergamum.battlesnek.util.CellContent;
import org.pergamum.battlesnek.util.NotPossibleException;
import org.pergamum.battlesnek.util.RelativeDirection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RightyMcRightersonJr implements SnekHandler {

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
		try {
			
			// 1. get all the points around my head
			// 2. figutre which are safe
			// 3. evaluate "points" for safe moves:
				// 3.1 how close is food?
				// 3.2 how close is wall?

			MoveResponse m = null;

			final Battlesnake you = request.getYou();
			final Board board = request.getBoard();

			// still here? try straight?
			m = makeSafeTurn(RelativeDirection.AHEAD, you, board);
			if (m != null) {
				log.debug(m.toString());
				return m;
			}

			
			m = makeSafeTurn(RelativeDirection.RIGHT, you, board);
			if (m != null) {
				log.debug(m.toString());
				return m;
			}

		
			// still here? try left
			m = makeSafeTurn(RelativeDirection.LEFT, you, board);
			if (m != null) {
				log.debug(m.toString());
				return m;
			}

			// still here? its been good, go out the way you were made, RIGHT!

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		
		MoveResponse m = new MoveResponse();
		m.setMove(BoardDirection.RIGHT.toString());
		m.setShout("<inside voice>I DON'T KNOW WHAT I AM DOING</inside voice>\n<outside voice>GO RIGHT!</right>");
		log.debug(m.toString());	
		return m;
	}

	private Coordinate getAdjacentCoordinate(Coordinate head, BoardDirection direction) throws NotPossibleException {
		Coordinate adjacent = new Coordinate();

		int x, y;

		switch (direction) {
		case UP:
			x = 0;
			y = 1;
			break;

		case RIGHT:
			x = 1;
			y = 0;
			break;

		case DOWN:
			x = 0;
			y = -1;
			break;

		case LEFT:
			x = -1;
			y = 0;
			break;

		default:
			throw new NotPossibleException();
		}

		adjacent = new Coordinate(head.getX() + x, head.getY() + y);

		return adjacent;
	}

	public BoardDirection whichWayIsMy(RelativeDirection rel, BoardDirection facing) throws NotPossibleException {
		switch (rel) {
		case RIGHT:
			switch (facing) {

			case RIGHT:
				return BoardDirection.DOWN;
			case LEFT:
				return BoardDirection.UP;
			case UP:
				return BoardDirection.RIGHT;
			case DOWN:
				return BoardDirection.LEFT;
			default:
				throw new NotPossibleException();
			}
		case AHEAD:
			switch (facing) {

			case RIGHT:
				return BoardDirection.RIGHT;
			case LEFT:
				return BoardDirection.LEFT;
			case UP:
				return BoardDirection.UP;
			case DOWN:
				return BoardDirection.DOWN;
			default:
				throw new NotPossibleException();
			}

		case BEHIND:
			switch (facing) {

			case RIGHT:
				return BoardDirection.LEFT;
			case LEFT:
				return BoardDirection.RIGHT;
			case UP:
				return BoardDirection.DOWN;
			case DOWN:
				return BoardDirection.UP;
			default:
				throw new NotPossibleException();
			}

		case LEFT:
			switch (facing) {

			case RIGHT:
				return BoardDirection.UP;
			case LEFT:
				return BoardDirection.DOWN;
			case UP:
				return BoardDirection.LEFT;
			case DOWN:
				return BoardDirection.RIGHT;
			default:
				throw new NotPossibleException();
			}
		default:
			throw new NotPossibleException();
		}

	}

	@Override
	public void start(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void end(Request request) {
		// TODO Auto-generated method stub

	}

	MoveResponse makeSafeTurn(RelativeDirection rel, Battlesnake you, Board board) {
//1. Figure out which way you are going
		try {

			BoardDirection facing = you.findDirection();

			// 2. Figure out which way is right

			BoardDirection myTurn = whichWayIsMy(rel, facing);

			// 3. Is right safe?
			Coordinate thatAWay = getAdjacentCoordinate(you.getHead(), myTurn);

			boolean isItSafe;

			CellContent cc = board.whatIsInThatSquare(thatAWay);

			switch (cc) {
			case FOOD:
				isItSafe = true;
				break; 	

			case EMPTY:
				isItSafe = true;
				break;
			default:
				isItSafe = false;
			}

			if (isItSafe) {
				MoveResponse m = new MoveResponse();
				m.setMove(myTurn.toString());
				m.setShout("I was made for this, going " + myTurn);
				return m;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

		return null;
	}

}
