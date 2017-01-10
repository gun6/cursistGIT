package be.vdab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.valueobjects.BestelForm;

@Controller
@RequestMapping("/winkelwagen")
class WinkelwagenController {
	private final static String VIEW = "redirect:winkelwagen";
	
	@PostMapping(params = "bestelForm")
	ModelAndView winkelwagen(BestelForm bestelForm){
		return new ModelAndView(VIEW,"bestelForm",bestelForm);
	}
}
