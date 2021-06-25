<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<div>

	<h3>Choose File to Upload in Server</h3>

	<form:form class="form-horizontal" action="/upload" method="post"
		enctype="multipart/form-data">

		<div class="form-group">

			<label class="control-label col-sm-2" for="file">File:</label>

			<div class="col-sm-10">

				<input type="file" class="form-control" name="file"
					placeholder="upload file" />

			</div>

		</div>

		<div class="form-group">

			<div class="col-sm-offset-2 col-sm-10">

				<button type="submit" class="btn btn-default">Submit</button>

			</div>

		</div>

	</form:form>

</div>