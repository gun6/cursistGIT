package be.vdab.web;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filialen")
public class FiliaalController {
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private static final Logger LOGGER = Logger.getLogger(FiliaalController.class.getName());
	
	@GetMapping
	String findAll() {
		return FILIALEN_VIEW;
	}
	
	@GetMapping("toevoegen")
	String createForm() {
		return TOEVOEGEN_VIEW;
	}
	
	@PostMapping
	String create() {
//		Handler handler = new ConsoleHandler();
//		handler.setLevel(Level.ALL);
//		LOGGER.addHandler(handler);
//		LOGGER.setLevel(Level.ALL);
		LOGGER.info("filiaal record toevoegen aan database");
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
}
