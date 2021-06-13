<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>

	<h2>Results</h2>

	<h3>${requestScope["messageToDisplay"]}</h3>

	<pre>OriginalFileName: ${requestScope["fileReceivedName"]}</pre>

	<pre>Type: ${requestScope["fileReceivedType"]}</pre>

	<pre>Size: ${requestScope["fileReceivedSize"]} in bytes</pre>

</div>