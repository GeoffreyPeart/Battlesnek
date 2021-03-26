package org.pergamum.battlesnek.handlers;

import org.pergamum.battlesnek.SnekHandler;
import org.pergamum.battlesnek.api.Coordinate;
import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.util.BoardDirection;
import org.pergamum.battlesnek.util.CellContent;

import org.pergamum.battlesnek.util.RelativeDirection;

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
			BoardDirection facing = request.getYou().findDirection();

			// 2. Figure out which way is right

			BoardDirection myRight = whichWayIsMy(RelativeDirection.RIGHT,facing);

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
			else
			{
		//		Direction straightAhead = whichWayIs(Direction.)
				// 4. is straight safe go straight, else go left.
				return null;
			}
			
			
		} catch (Exception e) {
			MoveResponse m = new MoveResponse();
			m.setMove(BoardDirection.RIGHT.toString());
			m.setShout("<inside voice>I DON'T KNOW WHAT I AM DOING</inside voice>\n<outside voice>GO RIGHT!</right>");
			return m;
		}
	}

	private Coordinate getAdjacentCoordinate(Coordinate head,  BoardDirection direction)
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

	private BoardDirection whichWayIsMy(RelativeDirection rel, BoardDirection facing) throws NotPossibleException {
		switch(rel)
		{
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

}
