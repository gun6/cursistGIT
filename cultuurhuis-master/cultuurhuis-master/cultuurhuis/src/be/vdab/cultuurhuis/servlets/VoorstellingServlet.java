package be.vdab.cultuurhuis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.cultuurhuis.business.Voorstelling;
import be.vdab.cultuurhuis.dao.GenreDAO;
import be.vdab.cultuurhuis.dao.VoorstellingDAO;

@WebServlet(urlPatterns = "/voorstelling.htm", name = "voorstellingservlet")

public class VoorstellingServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/voorstelling.jsp";
	
	private final transient VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		        
        Iterable<String>genres = new GenreDAO().findAllGenres();
        request.setAttribute("genres", genres);
        
        /*	Vervolg uitleg over parameter "genre" vanuit menu.jsp
         * 	Hier wordt de parameter "genre" uit de request gehaald die meegegeven is vanuit menu.jsp via de queryURL
         *  Later wordt deze op de request gezet als een request attribute zodat hij kan worden gebruik in voorstelling.jsp
         *  (zie voorstelling.jsp)
         */
        String genre = request.getParameter("genre");

        Iterable<Voorstelling> voorstellingen = voorstellingDAO.findVoorstellingenByGenre(genre);
        request.setAttribute("voorstellingen", voorstellingen);
        request.setAttribute("genre", genre);

        request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
