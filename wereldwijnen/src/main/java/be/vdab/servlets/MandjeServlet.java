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
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.services.BestelbonnenService;
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
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute(MANDJE, session.getAttribute(MANDJE));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		
		String naam = request.getParameter("naam");
		if (naam == null || naam.isEmpty()) {
			fouten.put("naam", FOUT_BOODSCHAP_AFWEZIG);
		}
		
		String straat = request.getParameter("straat");
		if (straat == null || straat.isEmpty()) {
			fouten.put("straat", FOUT_BOODSCHAP_AFWEZIG);
		}
		
		String huisnummer = request.getParameter("huisnummer");
		if (huisnummer == null || huisnummer.isEmpty()) {				//Integer.parseInt(null) geeft ook numberformatexception
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
		if (postcode == null || postcode.isEmpty()) {				
			fouten.put("postcode", FOUT_BOODSCHAP_AFWEZIG);
		}
		else {
			if (!postcode.matches("^\\d{4}$")) {
				fouten.put("postcode", FOUT_BOODSCHAP_VERKEERD);
			} 
		}
		
		String gemeente = request.getParameter("gemeente");
		if (gemeente == null || gemeente.isEmpty()) {
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
			Set<Bestelbonlijnen> bestelbonlijnen =  new LinkedHashSet<>();
			Bestelbonnen bestelbon = new Bestelbonnen(new Date(), bestelwijze, gemeente, huisnummer, naam, postcode, straat,bestelbonlijnen, 1);
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			List<MandOnderdeel> mandje = (List<MandOnderdeel>)session.getAttribute(MANDJE);
			for (MandOnderdeel mandOnderdeel : mandje) {
				Bestelbonlijnen bestelbonlijn = new Bestelbonlijnen(mandOnderdeel.getAantal(), mandOnderdeel.getPrijs(), mandOnderdeel.getWijnId());
				bestelbon.addBestelbonlijn(bestelbonlijn);
			}
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
