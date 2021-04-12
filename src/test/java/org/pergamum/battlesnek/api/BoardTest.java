package org.pergamum.battlesnek.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pergamum.battlesnek.util.CellContent;

class BoardTest extends Board {

	@Test
	void testVisualizeBoard() {
		//fail("Not yet implemented");
	}

	
	/*
	 * Board Test Board layout.
	 */
	//private static final CellContent[][] CELL_CONTENT = new CellContent[11][11];
	
	
	private static final CellContent[][] CELL_CONTENT =  {
			{CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY},
			{CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY},
			{CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY},
			{CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY},
			{CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY}
	};
	
	@Test
	void testWhatIsInThatSquare() {
		Board board = new Board();
		board.setCellContent(CELL_CONTENT);
		board.setWidth(5);
		board.setHeight(5);
		assertEquals(CellContent.EMPTY, board.whatIsInThatSquare(new Coordinate(0,0)));
	}

}
