package be.vdab.servlets.docenten;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.services.DocentService;

/**
 * 		10 Entity verwijderen
 *
 *			DocentDAO.java
 *				delete()
 *
 *			DocentService.java
 *				delete()
 *
 *			zoeken.jsp
 *				Verwijderen button
 *
 *			DocentVerwijderenServlet.java
 *				verwerkt POST requests naar /docenten/verwijderen.htm
 */

@WebServlet("/docenten/verwijderen.htm")
public class DocentVerwijderenServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private final transient DocentService docentService = new DocentService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		docentService.delete(Long.parseLong(request.getParameter("id")));
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
	}
}
