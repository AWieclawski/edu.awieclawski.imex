<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>

	<h3>Choose File to Upload in Server</h3>

	<form class="form-horizontal" action="/up" method="post"
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

	</form>

</div>