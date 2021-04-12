package org.pergamum.battlesnek.api;


import org.pergamum.battlesnek.util.BoardDirection;
import org.pergamum.battlesnek.util.NotPossibleException;
import org.pergamum.battlesnek.util.RelativeDirection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Battlesnake {
	private String id;
	private String name;
	private int health;
	private Coordinate[] body;
	private String latency;
	private Coordinate head;
	private Coordinate tail;
	private int length;
	private String shout;
	private String squad;
	
	public String findFacing() {
		
		return findDirection().toString();
	}
	
	public BoardDirection findDirection()
	{
		Coordinate snekNek = this.body[1];
		Coordinate head = this.head;
		
		if (head.getX() == snekNek.getX()) {
			if (head.getY() > snekNek.getY()) {
				return BoardDirection.UP;

			} else {
				return BoardDirection.DOWN;

			}

		} else {
		
			if (head.getX() > snekNek.getX()) {
				return BoardDirection.RIGHT;
			}

			else {
				return BoardDirection.LEFT;
			}

		}
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
	
	public Coordinate getAdjacentCoordinate(BoardDirection direction) throws NotPossibleException {
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

		adjacent = new Coordinate(this.head.getX() + x, this.head.getY() + y);

		return adjacent;
	}
}
