<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h3>Choose File to Upload in Server</h3>
	<%-- form action="/result" --%>
	<form action="/" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <input type="submit" value="upload" />
	</form>
</div>