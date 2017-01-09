package be.vdab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.BierService;

@Controller
@RequestMapping("/")
class WelkomController {
	private final static String VIEW = "index";
	private final BierService bierService;
	
	public WelkomController(BierService bierService) {
		this.bierService = bierService;
	}
	
	@GetMapping
	ModelAndView index(){
		return new ModelAndView(VIEW,"aantalBieren",bierService.findAantalBieren());
	}
}
