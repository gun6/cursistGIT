<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<div class="menu">
<h1>Wereldwijnen</h1>
<nav>
	<ul>
		<c:forEach items="${landen}" var="land">
			<li><a href="detail.htm?id=${land.id}"><img alt="vlag ${land.naam}" src='<c:url value = "/images/${land.id}.png"/>'></a></li>
		</c:forEach>
	</ul>
</nav>
</div>