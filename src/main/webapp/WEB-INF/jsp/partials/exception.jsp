<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div>

	<h1>Error</h1>

	<p>Exception <strong>${requestScope["errorNameToDisplay"]}</strong> thrown</p>

	<c:if test="${not empty errorMessageToDisplay}">

		<p>${requestScope["errorMessageToDisplay"]}</p>

	</c:if>

</div>