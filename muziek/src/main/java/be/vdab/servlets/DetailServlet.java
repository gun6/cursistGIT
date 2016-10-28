package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.AlbumService;


@WebServlet("/detail.htm")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/detail.jsp";
	private final transient AlbumService albumService = new AlbumService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("album", albumService.getAlbumMetArtiestEnTracks(Long.parseLong(request.getParameter("id"))));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
}
