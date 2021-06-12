<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">

	<div class="container-fluid">

		<div class="navbar-header">

			<a class="navbar-brand" href="/home">ImEx</a>

		</div>

		<ul class="nav navbar-nav">

			<li class="active"><a href="/up"> Upload </a></li>

			<c:choose>

				<%-- ${requestScope["valueslists"]} --%>
				<c:when test="${requestScope.valueslists != null}">

					<li class="active"><a href="/json"> JSON </a></li>

				</c:when>

				<c:otherwise>

					<li><a href="#"> N/A </a></li>

				</c:otherwise>

			</c:choose>

			<li><a href="#"> N/A </a></li>

		</ul>

	</div>

</nav>