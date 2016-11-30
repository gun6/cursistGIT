package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.daos.VoorstellingenDAO;
import be.vdab.entities.Voorstelling;



@WebServlet("/reserveren.htm")
public class ReserveerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reserveer.jsp";
	private static final String REDIRECT_URL = "%s/mandje.htm";
	private static final String TITLE = "reserveren";
	private static final transient VoorstellingenDAO voorstellingendao = new VoorstellingenDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute("mandje");
			if (mandje != null) {
				if (mandje.containsKey(Long.parseLong(request.getParameter("id")))) {
					request.setAttribute("vorigAantalPlaatsen", voorstellingendao.getPlaatsen(Long.parseLong(request.getParameter("id"))));
				}
			}
		}
		request.setAttribute("title", TITLE);
		request.setAttribute("voorstelling", voorstellingendao.getVoorstelling(Long.parseLong(request.getParameter("id"))));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fout = null;
		int aantalGevraagd = 0;
		int aantalBeschikbaar = voorstellingendao.getPlaatsen(Long.parseLong(request.getParameter("id")));
		try {
			aantalGevraagd = Integer.parseInt(request.getParameter("aantalPlaatsen"));
		} catch (NumberFormatException e) {
			fout = "Geef getal in";
		}
		if (aantalBeschikbaar < aantalGevraagd) {
			fout = "Moet tussen 1 en "+ aantalBeschikbaar +" zijn";
		}
		if (aantalGevraagd < 1) {
			fout = "Moet tussen 1 en "+ aantalBeschikbaar +" zijn";
		}
		if (fout == null) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute("mandje");
			if (mandje == null) {
				mandje = new LinkedHashMap<>();
			}
			mandje.put(Long.parseLong(request.getParameter("id")), aantalGevraagd);
			session.setAttribute("mandje", mandje);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		}
		else {
			request.setAttribute("fout", fout);
			request.setAttribute("title", TITLE);
			request.setAttribute("voorstelling", voorstellingendao.getVoorstelling(Long.parseLong(request.getParameter("id"))));
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
	
	@Resource(name = VoorstellingenDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource){
		voorstellingendao.setDataSource(dataSource);
	}
}
