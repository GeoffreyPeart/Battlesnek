package org.pergamum.battlesnek.handlers;

import org.pergamum.battlesnek.SnekHandler;
import org.pergamum.battlesnek.api.Coordinate;
import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.util.CellContent;
import org.pergamum.battlesnek.util.Direction;

public class RightyMcRightersonJr implements SnekHandler {

	@Override
	public SnekInitResponse initialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoveResponse move(Request request) {
		try {
			// 1. Figure out which way you are going
			Direction facing = request.getYou().findDirection();

			// 2. Figure out which way is right

			Direction myRight = whichWayIsRight(facing);

			// 3. Is right safe?
			Coordinate toMyRight = getAdjacentCoordinate(request.getYou().getHead(),myRight);

			boolean isItSafe;
			
			CellContent cc = request.getBoard().whatIsInThatSquare(toMyRight);
			
			switch(cc)
			{
			case FOOD:
				isItSafe = true;
				break;
			
			case EMPTY:
				isItSafe = true;
				break;
			default:
				isItSafe = false;
			}
			
			// 3.true go right
			if(isItSafe)
			{
				MoveResponse m = new MoveResponse();
				m.setMove(myRight.toString());
				m.setShout("I was made for this, going RIGHT!");
				return m; 
			}
		
			// 3.false
			// 4. is straight safe go straight, else go left.

			// TODO Auto-generated method stub
			return null;
		} catch (Exception e) {
			MoveResponse m = new MoveResponse();
			m.setMove(Direction.RIGHT.toString());
			m.setShout("<inside voice>I DON'T KNOW WHAT I AM DOING</inside voice>\n<outside voice>GO RIGHT!</right>");
			return m;
		}
	}

	private Coordinate getAdjacentCoordinate(Coordinate head,  Direction direction)
			throws NotPossibleException {
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
		
		adjacent = new Coordinate(head.getX()+x,head.getY()+y);
		

		return adjacent;
	}

	private Direction whichWayIsRight(Direction facing) throws NotPossibleException {
		switch (facing) {
		case RIGHT:
			return Direction.DOWN;
		case LEFT:
			return Direction.UP;
		case UP:
			return Direction.RIGHT;
		case DOWN:
			return Direction.LEFT;
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

}