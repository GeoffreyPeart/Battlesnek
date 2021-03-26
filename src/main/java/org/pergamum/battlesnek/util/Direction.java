package org.pergamum.battlesnek.util;

public enum Direction {
	
	
	UP("UP"), DOWN("DOWN"),LEFT("LEFT"),RIGHT("RIGHT");
	
	String direction;
	
	private Direction(String direction)
	{
		this.direction = direction;
	}
}
