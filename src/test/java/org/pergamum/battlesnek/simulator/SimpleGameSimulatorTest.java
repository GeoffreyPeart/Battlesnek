package org.pergamum.battlesnek.simulator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.pergamum.battlesnek.api.Battlesnake;
import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Coordinate;
import org.pergamum.battlesnek.util.CellContent;

class SimpleGameSimulatorTest {

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
	void testAdvanceBoardWithoutGrowth() {
		// test initialization
		Board testBoard = new Board(5, 5, TEST_FOOD, TEST_HAZARDS, TEST_SNAKES, null);

		SimpleGameSimulator sgs = new SimpleGameSimulator();
		Board advancedBoard = sgs.advanceBoard(testBoard,  new Coordinate(3, 2), false);
		assertNotNull(advancedBoard);
		assertEquals(CellContent.SNAKE, advancedBoard.whatIsInThatSquare(new Coordinate(3,2)));
		assertEquals(CellContent.EMPTY, advancedBoard.whatIsInThatSquare(new Coordinate(1,4)));
		
	}
	
	
	@Test
	void testAdvanceBoardWithGrowth() {
		// test initialization
		Board testBoard = new Board(5, 5, TEST_FOOD, TEST_HAZARDS, TEST_SNAKES, null);

		SimpleGameSimulator sgs = new SimpleGameSimulator();
		Board advancedBoard = sgs.advanceBoard(testBoard,  new Coordinate(3, 2), true);
		assertNotNull(advancedBoard);
		assertEquals(CellContent.SNAKE, advancedBoard.whatIsInThatSquare(new Coordinate(3,2)));
		assertEquals(CellContent.SNAKE, advancedBoard.whatIsInThatSquare(new Coordinate(1,4)));
		
	}

}
