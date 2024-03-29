package org.pergamum.battlesnek.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private static final MoveResponse  DEFAULT_MOVE_RESPONSE = new MoveResponse();
	static {
		DEFAULT_MOVE_RESPONSE.setMove(BoardDirection.RIGHT.toString());
		DEFAULT_MOVE_RESPONSE.setShout("I AM ROBORI!\\n<translation> Defaulting Right");
	}
	
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
		long timer = System.currentTimeMillis();

	
		
		if(null == request)
		{
			// failed basic input validation
			return DEFAULT_MOVE_RESPONSE;
		}
		
		log.debug(request.toString());
		
		MoveResponse response = new MoveResponse();

		Board board = request.getBoard();

		TreeNode<Board> root = new TreeNode<Board>();
		root.setNodeContent(board);

		waterTree(root, MAX_DEPTH, 0);

		BoardDirection move = tapForSyrup(root);

		if (null == move) {
			move = BoardDirection.RIGHT;
		}

		response.setMove(move.toString());
		response.setShout("I AM ROBORI!\n<translation>I am going --|" + response.getMove() + "|--");
		log.info(response.toString());
		timer = System.currentTimeMillis() - timer;
		
		log.info("response time: --|" + timer + "|--");
	
		return response;
	}

	// find the deepest path.
	private BoardDirection tapForSyrup(TreeNode<Board> root) {

		int leftDepth = 0, rightDepth = 0, upDepth = 0, downDepth = 0;

		if (root.getLeftChild() != null) {
			leftDepth = maxDepth(root.getLeftChild(), 0);
		}
		if (root.getUpChild() != null) {
			upDepth = maxDepth(root.getUpChild(), 0);
		}
		if (root.getDownChild() != null) {
			downDepth = maxDepth(root.getDownChild(), 0);
		}
		if (root.getRightChild() != null) {
			rightDepth = maxDepth(root.getRightChild(), 0);
		}

		Map<Integer, BoardDirection> testingConcept = new HashMap<Integer, BoardDirection>();
		
		if (leftDepth >= 0) {
			testingConcept.put(leftDepth, BoardDirection.LEFT);
		}
		if (upDepth >= 0) {
			testingConcept.put(upDepth, BoardDirection.UP);
		}
		if (rightDepth >= 0) {
			testingConcept.put(rightDepth, BoardDirection.RIGHT);
		}
		if (downDepth >= 0) {
			testingConcept.put(downDepth, BoardDirection.DOWN);
		}

		if (testingConcept.size() <= 0) {
			return BoardDirection.RIGHT;
		}

		List<Integer> sl = new ArrayList<Integer>();
		sl.addAll(testingConcept.keySet());

		Collections.sort(sl);
		log.info("Sorted List " + sl);

		log.info("testingConcept " + testingConcept);
		return testingConcept.get(sl.get(sl.size()-1));

	}

	private int maxDepth(TreeNode<Board> board, int currentDepth) {
		log.info("Tree walk - entry - current Depth: " + currentDepth);

		int leftDepth = -1, rightDepth = -1, upDepth = -1, downDepth = -1;

		if (board.getLeftChild() != null) {
			leftDepth = maxDepth(board.getLeftChild(), currentDepth + 1);
		}
		if (board.getUpChild() != null) {
			upDepth = maxDepth(board.getUpChild(), currentDepth + 1);
		}
		if (board.getDownChild() != null) {
			leftDepth = maxDepth(board.getDownChild(), currentDepth + 1);
		}
		if (board.getRightChild() != null) {
			leftDepth = maxDepth(board.getRightChild(), currentDepth + 1);
		}

		log.info("Tree walk - post recursion - left |" + leftDepth + "| rightDepth |" + rightDepth + "| upDepth |"
				+ upDepth + "| downDepths |" + downDepth + "|");

		if(-1 == leftDepth && -1 == rightDepth && upDepth ==-1 &&downDepth == -1)
		{
			return currentDepth;
		}
		
		// TODO fix this terrible code
		if (leftDepth > rightDepth) {
			if (leftDepth > upDepth) {
				if (leftDepth > downDepth) {
					log.info("return left");
					return leftDepth;
				} else {
					log.info("return down");
					return downDepth;
				}

			} else {
				if (upDepth > downDepth) {
					log.info("return up");
					return upDepth;
				} else {
					log.info("return down");
					return downDepth;
				}
			}

		} else {
			if (rightDepth > upDepth) {
				if (rightDepth > downDepth) {
					log.info("return right");
					return rightDepth;
				} else {
					log.info("return down");
					return downDepth;
				}
			} else {
				if (upDepth > downDepth) {
					log.info("return up");
					return upDepth;
				} else {
					log.info("return down");
					return downDepth;
				}
			}
		}

	}

	private void waterTree(TreeNode<Board> root, int maxDepth, int depth) {

		log.info("current depth: |" + depth + "|");
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
			waterTree(leftNode, maxDepth, depth + 1);
		}
		if (null != right) {
			TreeNode<Board> rightNode = new TreeNode<Board>(root, right);
			root.setRightChild(rightNode);
			waterTree(rightNode, maxDepth, depth + 1);
		}
		if (null != up) {
			TreeNode<Board> upNode = new TreeNode<Board>(root, up);
			root.setUpChild(upNode);
			waterTree(upNode, maxDepth, depth + 1);
		}
		if (null != down) {
			TreeNode<Board> downNode = new TreeNode<Board>(root, down);
			root.setDownChild(downNode);
			waterTree(downNode, maxDepth, depth + 1);

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
