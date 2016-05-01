<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
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
	    	 <div class="alert alert-danger" style="display: none" id="alert-fail">
    			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
   				  No matching password or account not exist!
 			</div>
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
	    		<input type="hidden" name="action" value="login" >
	    		<a href="#" >Forget password</a>
	    		<div>If you do not have a account, please <a href="register.jsp">Sign In</a></div>
	    		<input class="btn btn-primary pull-right" type="submit" value="Login">
	    		</form>
	    </div>
	</div>
	
	
	
	
		<c:if test="${not empty message}">
		<script type="text/javascript">
			document.getElementById("alert-fail").style.display = "block";
		</script>
	    </c:if>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>    