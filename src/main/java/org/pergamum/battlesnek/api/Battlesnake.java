package org.pergamum.battlesnek.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Battlesnake {
	private String id;
	private String name;
	private int health;
	private Coordinate[] body;
	private String latency;
	private Coordinate head;
	private Coordinate tail;
	private int length;
	private String shout;
	private String squad;
	
	public String findFacing() {

		
		Coordinate snekNek = this.body[1];
		Coordinate head = this.head;
		
		if (head.getX() == snekNek.getX()) {
			if (head.getY() > snekNek.getY()) {
				return "up";

			} else {
				return "down";

			}

		} else {
		
			if (head.getX() > snekNek.getX()) {
				return "right";
			}

			else {
				return "left";
			}

		}
	}
}
