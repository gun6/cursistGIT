<%@ tag description='menu' pageEncoding='UTF-8'%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<header>
	<nav>
		<ul>
			<%--	items='${genres}
						-> komt uit de IndexServlet
							Iterable<String> genres = genreDAO.findAllGenres();
							request.setAttribute("genres", genres);	
					
					Stop de genres die de servlet uit de db heeft gehaald in een lus
						<c:forEach var='genre' items='${genres}'>
					Stop elk genre dan in variable 
						var='genre'
					Maak per genre een link met <c url...
					Stop de variabele 'genre' in een parameter met name "genre"
						<c:param name="genre" value="${genre}"/>
					
					Deze parameter kan dan in de queryURL meegegeven worden om naar het overzicht per genre te gaan in voorstelling.htm
						vb: http://localhost:8080/cultuurhuis/voorstelling.htm?genre=Circustheater
						
					De parameter "genre" wordt dan in VoorstellingServlet.java verder behandeld
					(zie VoorstellingServlet.java)
			 --%>
			<c:forEach var='genre' items='${genres}'>
				<c:url value="/voorstelling.htm" var="voorstellingURL">
					<c:param name="genre" value="${genre}"/>
				</c:url>
				<li><a href="${voorstellingURL}">${genre}</a></li>
			</c:forEach>
		</ul>
	</nav>
</header>