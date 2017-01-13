package be.vdab.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bier;
import be.vdab.entities.Brouwer;
import be.vdab.services.BierService;
import be.vdab.services.BrouwerService;
import be.vdab.valueobjects.AantalForm;
import be.vdab.valueobjects.Bestelbonlijn;
import be.vdab.web.Mandje;

@Controller
@RequestMapping("/brouwers")
class BrouwersController {
	private final static String BROUWERS_VIEW = "brouwers";
	private final static String BIEREN_VIEW = "bieren";
	private final static String BIER_VIEW = "bier";
	private final BrouwerService brouwerService;
	private final BierService bierService;
	private final Mandje mandje;
	
	public BrouwersController(BrouwerService brouwerService,BierService bierService,Mandje mandje) {
		this.brouwerService = brouwerService;
		this.bierService = bierService;
		this.mandje = mandje;
	}
	
	@GetMapping
	ModelAndView brouwers(){
		return new ModelAndView(BROUWERS_VIEW,"brouwers",brouwerService.findAll());
	}
	
	@GetMapping("bieren/{bierid}")
	ModelAndView bier(@PathVariable String bierid){
		AantalForm aantalForm = new AantalForm();
		aantalForm.setAantal(1);
		Bier bier = bierService.read(Long.parseLong(bierid));
		if (mandje!=null && !mandje.isEmpty()) {
			List<Bestelbonlijn> winkelwagen = mandje.getMandje();
			Bestelbonlijn teVerwijderenLijn = new Bestelbonlijn();
			for (Bestelbonlijn bestelbonlijn : winkelwagen) {
				if (bestelbonlijn.getBier().getId() == bier.getId()) {
					aantalForm.setAantal(bestelbonlijn.getAantal());
					teVerwijderenLijn = bestelbonlijn;
				}
			}
			mandje.removeLijn(teVerwijderenLijn);
		}
		return new ModelAndView(BIER_VIEW,"bier",bier).addObject(aantalForm);
	}
	
	@GetMapping("{brouwernaam}")
	ModelAndView bieren(@PathVariable String brouwernaam){
		Brouwer brouwer = brouwerService.findByNaam(brouwernaam);
		return new ModelAndView(BIEREN_VIEW,"brouwer",brouwer).addObject("bieren",bierService.findByBrouwerOrderByName(brouwer));
	}
	
	
}
