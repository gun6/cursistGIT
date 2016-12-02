package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
	private static final transient VoorstellingenDAO voorstellingendao = new VoorstellingenDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title",TITLE);
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			List<Long> gelukteReservaties = (List<Long>) session.getAttribute("gelukteReservaties");
			@SuppressWarnings("unchecked")
			Map<Long,Integer> mislukteReservaties = (Map<Long,Integer>) session.getAttribute("mislukteReservaties");
			if (gelukteReservaties != null) {
				List<Reservering> gelukteReserveringen = new ArrayList<>();
				for (Long reservatie : gelukteReservaties) {
					Reservering reservering = reserveerdao.getReserveringen(reservatie);
					gelukteReserveringen.add(reservering);
				}
				request.setAttribute("gelukteReserveringen", gelukteReserveringen);
			}
			if (mislukteReservaties != null) {
				List<Reservering> mislukteReserveringen = new ArrayList<>();
				for (Entry<Long, Integer> reservatie : mislukteReservaties.entrySet()) {
					if (reservatie.getKey() != null) {
						Voorstelling voorstelling = voorstellingendao.getVoorstelling(reservatie.getKey());
						Reservering reservering = new Reservering(voorstelling, reservatie.getValue());
						mislukteReserveringen.add(reservering);
					}
				}
				request.setAttribute("mislukteReserveringen", mislukteReserveringen);
			}
		}
		session.invalidate();
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
				Map<Long,Integer> mislukteReservaties = new LinkedHashMap<>();
				for (Entry<Long, Integer> entry : mandje.entrySet()) {
					long voorstellingId = entry.getKey();
					int aantalPlaatsen = entry.getValue();
					Reservatie reservatie = new Reservatie(klantId, voorstellingId, aantalPlaatsen);
					long reservatieId = reserveerdao.reserveer(reservatie);
					if (reservatieId != 0) {
						gelukteReservaties.add(reservatieId);
					}
					else {
						mislukteReservaties.put(voorstellingId, aantalPlaatsen);
					}
				}
				session.setAttribute("gelukteReservaties", gelukteReservaties);
				session.setAttribute("mislukteReservaties", mislukteReservaties);
			}
			
			session.removeAttribute("mandje");
			
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
	
	@Resource(name = ReserveerDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource){
		reserveerdao.setDataSource(dataSource);
		voorstellingendao.setDataSource(dataSource);
	}

}
