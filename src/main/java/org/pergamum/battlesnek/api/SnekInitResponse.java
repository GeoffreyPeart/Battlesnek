package org.pergamum.battlesnek.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class SnekInitResponse
{
	
	
	private String apiversion;
	
	private String name; 
	
	private String author;
	
	private String color;
	
	private String head;
	
	private String tail;
	
	private String version;
}
