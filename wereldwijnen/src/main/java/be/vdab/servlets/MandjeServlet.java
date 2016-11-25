package be.vdab.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbonnen;
import be.vdab.entities.Wijnen;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.services.BestelbonnenService;
import be.vdab.services.WijnenService;
import be.vdab.valueobjects.Bestelbonlijnen;
import be.vdab.valueobjects.MandOnderdeel;


@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "%s/bevestiging.htm?bon=%d";
	private static final String MANDJE = "mandje";
	private static final String FOUT_BOODSCHAP_AFWEZIG = "Dit veld is verplicht";
	private static final String FOUT_BOODSCHAP_VERKEERD = "Dit veld is niet correct ingevuld";
	private final transient BestelbonnenService bestelbonnenService = new BestelbonnenService();
	private final transient WijnenService wijnenService = new WijnenService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Set<Bestelbonlijnen> mandjeLijnen = (Set<Bestelbonlijnen>) session.getAttribute(MANDJE);
		Set<MandOnderdeel> bestelLijnen = new LinkedHashSet<>();
		for (Bestelbonlijnen bestelbonlijn : mandjeLijnen) {
			Wijnen wijn = wijnenService.getWijnMetSoortEnLand(bestelbonlijn.getWijnid());
			MandOnderdeel onderdeel = new MandOnderdeel(wijn.getSoorten().getLanden().getNaam(), wijn.getSoorten().getNaam(), wijn.getJaar(), bestelbonlijn);
			bestelLijnen.add(onderdeel);
			// omzetten naar ander object om alle parameters te kunnen meegeven
			//blijven werken met prijs bestelbonlijnen als de prijs verandert mag dit niet meeveranderen
		}
		request.setAttribute("bestellijnen", bestelLijnen);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		
		String naam = request.getParameter("naam");
		if (naam == null || naam.trim().isEmpty()) {
			fouten.put("naam", FOUT_BOODSCHAP_AFWEZIG);
		}
		
		String straat = request.getParameter("straat");
		if (straat == null || straat.trim().isEmpty()) {
			fouten.put("straat", FOUT_BOODSCHAP_AFWEZIG);
		}
		
		String huisnummer = request.getParameter("huisnummer");
		if (huisnummer == null || huisnummer.trim().isEmpty()) {				//Integer.parseInt(null) geeft ook numberformatexception
			fouten.put("huisnummer", FOUT_BOODSCHAP_AFWEZIG);
		}
		else {
			int test;
			try {
				test = Integer.parseInt(huisnummer);
				if (test < 1) {
					fouten.put("huisnummer", FOUT_BOODSCHAP_VERKEERD);
				}
			} 
			catch (NumberFormatException e) {
				fouten.put("huisnummer", FOUT_BOODSCHAP_VERKEERD);
			}
		}
		
		String postcode = request.getParameter("postcode");
		if (postcode == null || postcode.trim().isEmpty()) {				
			fouten.put("postcode", FOUT_BOODSCHAP_AFWEZIG);
		}
		else {
			if (!postcode.matches("^\\d{4}$")) {
				fouten.put("postcode", FOUT_BOODSCHAP_VERKEERD);
			} 
		}
		
		String gemeente = request.getParameter("gemeente");
		if (gemeente == null || gemeente.trim().isEmpty()) {
			fouten.put("gemeente", FOUT_BOODSCHAP_AFWEZIG);
		}
		
		int bestelwijze = -1;
		if (request.getParameter("bestelwijze") == null) {
			fouten.put("bestelwijze", FOUT_BOODSCHAP_AFWEZIG);
		}
		else {
			bestelwijze = Integer.parseInt(request.getParameter("bestelwijze"));
		}
		
		if (fouten.isEmpty()) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Bestelbonlijnen> bestelbonlijnen =  (Set<Bestelbonlijnen>) session.getAttribute(MANDJE);
			Bestelbonnen bestelbon = new Bestelbonnen(new Date(), bestelwijze, gemeente, huisnummer, naam, postcode, straat,bestelbonlijnen, 1);	
			//@SuppressWarnings("unchecked")
			//Set<MandOnderdeel> mandje = (Set<MandOnderdeel>)session.getAttribute(MANDJE);				*als direct met MandOnderdeel zou gewerkt worden
			//for (MandOnderdeel mandOnderdeel : mandje) {													minder DB aanroepen maar ev wijzigingen in naam
				//Bestelbonlijnen bestelbonlijn = mandOnderdeel.getBestelbonlijn();							worden niet bijgewerkt
				//bestelbon.addBestelbonlijn(bestelbonlijn);
			//}
			try {
				bestelbonnenService.create(bestelbon);
				session.invalidate();
				response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(),bestelbon.getId())));
			} catch (RecordAangepastException e) {
				fouten.put("database", "Database was in gebruik, probeer opniew");
			}
			
		}
		
		if (! fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			HttpSession session = request.getSession();
			request.setAttribute(MANDJE, session.getAttribute(MANDJE));
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
