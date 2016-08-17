package be.vdab.cultuurhuis.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.cultuurhuis.business.Voorstelling;
import be.vdab.cultuurhuis.dao.VoorstellingDAO;

@WebServlet(urlPatterns = "/reservatie.htm", name = "reservatieservlet")

public class ReservatieServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatie.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Voorstelling voorstelling = null;
		
		HttpSession session = request.getSession();
		
		int voorstellingId = 0;
		
		// als de queryURL van de request een voorstellingId bevat
		if (request.getParameter("voorstellingId") != null)
		{
			/* 	Uitlezen van de request parameter die in de queryURL (/reservatie.htm?voorstellingId=27) zit
			 * 	Deze is samengesteld in voorstelling.jsp
			 */
			voorstellingId = Integer.parseInt(request.getParameter("voorstellingId"));
			voorstelling = new VoorstellingDAO().findVoorstelling(voorstellingId);
	
			// als er al een mandje is
			if (session.getAttribute("mandje") != null )
			{
				// haal de inhoud van het session attribute "mandje" uit de session en steek het in de lokale variable Map mandje
				Map<Integer, Integer> mandje = ( Map<Integer, Integer> ) session.getAttribute("mandje");
				
				// als de map de voorstellingId (key) bevat
				if ( mandje.containsKey(voorstellingId) )
				{ 
					// geef de waarde (=aantal reeds gereserveerde plaatsen) uit het mandje dat overeenkomt met deze key (= voorstellingId)
					long reedsGereserveerdePlaatsen = mandje.get(voorstellingId);
					// plaats de plaatsen terug op de request
					request.setAttribute("plaatsen", reedsGereserveerdePlaatsen);
				}
			}
		}
		request.setAttribute("voorstelling", voorstelling);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Voorstelling voorstelling = null;
		HttpSession session = request.getSession();
		
		int plaatsen = 0;
		int voorstellingId = 0;
		
		Map<String, String> fouten = new HashMap<>();
		Map<Integer, Integer> mandje = new HashMap<>();
		
		// als de queryURL van de request een voorstellingId bevat
		if (request.getParameter("voorstellingId") != null)
		{
			// uitlezen van de request parameter die uit queryURL van reservatie.jsp komt
			voorstellingId = Integer.parseInt(request.getParameter("voorstellingId"));
			// haal de voorstelling uit de db obv voorstellingId
			voorstelling = new VoorstellingDAO().findVoorstelling(voorstellingId);
			// haal uit de voorstelling het aantal vrije plaatsen
			int vrijePlaatsen = voorstelling.getVrijePlaatsen();
			
			try
			{
				// "plaatsen" is de waarde uit het invoerveld uit de form
				plaatsen = Integer.parseInt(request.getParameter("plaatsen"));
				
				if(plaatsen < 1)
				{   
					fouten.put("plaatsen", "Vul een getal in groter dan 0");
				}
				
				if((plaatsen > vrijePlaatsen) || (plaatsen == 0) )
				{
					fouten.put("plaatsen", "Tik een getal tussen 1 en " + vrijePlaatsen);
				}
			}
			catch(Exception ex) 
			{
				fouten.put("plaatsen", "Vul een getal");
			}

			// check of er fouten bij de invoer waren
			if(!fouten.isEmpty()) 
			{ 	
				// er zijn fouten
				
				request.setAttribute("voorstelling", voorstelling);		// geef de voorstelling mee op de request
				request.setAttribute("fouten", fouten);					// geef de fouten mee op request
				
				request.getRequestDispatcher(VIEW).forward(request, response);
				//doPost(request, response);
			}
			else 
			{ 	
				// er zijn geen fouten
				
				voorstelling = new VoorstellingDAO().findVoorstelling(voorstellingId);
				
				if(session.getAttribute("mandje") != null ) 
				{	
					// indien er reeds een sessie en een mandje is
					
					mandje = (Map<Integer, Integer>) session.getAttribute("mandje"); 	// haal bestaand mandje van session
					
					if(mandje.containsKey(voorstellingId))
					{	
						// als mandje deze voorstelling reeds bevat
						
						long vorigePlaatsen = mandje.get(voorstellingId);
						
						if(vorigePlaatsen == plaatsen)
						{ 	
							// als de gebruiker het aantal plaatsen niet wijzigde
							
							request.setAttribute("voorstelling", voorstelling); 	// geef de voorstelling mee op de request
							request.setAttribute("plaatsen",vorigePlaatsen); 		// geef het voordien gekozen aantal plaatsen mee op de request
							request.getRequestDispatcher(VIEW).forward(request, response);
							//doPost(request, response);
						}
						else
						{ 	
							// als de gebruiker het aantal plaatsen wijzigde
							
							mandje.remove(voorstellingId); 							// verwijder de bestaande reservatie uit het mandje
							mandje.put(voorstellingId, plaatsen); 					// stop de gewijzigde reservatie in het mandje
							session.setAttribute("mandje", mandje);					// plaats het mandje op de sessie
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
						}
					}
					else
					{	
						// als mandje deze voorstelling nog niet bevat
						
						mandje.put(voorstellingId, plaatsen);						// stop deze reservatie in het mandje
						session.setAttribute("mandje", mandje);						// plaats het mandje op de sessie
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
					}
				}
				else
				{
					// indien er nog geen session en mandje is
					
					mandje.put(voorstellingId, plaatsen);							// stop deze reservatie in het mandje
					session.setAttribute("mandje", mandje); 						// plaats het mandje op de sessie
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
				}
			}
		}
	}
}
