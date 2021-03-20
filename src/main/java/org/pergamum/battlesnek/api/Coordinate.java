package org.pergamum.battlesnek.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {
	private int x;
	private int y;
	
	public int distanceBetweenCoordinates(Coordinate other)
	{
		int distanceToX = this.x - other.x;
		int distanceToY = this.y - other.y;
		int distance = Math.abs(distanceToX) + Math.abs(distanceToY);
		
		return distance;
	}
	
	public int xDistance(Coordinate other)
	{
		return this.x - other.x;
	}
	
	public int yDistance(Coordinate other)
	{
		return this.y - other.y;
	}
}
