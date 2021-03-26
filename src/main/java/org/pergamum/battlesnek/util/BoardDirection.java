package org.pergamum.battlesnek.util;

public enum BoardDirection {
	
	
	UP("UP"), DOWN("DOWN"),LEFT("LEFT"),RIGHT("RIGHT");
	
	String direction;
	
	private BoardDirection(String direction)
	{
		this.direction = direction;
	}
}
