package be.vdab.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private final static String VIEW = "redirect:/winkelwagen";
	private final static String GET_VIEW = "winkelwagen";
	private final static String BEVESTIG_VIEW = "bevestigen";
	private final static String BEVESTIG_REDIRECT = "redirect:/winkelwagen/bevestiging/{id}";
	private final BierService bierService;
	private final BestelbonService bestelbonService;
	
	public WinkelwagenController(Mandje mandje,BierService bierService,BestelbonService bestelbonService) {
		this.mandje = mandje;
		this.bierService = bierService;
		this.bestelbonService = bestelbonService;
	}
	
	@PostMapping("bevestiging")
	ModelAndView sendToConfirm(@Valid KlantForm klantForm,BindingResult bindingResult,RedirectAttributes redirectAttributes){
		if (bindingResult.hasErrors()) {
			return new ModelAndView(VIEW);
		}
		Bestelbon bestelbon = new Bestelbon(klantForm.getNaam(), klantForm.getStraat(), klantForm.getHuisNr(), klantForm.getPostcode(), klantForm.getGemeente(), mandje.getMandje());
		bestelbon = bestelbonService.create(bestelbon);
		long id = bestelbon.getId();
		redirectAttributes.addAttribute("id", id);
		mandje.clear();
		return new ModelAndView(BEVESTIG_REDIRECT);
	}
	
	@GetMapping("bevestiging/{id}")
	ModelAndView confirm(@PathVariable long id){
		return new ModelAndView(BEVESTIG_VIEW,"id",id);
	}

	@PostMapping(params = {"aantal","bierId"})
	ModelAndView winkelwagen(@Valid AantalForm aantalForm,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			String biernaam = bierService.read(aantalForm.getBierId()).getNaam();
			return new ModelAndView("redirect:/brouwers/bieren/"+biernaam);
		}
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
