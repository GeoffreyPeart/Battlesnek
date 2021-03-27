package org.pergamum.battlesnek.util;

public enum BoardDirection {
	
	
	UP("up"), DOWN("down"),LEFT("left"),RIGHT("right");
	
	String direction;
	
	private BoardDirection(String direction)
	{
		this.direction = direction;
	}
	
	public String toString()
	{
		return direction;
	}
}
