package be.vdab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/")
class WerknemerController {
	private final static String VIEW = "index";
	private final WerknemerService werknemerSerivce;
	
	public WerknemerController(WerknemerService werknemerSerivce) {
		this.werknemerSerivce = werknemerSerivce;
		}
	
	@GetMapping
	ModelAndView index() {
		return new ModelAndView(VIEW).addObject(new VoornaamForm());
	}
	
	@GetMapping(params = "voornaam")
	ModelAndView index(@Validated VoornaamForm voornaamForm,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView(VIEW);
	}
		return new ModelAndView(VIEW, "werknemers",werknemerSerivce.findByVoornaam(voornaamForm.getVoornaam()));
	}
}
