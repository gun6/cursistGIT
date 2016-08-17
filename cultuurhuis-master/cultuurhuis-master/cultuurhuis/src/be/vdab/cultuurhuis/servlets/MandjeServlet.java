package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.cultuurhuis.business.MandjeItem;
import be.vdab.cultuurhuis.dao.MandjeDAO;
import be.vdab.cultuurhuis.dao.VoorstellingDAO;

@WebServlet(urlPatterns = "/mandje.htm", name = "mandjeservlet")

public class MandjeServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mandje") != null)
		{
			BigDecimal totaalPrijs = BigDecimal.ZERO;
			Map<Integer, Integer> mandjeMap = (Map<Integer, Integer>) session.getAttribute("mandje"); 	// haal bestaand mandje op van session
			
			// bereken de totaal prijs van alle reservaties in het mandje
			for(Map.Entry<Integer, Integer> entry : mandjeMap.entrySet())
			{
				BigDecimal prijs = new VoorstellingDAO().findVoorstelling(entry.getKey()).getPrijs();
				BigDecimal aantalPlaatsen = new BigDecimal(entry.getValue());
				BigDecimal totaal = prijs.multiply(aantalPlaatsen);
				totaalPrijs = totaalPrijs.add(totaal);
			}
			
			Iterable<MandjeItem> mandjeItems = new MandjeDAO().toonMandje(mandjeMap);
			request.setAttribute("mandjeItems", mandjeItems);		// plaats de volledige inhoud van reservaties terug op de request
			request.setAttribute("totaalprijs", totaalPrijs);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(request.getParameterValues("id") != null)
		{
			// indien op knop "verwijderen" werd geklikt en iets werd aangevinkt
			
			Map<Integer, Integer> mandje = (Map<Integer, Integer>)session.getAttribute("mandje");
			
			for(String id : request.getParameterValues("id"))
			{
				mandje.remove(Integer.parseInt(id));
			}
			session.setAttribute("mandje", mandje);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		}
		else
		{   
			// indien op knop "verwijderen" werd geklikt en niets werd aangevinkt
			
			BigDecimal totaalPrijs = BigDecimal.ZERO;
			Map<Integer, Integer> mandjeMap = (Map<Integer, Integer>) session.getAttribute("mandje"); // haal bestaand mandje op van session
			
			// bereken de totaal prijs van alle reservaties in het mandje
			for(Map.Entry<Integer, Integer> entry : mandjeMap.entrySet())
			{
				BigDecimal prijs = new VoorstellingDAO().findVoorstelling(entry.getKey()).getPrijs();
				BigDecimal aantalPlaatsen = new BigDecimal(entry.getValue());
				BigDecimal totaal = prijs.multiply(aantalPlaatsen);
				totaalPrijs = totaalPrijs.add(totaal);
			}
			
			Iterable<MandjeItem> mandjeItems = new MandjeDAO().toonMandje(mandjeMap);
			request.setAttribute("mandjeItems", mandjeItems);		// plaats de volledige inhoud van reservaties terug op de request
			request.setAttribute("totaalprijs", totaalPrijs);
			
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
