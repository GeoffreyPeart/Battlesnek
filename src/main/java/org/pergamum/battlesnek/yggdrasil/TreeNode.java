package org.pergamum.battlesnek.yggdrasil;

import org.pergamum.battlesnek.api.Battlesnake;
import org.pergamum.battlesnek.api.Board;
import org.pergamum.battlesnek.api.Game;
import org.pergamum.battlesnek.api.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<T> {

	public TreeNode(TreeNode<T> parent, T content) {
		this.nodeContent = content;
		this.parent = parent;
	}

	private T nodeContent;
	private TreeNode<T> parent;
	
	// these are board directions, not tree directions
	private TreeNode<T> leftChild, rightChild, upChild, downChild; 
	
}
