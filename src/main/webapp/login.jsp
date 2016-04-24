<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>JCIA Shop - Login</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="assests/css/bootstrap.min.css" rel="stylesheet"/>
    
    <!-- custom style -->
	<link href="assests/css/custom.css" rel="stylesheet"/>
</head>
<body>
	
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>

	<!-- content -->
	<div class="container">
	    <div class="well col-md-6">
	    	<h2>Account Login</h2>
	    	<form action="AccountService" method="post">
	    		<div class="form-group">
	    			<label class="control-label" for="username">
	    			Username
	    			</label>
	    			<input type="text" class="form-control" id="username" name="username"/>
	    		</div>
	    		<div class="form-group">
	    			<label class="control-label" for="password">
	    			Password
	    			</label>
	    			<input type="password" class="form-control" id="password" name="password"/>
	    		</div>
	    		<button class="btn btn-primary pull-right" type="submit">Login</button>
	    	</form>
	    </div>
	</div>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>    