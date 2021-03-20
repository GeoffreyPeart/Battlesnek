package org.pergamum.battlesnek;


import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.handlers.BryanSnekHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@SpringBootApplication
@Slf4j
public class BattlesnekAppApplication {

	private SnekHandler handler = new BryanSnekHandler();
	
	public static void main(String[] args) {
		SpringApplication.run(BattlesnekAppApplication.class, args);
	}


	@GetMapping("/")
	public SnekInitResponse initializeSnake() {
		log.info("initializeSnake - Entry");
		SnekInitResponse response = handler.initialize();
		//SnekInitResponse response = new SnekInitResponse("1", "", "#003B6F", "silly", "skinny", "1");

		/// response.setApiversion("1");

		// , "", "#003B6F", "silly", "skinny", "1");

		return response;
	}

	@PostMapping("/start")
	public void start(@RequestBody Request req) {
		log.info(req.toString());
		handler.start(req);
	}

	@PostMapping("/end")
	public void end(@RequestBody Request req) {
		log.info(req.toString());
		handler.end(req);
	}

	@PostMapping("/move")
	public @ResponseBody MoveResponse move(@RequestBody Request req)

	{
		log.info(req.toString());
		MoveResponse response = handler.move(req);
		log.info(response.toString());
		return response;
	}
}
