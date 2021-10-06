/**
 * 
 */
package org.pergamum.battlesnek.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.pergamum.battlesnek.api.Battlesnake;
import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Coordinate;
import org.pergamum.battlesnek.api.Game;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.Ruleset;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.util.BoardDirection;
import org.pergamum.battlesnek.util.CellContent;

/**
 * @author geoffreypeart
 *
 */
class RoborianTreeSnekTest {

	
	@Test
	void testEvaluateMove()
	{

		RoborianTreeSnek testSnekHandler = new RoborianTreeSnek();
		
		testSnekHandler.move(TEST_REQUEST);
	}
	
	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#initialize()}.
	 */
	@Test
	void testInitialize() {

		RoborianTreeSnek testSnekHandler = new RoborianTreeSnek();
		SnekInitResponse response = testSnekHandler.initialize();
		
		assertNotNull(response);
		assertNotNull(response.getName());
		assertNotNull(response.getAuthor());
		assertNotNull(response.getColor());
		assertNotNull(response.getHead());
		assertNotNull(response.getTail());
		assertNotNull(response.getApiversion());
		assertEquals("1",response.getApiversion());
			
	}

	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#move(org.pergamum.battlesnek.api.Request)}.
	 */
	@Test
	void testMoveInputValidation() {
		
		RoborianTreeSnek testHandler = new RoborianTreeSnek();
		assertEquals(BoardDirection.RIGHT.toString(), testHandler.move(null).getMove());
		
		
		// setup mocks
		// TODO use annotations?
		Request request = mock(Request.class);
		Board board = mock(Board.class);
		
		Battlesnake me, them;
		me = mock(Battlesnake.class);
		them = mock(Battlesnake.class);
		Battlesnake snakes[] = new Battlesnake[] {me, them};
		
		
		
		when(request.getBoard()).thenReturn(board);
		when(board.getSnakes()).thenReturn(snakes);
		
	//	assertEquals(BoardDirection.RIGHT.toString(),testHandler.move(request).getMove());
	//	
		
		
	}

	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#start(org.pergamum.battlesnek.api.Request)}.
	 */
	@Test
	void testStart() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#end(org.pergamum.battlesnek.api.Request)}.
	 */
	@Test
	void testEnd() {
		//fail("Not yet implemented");
	}
	
	@Test void tapForSyrup() throws NoSuchMethodException, SecurityException
	{
		RoborianTreeSnek snekHandler = new RoborianTreeSnek();
		
		// Arrays.stream(snekHandler.getClass().getMethods()).forEach(meth -> meth.setAccessible(true)); ;
		
		// Method m = snekHandler.getClass().getMethod("tapForSyrup", TreeNode.class);
		
	}
	
	public static Request generateRequest() {
		/*
		 * game=Game(id=356b90c8-98f8-4004-9950-06a974cde9c3, ruleset=Ruleset(name=solo, version=v1.0.22), timeout=500)
		 */
		Ruleset ruleset = new Ruleset("solo", "v1.0.22");
		Game game = new Game("356b90c8-98f8-4004-9950-06a974cde9c3", ruleset, 500);
		
		int turn = 3;
		
		/*
		 *  board=Board(height=11, width=11, 
		 *  food=[Coordinate(x=8, y=0), Coordinate(x=5, y=5)], 
		 *  hazards=[], 
		 *  snakes=[
		 *  	Battlesnake(id=gs_VKvgRWXSfMf7G67m88MJvxmP, name=CheeseSnek, health=97, body=[Coordinate(x=10, y=3), Coordinate(x=9, y=3), Coordinate(x=9, y=2)],
		 *  	 latency=308, 
		 *  	head=Coordinate(x=10, y=3), 
		 *  	tail=null, 
		 *  	length=3, 
		 *  	shout=I AM ROBORI! <translation>I am going --|right|--, squad=)], 
		 *  cellContent=null)
		 */
		Coordinate[] food = new Coordinate[] {
				new Coordinate(8,0),
				new Coordinate(5,5)
				
		};
		Coordinate[] hazards = new Coordinate[] {};
		
		Battlesnake battlesnake =new Battlesnake(
				"gs_VKvgRWXSfMf7G67m88MJvxmP",
				"CheeseSnek",
				97,
				new Coordinate[] {
						new Coordinate(10,3),
						new Coordinate(9,3),
						new Coordinate(9,2)
				},
				"308",
				new Coordinate(10,3),
				null,
				3,
				"Some random words",
				""
				);
		
		Battlesnake[] snakes = new Battlesnake[] { battlesnake };
		
		CellContent[][] cellContent = null;
		
		
		Board board = new Board(11,11,food, hazards, snakes, cellContent);
		
		
		Request r = new Request(game, turn, board, battlesnake); 
		
		return r;
		
		
	}
	
	private static final Request TEST_REQUEST = generateRequest();
	
	private static final String JSON_REQUEST = "Request(game=Game(id=356b90c8-98f8-4004-9950-06a974cde9c3, ruleset=Ruleset(name=solo, version=v1.0.22), timeout=500), turn=3, board=Board(height=11, width=11, food=[Coordinate(x=8, y=0), Coordinate(x=5, y=5)], hazards=[], snakes=[Battlesnake(id=gs_VKvgRWXSfMf7G67m88MJvxmP, name=CheeseSnek, health=97, body=[Coordinate(x=10, y=3), Coordinate(x=9, y=3), Coordinate(x=9, y=2)], latency=308, head=Coordinate(x=10, y=3), tail=null, length=3, shout=I AM ROBORI!"
		+"<translation>I am going --|right|--, squad=)], cellContent=null), you=Battlesnake(id=gs_VKvgRWXSfMf7G67m88MJvxmP, name=CheeseSnek, health=97, body=[Coordinate(x=10, y=3), Coordinate(x=9, y=3), Coordinate(x=9, y=2)], latency=308, head=Coordinate(x=10, y=3), tail=null, length=3, shout=I AM ROBORI!"
		+	"<translation>I am going --|right|--, squad=))";


}
