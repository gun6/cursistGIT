package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.cultuurhuis.business.Klant;
import be.vdab.cultuurhuis.business.MandjeItem;
import be.vdab.cultuurhuis.dao.MandjeDAO;
import be.vdab.cultuurhuis.dao.ReservatieDAO;

@WebServlet(urlPatterns = "/boeking.htm", name = "boekingservlet")

public class BoekingServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/boeking.jsp";
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Map<Integer, Integer> mislukt = new ConcurrentHashMap<>();
		Map<Integer, Integer> gelukt = new ConcurrentHashMap<>();
		
		if ((session.getAttribute("mandje") != null) && (session.getAttribute("klant") != null))
		{
			Map<Integer, Integer> mandje = (Map<Integer, Integer>) session.getAttribute("mandje"); 	// haal bestaand mandje op van session
			Klant klant = (Klant)session.getAttribute("klant"); 									// haal klant op van session
			int klantnr = klant.getKlantId();
			
			for(Map.Entry<Integer, Integer> entry : mandje.entrySet())
			{
				int voorstellingsnr = entry.getKey();
				int plaatsen = entry.getValue();
				
				if(new ReservatieDAO().boekReservaties(klantnr, voorstellingsnr, plaatsen))
				{
					gelukt.put(entry.getKey(), entry.getValue());
				}
				else
				{
					mislukt.put(entry.getKey(), entry.getValue());
				}
			}
			
			Iterable<MandjeItem> mandjeItemsGelukt = new MandjeDAO().toonMandje(gelukt);
			Iterable<MandjeItem> mandjeItemsMislukt = new MandjeDAO().toonMandje(mislukt);
			request.setAttribute("mandjeItemsGelukt",mandjeItemsGelukt);
			request.setAttribute("mandjeItemsMislukt",mandjeItemsMislukt);
			session.removeAttribute("mandje");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
