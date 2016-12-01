package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.daos.KlantDAO;
import be.vdab.daos.ReserveerDAO;
import be.vdab.daos.VoorstellingenDAO;
import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;
import be.vdab.entities.Voorstelling;
import be.vdab.valueobjects.Reservering;


@WebServlet("/overzicht.htm")
public class OverzichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/overzicht.jsp";
	private static final String TITLE = "overzicht";
	private static final transient ReserveerDAO reserveerdao = new ReserveerDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title",TITLE);
		HttpSession session = request.getSession(false);
		if (session != null) {
			if (session.getAttribute("gelukteReserveringen") != null) {
				
			}
			if (session.getAttribute("mislukteReserveringen") != null) {
				
			}
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null){
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute("mandje");
			long klantId = reserveerdao.getKlantId((String)session.getAttribute("klant"));
			if (mandje != null) {
				List<Long> gelukteReservaties = new ArrayList<>();		
				List<Long> mislukteReservaties = new ArrayList<>();
				for (Entry<Long, Integer> entry : mandje.entrySet()) {
					long voorstellingId = entry.getKey();
					int aantalPlaatsen = entry.getValue();
					Reservatie reservatie = new Reservatie(klantId, voorstellingId, aantalPlaatsen);
					long reservatieId = reserveerdao.reserveer(reservatie);
					if (reservatieId != 0) {
						gelukteReservaties.add(reservatieId);
					}
					else {
						mislukteReservaties.add(reservatieId);
					}
				}
				session.setAttribute("gelukteReserveringen", gelukteReservaties);
				session.setAttribute("mislukteReserveringen", mislukteReservaties);
			}
			
			session.removeAttribute("mandje");
			
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
	
	@Resource(name = ReserveerDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource){
		reserveerdao.setDataSource(dataSource);
	}
}
