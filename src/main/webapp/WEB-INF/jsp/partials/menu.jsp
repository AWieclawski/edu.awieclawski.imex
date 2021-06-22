<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">

	<div class="container-fluid">

		<div class="navbar-header">

			<a class="navbar-brand" href="/home">ImEx</a>

		</div>

		<ul class="nav navbar-nav">

			<li class="active"><a href="/up"> Upload </a></li>

			<c:choose>

				<c:when test="${sessionScope.sessionValuesLists != null}">

					<jsp:include page="sub/menujson.jsp" />

				</c:when>

				<c:otherwise>

					<li><a href="#"> N/A </a></li>

				</c:otherwise>

			</c:choose>

			<c:choose>

				<c:when test="${sessionScope.sessionValuesLists != null}">

					<!-- 	<li class="active"><a href="/xml" target="_blank"> XML </a></li>	-->

					<jsp:include page="sub/menuxml.jsp" />

				</c:when>

				<c:otherwise>

					<li><a href="#"> N/A </a></li>

				</c:otherwise>

			</c:choose>

		</ul>

	</div>

</nav>