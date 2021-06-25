<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>

	<c:choose>

		<c:when test="${requestScope.fileReceivedName != null}">

			<pre>OriginalFileName: ${requestScope["fileReceivedName"]}<br>

	Type: ${requestScope["fileReceivedType"]}<br>

	Size: ${requestScope["fileReceivedSize"]} [bytes]</pre>

		</c:when>

		<c:otherwise>

		</c:otherwise>

	</c:choose>

</div>