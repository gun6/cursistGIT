package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Wijnen;
import be.vdab.services.WijnenService;
import be.vdab.valueobjects.Bestelbonlijnen;
import be.vdab.valueobjects.MandOnderdeel;


@WebServlet("/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/toevoegen.jsp";
	private static final String MANDJE = "mandje";
	private final transient WijnenService wijnenService = new WijnenService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Wijnen wijn = wijnenService.getWijnMetSoortEnLand(Long.parseLong(request.getParameter("wijnid")));
		request.setAttribute("wijn", wijn);
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings ("unchecked")
			Set<Bestelbonlijnen> mandje = (Set<Bestelbonlijnen>)session.getAttribute(MANDJE);
			if (mandje != null) {
				for (Bestelbonlijnen bestelbonlijn : mandje) {
					if (bestelbonlijn.getWijnid() == wijn.getId()) {
						request.setAttribute("aantal", bestelbonlijn.getAantal());
					}
				}
			}
			
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fout = null;
		int aantalFlessen = 0;
		
		try {
			aantalFlessen = Integer.parseInt(request.getParameter("aantalFlessen"));
		}
		catch (NumberFormatException | NullPointerException e) {
			fout = "tik een positief geheel getal";
		}
		
		if (aantalFlessen < 1){
			fout = "tik een geheel getal groter dan 1";
		}
		
		if (fout != null) {
			request.setAttribute("wijn", wijnenService.getWijnMetSoortEnLand(Long.parseLong(request.getParameter("wijnid"))));
			request.setAttribute("fout", fout);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
		if (fout == null) {
			//Wijnen wijn =  wijnenService.getWijnMetSoortEnLand(Long.parseLong(request.getParameter("wijnid")));
			//MandOnderdeel mandOnderdeel = new MandOnderdeel(wijn.getSoorten().getLanden().getNaam(), wijn.getSoorten().getNaam(), wijn.getJaar(), wijn.getPrijs(), Integer.parseInt(request.getParameter("aantalFlessen")), wijn.getId());
			Wijnen wijn = wijnenService.getWijn(Long.parseLong(request.getParameter("wijnid")));
			Bestelbonlijnen bestelbonlijn = new Bestelbonlijnen(Integer.parseInt(request.getParameter("aantalFlessen")), wijn.getPrijs(), wijn.getId());
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			//List<MandOnderdeel> mandje = (List<MandOnderdeel>)session.getAttribute(MANDJE);				*als direct met MandOnderdeel zou gewerkt worden
			Set<Bestelbonlijnen> mandje = (Set<Bestelbonlijnen>)session.getAttribute(MANDJE);					//minder DB aanroepen maar ev wijzigingen in naam
			if (mandje == null) {																				//worden niet bijgewerkt
				//mandje = new ArrayList<>();
				mandje = new LinkedHashSet<>();
			}
			//mandje.add(mandOnderdeel);
			if (mandje.contains(bestelbonlijn)) {
				mandje.remove(bestelbonlijn);
			}
			mandje.add(bestelbonlijn);
			session.setAttribute(MANDJE, mandje);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
		}
		
	}

}
