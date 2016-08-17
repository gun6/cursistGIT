package be.vdab.servlets.docenten;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.DocentDAO;
import be.vdab.services.DocentService;

/**
 * 		5 Entity zoeken via de primary key
 * 			
 * 			deze servlet verwerkt GET requests naar /docenten/zoeken.htm
 */

/**
 * 		8 Transacties & Service layer
 * 
 * 			Wijzig DocentDAO naar DocentService
 * 
 * 			DocentService.java
 * 			DocentDAO.java
 * 			ZoekenServlet.java
 */

@WebServlet("/docenten/zoeken.htm")
public class ZoekenServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/docenten/zoeken.jsp";
	private final transient DocentService docentService = new DocentService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		if (request.getQueryString() != null) 
		{
			try 
			{
				request.setAttribute("docent", docentService.read(Long.parseLong(request.getParameter("id"))));
			} 
			catch (NumberFormatException ex) 
			{
				request.setAttribute("fouten", Collections.singletonMap("id", "tik een getal"));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
