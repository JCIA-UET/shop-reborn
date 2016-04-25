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
	    	<h2>Account Register</h2>
	    	<form action="AccountService" method="post" class="form-horizontal">
	    		<legend>Personal Details</legend>
	    		<div class="form-group required">
		            <label class="col-sm-2 control-label" for="realname">Full Name</label>
		            <div class="col-sm-10">
		                <input name="fullname" placeholder="Full Name" id="realname" class="form-control" type="text">
            		</div>
                </div>
                <div class="form-group required">
		            <label class="col-sm-2 control-label" for="phonenumber">Phone number</label>
		            <div class="col-sm-10">
		                <input name="phonenumber" placeholder="Phone number" id="phonenumber" class="form-control" type="text">
            		</div>
                </div>
                <legend>Address</legend>
                <div class="form-group">
		            <label class="col-sm-2 control-label" for="city">City</label>
		            <div class="col-sm-10">
		                <input name="city" placeholder="City" id="city" class="form-control" type="text">
            		</div>
                </div>
                <div class="form-group required">
		            <label class="col-sm-2 control-label" for="address">Address</label>
		            <div class="col-sm-10">
		                <input name="address" placeholder="Address" id="address" class="form-control" type="text">
            		</div>
                </div>
	    		<legend>Account</legend>
	    		<div class="form-group required">
		            <label class="col-sm-2 control-label" for="address">Username</label>
		            <div class="col-sm-10">
		                <input name="username" placeholder="Username" id="username" class="form-control" type="text">
            		</div>
                </div>
                <div class="form-group required">
		            <label class="col-sm-2 control-label" for="password">Password</label>
		            <div class="col-sm-10">
		                <input name="password" placeholder="Password" id="password" class="form-control" type="text">
            		</div>
                </div>
                <div class="form-group required">
		            <label class="col-sm-2 control-label" for="re-password">Re-type Password</label>
		            <div class="col-sm-10">
		                <input name="re-password" placeholder="Re-type Password" id="re-password" class="form-control" type="text">
            		</div>
                </div>
                <legend></legend>
                <input value="Register" class="btn btn-primary pull-right" type="submit">
	    	</form>
	    </div>
	</div>
		
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>    