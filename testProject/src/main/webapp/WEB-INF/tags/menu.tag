<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<ul>
		<li><a href="#">Artikels</a>
			<ul>
				<li><a href="<c:url value='/artikels/zoekenopnummer.htm'/>">Zoeken op nummer</a></li>
				<li><a href="<c:url value='/artikels/toevoegen.htm'/>">Toevoegen</a></li>
				<li><a href='<c:url value='/artikels/zoekenopnaam.htm'/>'>Zoeken op naam</a></li>
				<li><a href="<c:url value='/artikels/prijsverhoging.htm'/>">Prijsverhoging</a></li>
				<li><a href="<c:url value='/artikels/kortingen.htm'/>">Kortingen</a></li>
				<li><a href="<c:url value='/artikels/perartikelgroep.htm'/>">Artikels per artikelgroep</a></li>
			</ul></li>
			<!-- 
		<li><a href="#">Cursussen</a>
			<ul>
				<li><a href="<c:url value='/cursussen/metnaam.htm'/>">Zoeken op naam</a></li>
			</ul></li>
		<li><a href="#">Campussen</a>
			<ul>
				<li><a href="<c:url value='/campussen/ingemeente.htm'/>">Zoeken op gemeente</a></li>
				<li><a href="<c:url value='/campussen/docenten.htm'/>">Docenten</a></li>
			</ul></li>
			-->
	</ul>
</nav>
