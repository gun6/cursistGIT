package be.vdab.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Bier;
import be.vdab.services.BestelbonService;
import be.vdab.services.BierService;
import be.vdab.valueobjects.AantalForm;
import be.vdab.valueobjects.Bestelbonlijn;
import be.vdab.valueobjects.KlantForm;
import be.vdab.web.Mandje;


@Controller
@RequestMapping("/winkelwagen")
class WinkelwagenController {
	private final Mandje mandje;
	private final static String VIEW = "redirect:winkelwagen";
	private final static String GET_VIEW = "winkelwagen";
	private final static String BEVESTIG_VIEW = "bevestigen";
	private final static String BEVESTIG_REDIRECT = "redirect:/winkelwagen/bevestiging";
	private final static String TEST_VIEW = "test";
	private final BierService bierService;
	private final BestelbonService bestelbonService;
	
	public WinkelwagenController(Mandje mandje,BierService bierService,BestelbonService bestelbonService) {
		this.mandje = mandje;
		this.bierService = bierService;
		this.bestelbonService = bestelbonService;
	}
	
	@PostMapping("bevestiging")
	ModelAndView sendToConfirm(KlantForm klantForm){
		Bestelbon bestelbon = new Bestelbon(klantForm.getNaam(), klantForm.getAdres().getStraat(), klantForm.getAdres().getHuisNr(), klantForm.getAdres().getPostcode(), klantForm.getAdres().getGemeente(), mandje.getMandje());
		if (bestelbon.getNaam()!=null) {
			return new ModelAndView(TEST_VIEW,"var",bestelbon);
		}
//		bestelbonService.create(bestelbon);
		return new ModelAndView(BEVESTIG_REDIRECT);
	}
	
	@GetMapping("bevestiging")
	ModelAndView confirm(){
		return new ModelAndView(BEVESTIG_VIEW);
	}

	@PostMapping(params = {"aantal","bierId"})
	ModelAndView winkelwagen(AantalForm aantalForm){
		if (aantalForm != null) {
			Bier bier = bierService.read(aantalForm.getBierId());
			Bestelbonlijn bestelbonlijn = new Bestelbonlijn(aantalForm.getAantal(), bier.getPrijs(), bier);
			mandje.addLijn(bestelbonlijn);
		}
		return new ModelAndView(VIEW);
	}
	
	@GetMapping
	ModelAndView toonWinkelwagen(){
		KlantForm klantForm = new KlantForm();
		List<Bestelbonlijn> lijnen = mandje.getMandje();
		if (lijnen == null) {
			lijnen = new ArrayList<>();
		}
		return new ModelAndView(GET_VIEW,"lijnen",lijnen).addObject(klantForm);
	}
	
	
}
