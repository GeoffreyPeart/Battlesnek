package org.pergamum.battlesnek.handlers;

import java.util.ArrayList;

import org.pergamum.battlesnek.SnekHandler;
import org.pergamum.battlesnek.api.Battlesnake;
import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Coordinate;
import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.simulator.SimpleGameSimulator;
import org.pergamum.battlesnek.util.BoardDirection;
import org.pergamum.battlesnek.util.CellContent;
import org.pergamum.battlesnek.util.NotPossibleException;
import org.pergamum.battlesnek.yggdrasil.TreeNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoborianTreeSnek implements SnekHandler {

	private static final int MAX_DEPTH = 3;

	@Override
	public SnekInitResponse initialize() {
		SnekInitResponse response = new SnekInitResponse();
		response.setApiversion("1");
		response.setName("Robori");
		response.setAuthor("Bryan Peart, and Geoffrey Peart");
		response.setColor("#D02D23");
		response.setHead("tongue");
		response.setTail("swirl");

		return response;
	}

	@Override
	public MoveResponse move(Request request) {

		MoveResponse response = new MoveResponse();

		Board board = request.getBoard();

		TreeNode<Board> root = new TreeNode<Board>();
		root.setNodeContent(board);

		waterTree(root, MAX_DEPTH, 0);
		
		BoardDirection move = tapForSyrup(root, new ArrayList<TreeNode<Board>>());
		
		if(null == move)
		{
			move = BoardDirection.RIGHT;
		}

		response.setMove(move.toString());
		response.setShout("I AM ROBORI!\n<translation>I am going --|" + response.getMove() + "|--");
		log.info(response.toString());
		return response;
	}

	// find the deepest path.
	private BoardDirection tapForSyrup(TreeNode<Board> root, ArrayList<TreeNode<Board>> arrayList) {
		
		if(null != root.getLeftChild())
		{
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	
	private void waterTree(TreeNode<Board> root, int maxDepth, int depth) {
		if (depth > maxDepth) {
			return;
		}

		Board left, right, up, down;

		
		left = evalutateMove(root, BoardDirection.LEFT);
		right = evalutateMove(root, BoardDirection.RIGHT);
		up = evalutateMove(root, BoardDirection.UP);
		down = evalutateMove(root, BoardDirection.DOWN);

		if (null != left) {
			TreeNode<Board> leftNode = new TreeNode<Board>(root, left);
			root.setLeftChild(leftNode);
			waterTree(leftNode, maxDepth, depth++);
		}
		if (null != right) {
			TreeNode<Board> rightNode = new TreeNode<Board>(root, right);
			root.setRightChild(rightNode);
			waterTree(rightNode, maxDepth, depth++);
		}
		if (null != up) {
			TreeNode<Board> upNode = new TreeNode<Board>(root, up);
			root.setLeftChild(upNode);
			waterTree(upNode, maxDepth, depth++);
		}
		if (null != down) {
			TreeNode<Board> downNode = new TreeNode<Board>(root, down);
			root.setLeftChild(downNode);
			waterTree(downNode, maxDepth, depth++);
		}

		
		
	}

	private Board evalutateMove(TreeNode<Board> root, BoardDirection direction) {

		try {
			Board board = root.getNodeContent();
			Battlesnake you = board.getSnakes()[0];
			Coordinate moveInto = you.getAdjacentCoordinate(direction);

			CellContent content = board.whatIsInThatSquare(moveInto);

			boolean growSnake = false;
			switch (content) {
			case FOOD:
				growSnake = true;

			case EMPTY:

				// these are possible moves
				break;

			case SNAKE:
				/*
				 * this special case will need to be revisited once we start anticipating where
				 * opposing snakes will go as well as snake killing.
				 */
				// TODO
				return null;

			case EDGE:
			case HAZARD:
			default:
				// these are not possible moves
				return null;
			}
			SimpleGameSimulator sim = new SimpleGameSimulator();
			Board advanceWithMove = sim.advanceBoard(board, moveInto, growSnake);

			if (null != advanceWithMove) {
				return advanceWithMove;
			}

			return null;
		} catch (NotPossibleException e) {
			e.printStackTrace();
			return null;

		}

	}

	

	@Override
	public void start(Request request) {
		log.debug(request.toString());

	}

	@Override
	public void end(Request request) {
		log.debug(request.toString());

	}

}
