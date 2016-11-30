package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.daos.KlantDAO;
import be.vdab.daos.VoorstellingenDAO;
import be.vdab.entities.Klant;



@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";
	private static final String TITLE = "nieuwe klant";
	private static final String REDIRECT_URL = "%s/bevestiging.htm";
	private static final transient VoorstellingenDAO voorstellingendao = new VoorstellingenDAO();
	private static final transient KlantDAO klantdao = new KlantDAO();


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title",TITLE);
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			List<String> fouten =(List<String>) session.getAttribute("fouten");
			request.setAttribute("fouten", fouten);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> fouten = new ArrayList<>();
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		if (gebruikersnaam == null || gebruikersnaam.trim().isEmpty()){ fouten.add("gebruikersnaam niet ingevuld.");}
		else {
			if (klantdao.checkKlantgegevens(gebruikersnaam)) fouten.add("Deze gebruikersnaam komt reeds voor in ons systeem");
		}
		String voornaam = request.getParameter("voornaam");
		if (voornaam == null || voornaam.trim().isEmpty()) fouten.add("voornaam niet ingevuld.");
		String familienaam = request.getParameter("familienaam");
		if (familienaam == null || familienaam.trim().isEmpty()) fouten.add("familienaam niet ingevuld.");
		String straat = request.getParameter("straat");
		if (straat == null || straat.trim().isEmpty()) fouten.add("straat niet ingevuld.");
		String huisnr = request.getParameter("huisnr");
		if (huisnr == null || huisnr.trim().isEmpty()) fouten.add("huisnr niet ingevuld.");
		String postcode = request.getParameter("postcode");
		if (postcode == null || postcode.trim().isEmpty()) fouten.add("postcode niet ingevuld.");
		String gemeente = request.getParameter("gemeente");
		if (gemeente == null || gemeente.trim().isEmpty()) fouten.add("gemeente niet ingevuld.");
		String paswoord = request.getParameter("paswoord");
		if (paswoord == null || paswoord.trim().isEmpty()) fouten.add("paswoord niet ingevuld.");
		String herhaalpaswoord = request.getParameter("herhaalpaswoord");
		if (!paswoord.equals(herhaalpaswoord)) fouten.add("Paswoord en herhaal paswoord zijn niet hetzelfde.");
		
			
		if (fouten.isEmpty()) {
			Klant klant = new Klant(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, herhaalpaswoord);
			klantdao.insertNew(klant);
			HttpSession session = request.getSession();
			session.setAttribute("klant", gebruikersnaam);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("fouten", fouten);
			response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		}
		
	}
	
	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource){
		klantdao.setDataSource(dataSource);
	}
}
