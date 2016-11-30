package be.vdab.servlets;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import be.vdab.daos.VoorstellingenDAO;
import be.vdab.entities.Voorstelling;
import be.vdab.valueobjects.Reservering;



@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String TITLE = "reservatiemandje";
	private static final transient VoorstellingenDAO voorstellingendao = new VoorstellingenDAO();
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title",TITLE);
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute("mandje");		
		if (mandje != null) {
			List<Reservering> reserveringen = new ArrayList<>();								//Geen check beschikbare plaatsen: moet mislukte reserveringen kunnen krijgen
			for (Entry<Long, Integer> entry : mandje.entrySet()) {
				Voorstelling voorstelling = voorstellingendao.getVoorstelling(entry.getKey());
				int aantalPlaatsen = entry.getValue();
				Reservering reservering = new Reservering(voorstelling, aantalPlaatsen);
				reserveringen.add(reservering);
			}
			if (!reserveringen.isEmpty()) {
				request.setAttribute("reserveringen", reserveringen);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute("mandje");
		if (mandje != null && !mandje.isEmpty() && request.getParameterValues("id") != null) {
			List<String> aangevinkt = Arrays.asList(request.getParameterValues("id"));
			if (aangevinkt != null && !aangevinkt.isEmpty() && !aangevinkt.contains(null)){
				for (String id : aangevinkt) {
					mandje.remove(Long.parseLong(id));
				}
			}
		}
		session.setAttribute("mandje", mandje);
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
	

	@Resource(name = VoorstellingenDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource){
		voorstellingendao.setDataSource(dataSource);
	}
}
