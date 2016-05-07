<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>user</title>

    <link href="../assests/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assests/css/metisMenu.min.css" rel="stylesheet">
    <link href="../assests/css/sb-admin-2.css" rel="stylesheet">
    <link href="../assests/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<h1 class="page-header">Update User</h1>
	<form action="UserService" method="post">
		<input type="hidden" name="userId" id="userid" value="${account.id }">
		<div class="form-group">
			<label>Username</label>
			<input class="form-control" type="text" value="${account.username }" readonly>
		</div>
		<div class="form-group">
			<label>Real Name</label>
			<input class="form-control" type="text" name="realname" value="${account.realName }" required>
		</div>
		<div class="form-group">
			<label>Phone number</label>
			<input class="form-control" type="text" name="phone" value="${account.phone }" required>
		</div>
		<div class="form-group">
			<label>Address</label>
			<input class="form-control" type="text" name="address" value="${account.address }" required>
		</div>
		<div class="form-group">
			<label>City</label>
			<input class="form-control" type="text" name="city" value="${account.city }" required>
		</div>
		<input type="hidden" name="action" id="action" value="update-user">
		<input class="btn btn-primary" type="submit" value="Save">
		<a href="UserService?action=gallcus" role="button" class="btn btn-danger">Cancel</a>
		
	</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>