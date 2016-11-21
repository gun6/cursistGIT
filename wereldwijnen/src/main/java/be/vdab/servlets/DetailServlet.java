package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandenService;
import be.vdab.services.SoortenService;
import be.vdab.services.WijnenService;


@WebServlet("/detail.htm")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/detail.jsp";
	private final transient LandenService landenService = new LandenService();
	private final transient SoortenService soortenService = new SoortenService();
	private final transient WijnenService wijnenService = new WijnenService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("landen", landenService.findAll());
		request.setAttribute("land", landenService.read(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("soorten", soortenService.findAllOfLand(Integer.parseInt(request.getParameter("id"))));
		if (request.getParameter("soortid")!=null) {
			request.setAttribute("huidigeSoort", soortenService.read(Integer.parseInt(request.getParameter("soortid"))));
			request.setAttribute("wijnen", wijnenService.findAllOfSoort(Integer.parseInt(request.getParameter("soortid"))));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}


	
}
