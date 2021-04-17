package org.pergamum.battlesnek;



import java.util.HashMap;
import java.util.Map;
import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.handlers.BryanSnekHandler;
import org.pergamum.battlesnek.handlers.RightyMcRightersonJr;
import org.pergamum.battlesnek.handlers.RoborianTreeSnek;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@SpringBootApplication
@Slf4j
public class BattlesnekAppApplication {
	private Map<String, SnekHandler> handlers;
	
	//private SnekHandler handler = 
	
	public static void main(String[] args) {
		
		SpringApplication.run(BattlesnekAppApplication.class, args);
	}

	private void initHandlers()
	{
		if(null == handlers)
		{
			handlers = new HashMap<String, SnekHandler>();
			handlers.put("Robori", new RoborianTreeSnek());
			handlers.put("Moldy", new BryanSnekHandler());
			handlers.put("Righty", new RightyMcRightersonJr());
		}
		
	}
	
	public BattlesnekAppApplication() {
		super();
		initHandlers();		
	}



	@GetMapping("/{snek}/")
	public SnekInitResponse initializeSnake(@PathVariable String snek) {
		log.info("initializeSnake - Entry");

		SnekInitResponse response = handlers.get(snek).initialize();
	
		return response;
	}


	@PostMapping("/{snek}/start")
	public void start(@PathVariable String snek, @RequestBody Request req) {
		log.info(req.toString());
		handlers.get(snek).start(req);
	}


	@PostMapping("/{snek}/end")
	public void end(@PathVariable String snek, @RequestBody Request req) {
		log.info(req.toString());
	//	initHandlers();
		handlers.get(snek).end(req);
	}

	@PostMapping("/{snek}/move")
	public @ResponseBody MoveResponse move(@PathVariable String snek, @RequestBody Request req)
	{
		log.info(req.toString());
		MoveResponse response = handlers.get(snek).move(req);
		log.info(response.toString());
		return response;
	}
}
