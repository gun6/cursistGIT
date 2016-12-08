package be.vdab.web;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offertes")
public class OfferteController {
	 private static final String STAP1_VIEW = "offertes/stap1"; 
	 private static final String STAP2_VIEW = "offertes/stap2"; 
	 private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/"; 
	 private static final Logger LOGGER = Logger.getLogger(OfferteController.class.getName());
	 
	 @GetMapping("aanvraag")
	 String createform1(){
		 return STAP1_VIEW;
	 }
	 
	 @PostMapping(params = "volgende")
	 String createform1naar2(){
		 return STAP2_VIEW;
	 }
	 
	 @PostMapping(params = "vorige")
	 String createform2naar1(){
		 return STAP1_VIEW;
	 }
	 
	 @PostMapping(params = "bevestigen")
	 String create(){
		 LOGGER.info("offerte versturen via e-mail");
		 return REDIRECT_URL_NA_TOEVOEGEN;
	 }
}
