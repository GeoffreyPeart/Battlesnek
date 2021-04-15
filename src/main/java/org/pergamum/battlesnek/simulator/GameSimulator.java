/**
 * 
 */
package org.pergamum.battlesnek.simulator;

import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Coordinate;

/**
 * @author geoffreypeart
 *
 */
public interface GameSimulator {
	public Board advanceBoard(Board board, Coordinate moveInto, boolean growSnake);
		
}
