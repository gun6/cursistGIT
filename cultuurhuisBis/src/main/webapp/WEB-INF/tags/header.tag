<%@ tag description='titel onderdeel van pagina' pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

	<div class="super"><h1>Het Cultuurhuis: ${title}</h1>
	<img alt="afbeelding ${title}" src='<c:url value="/images/${title}.png"/>'></div>
	<nav>
		<ul>
			<c:if test="${title ne 'voorstellingen'}"><li><a href='<c:url value='/index.htm'/>'>Voorstellingen</a></li></c:if>
			<c:if test="${not empty mandje && title ne 'reservatiemandje'}"><li><a href='<c:url value='/mandje.htm'/>'>Reservatiemandje</a></li></c:if>
			<c:if test="${not empty mandje && title ne 'bevestiging reservaties'}"><li><a href='<c:url value='/bevestiging.htm'/>'>Bevestiging reservatie</a></li></c:if>
		</ul>
	</nav>

