package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.daos.KlantDAO;
import be.vdab.entities.Klant;



@WebServlet("/bevestiging.htm")
public class BevestigingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
	private static final String TITLE = "bevestiging reservaties";
	private static final transient KlantDAO klantdao = new KlantDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title",TITLE);
		HttpSession session = request.getSession(false);
		if (session != null){
			String gebruikersnaam = (String) session.getAttribute("klant");
			Klant klant = klantdao.getKlant(gebruikersnaam);
			if (klant != null) {
				request.setAttribute("actiefEen","disabled");
				request.setAttribute("klantgegevens",klant.toString());
				request.setAttribute("actiefTwee",null);
			}
			else {
				request.setAttribute("actiefEen",null);
				request.setAttribute("actiefTwee","disabled");
				if (session.getAttribute("inlogfout") != null) {
					request.setAttribute("klantgegevens",session.getAttribute("inlogfout"));
					session.removeAttribute("inlogfout");
				}
				else {
					request.setAttribute("klantgegevens",null);
				}
			}
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		String paswoord = request.getParameter("paswoord");
		HttpSession session = request.getSession();
		if (gebruikersnaam != null && paswoord != null) {
			if (paswoord.equals(klantdao.getPaswoord(gebruikersnaam))) {
				session.setAttribute("klant", gebruikersnaam);
			}
			else {
				session.setAttribute("inlogfout", "Verkeerde gebruikersnaam of paswoord");
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}

	@Resource(name = KlantDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource){
		klantdao.setDataSource(dataSource);
	}
	

}
