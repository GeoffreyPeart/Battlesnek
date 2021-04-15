package org.pergamum.battlesnek.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pergamum.battlesnek.util.CellContent;

class BoardTest extends Board {

	
	@Test
	void testVisualizeBoard() {
		// fail("Not yet implemented");
	}

	/*
	 * Board Test Board layout.
	 */
	// private static final CellContent[][] CELL_CONTENT = new CellContent[11][11];

	private static final CellContent[][] CELL_CONTENT = {
			{ CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY },
			{ CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY },
			{ CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY },
			{ CellContent.FOOD, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY },
			{ CellContent.EMPTY, CellContent.SNAKE, CellContent.EMPTY, CellContent.EMPTY, CellContent.EMPTY } };

	private static final Coordinate[] TEST_FOOD = { new Coordinate(0, 0) };
	private static final Coordinate[] TEST_HAZARDS = { new Coordinate(1, 1) };

	private static final Coordinate[] TEST_SNAKE_BODY = { new Coordinate(3, 3), new Coordinate(3, 4),
			new Coordinate(2, 4), new Coordinate(1, 4) };
	private static final Battlesnake TEST_SNAKE = new Battlesnake("TEST ID", "TEST SNAKE", 100, TEST_SNAKE_BODY, "Late",
			TEST_SNAKE_BODY[0], TEST_SNAKE_BODY[TEST_SNAKE_BODY.length - 1], TEST_SNAKE_BODY.length, "I'M A TEST SNAKE",
			"SOLO");

	private static final Battlesnake[] TEST_SNAKES = { TEST_SNAKE };

	@Test
	void testWhatIsInThatSquare() {

		// test coordinate checking
		Board board = new Board();
		board.setCellContent(CELL_CONTENT);
		board.setWidth(5);
		board.setHeight(5);
		assertEquals(CellContent.EMPTY, board.whatIsInThatSquare(new Coordinate(0, 0)));
		assertEquals(CellContent.EDGE, board.whatIsInThatSquare(new Coordinate(6, 3)));
		assertEquals(CellContent.EDGE, board.whatIsInThatSquare(new Coordinate(-1, 3)));
		assertEquals(CellContent.EDGE, board.whatIsInThatSquare(new Coordinate(3, -1)));
		assertEquals(CellContent.EDGE, board.whatIsInThatSquare(new Coordinate(3, 6)));

		// test initialization
		Board board2 = new Board(5, 5, TEST_FOOD, TEST_HAZARDS, TEST_SNAKES, null);

		assertEquals(CellContent.FOOD, board2.whatIsInThatSquare(new Coordinate(0, 0)));
		assertEquals(CellContent.EDGE, board2.whatIsInThatSquare(new Coordinate(6, 3)));
		assertEquals(CellContent.EDGE, board2.whatIsInThatSquare(new Coordinate(-1, 3)));
		assertEquals(CellContent.EDGE, board2.whatIsInThatSquare(new Coordinate(3, -1)));
		assertEquals(CellContent.EDGE, board2.whatIsInThatSquare(new Coordinate(3, 6)));
		assertEquals(CellContent.HAZARD, board2.whatIsInThatSquare(new Coordinate(1, 1)));

		assertEquals(CellContent.SNAKE, board2.whatIsInThatSquare(TEST_SNAKE_BODY[2]));

	}

	@Test
	void testCopy() {
		Board testBoard =  new Board(5, 5, TEST_FOOD, TEST_HAZARDS, TEST_SNAKES, null);


		Board copyBoard = testBoard.copy();

		// they are equal but different
		assertEquals(testBoard, copyBoard);
		assertNotSame(testBoard, copyBoard);
		assertEquals(CellContent.FOOD, copyBoard.whatIsInThatSquare(new Coordinate(0, 0)));
		assertEquals(CellContent.EMPTY, copyBoard.whatIsInThatSquare(new Coordinate(3, 1)));
	}

}
