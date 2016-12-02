package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.daos.VoorstellingenDAO;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final String TITLE = "voorstellingen";
	private static final transient VoorstellingenDAO voorstellingendao = new VoorstellingenDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", TITLE);
		request.setAttribute("genres", voorstellingendao.getGenres());
		if (request.getParameter("id") != null) {
			try {
				request.setAttribute("lijstVoorstellingen", voorstellingendao.getHuidigeVoorstellingenVanGenre(Long.parseLong(request.getParameter("id"))));
			} catch (Exception e) {
				request.setAttribute("fout", "Kon niet laden van databank");
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Resource(name = VoorstellingenDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource){
		voorstellingendao.setDataSource(dataSource);
	}

}
