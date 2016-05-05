<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JCIA Shop - Checkout</title>
	
	<c:if test="${empty sessionScope.cart && empty sessionScope.account }">
		<meta http-equiv="refresh" content="0;URL=login.jsp">
	</c:if>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="assests/css/bootstrap.min.css" rel="stylesheet" />

	<!-- custom style -->
	<link href="assests/css/custom.css" rel="stylesheet" />
	</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>

	<!--  content -->
	<div class="container">
		<div class="well col-lg-12 col-md-12 col-sm-12">
			<c:choose>
				<c:when test="${not empty sessionScope.cart}">
					<h2>Please confirm your <strong>information</strong> and <strong>the place</strong> where to receive our products</h2>
					<hr/>
					<c:set var="account" value="${sessionScope.account}"></c:set>
					<form action="TransactionService" method="post" class="form-horizontal" id="checkoutForm">
			    		<legend>Information</legend>
			    		<div class="form-group required">
				            <label class="col-sm-3 control-label" for="realname">Name</label>
				            <div class="col-sm-9">
				                <input name="fullname" id="realname" value="${account.realName}" class="form-control" type="text" required="required">
		            		</div>
		                </div>
		                <div class="form-group required">
				            <label class="col-sm-3 control-label" for="phonenumber">Phone number</label>
				            <div class="col-sm-9">
				                <input name="phonenumber" value="${account.phone}" id="phonenumber" class="form-control" type="text" required="required">
		            		</div>
		                </div>
		                <legend>Where to pay</legend>
		                <div class="form-group">
				            <label class="col-sm-3 control-label" for="city">City</label>
				            <div class="col-sm-9">
				                <input name="city" value="${account.city}" id="city" class="form-control" type="text" required="required">
		            		</div>
		                </div>
		                <div class="form-group required">
				            <label class="col-sm-3 control-label" for="address">Address</label>
				            <div class="col-sm-9">
				                <input name="address" value="${account.address}" id="address" class="form-control" type="text" required="required">
		            		</div>
		                </div>
		                <input type="hidden" name="action" value="checkout">
		                <a href="index.jsp" class="btn btn-info pull-left">Continue shopping</a>
			    	 	<button type="submit" class="btn btn-success pull-right">Checkout</button>
			    	</form>
			    	
				</c:when>
				<c:otherwise>
					<h2>Your cart is empty now!</h2>
					<a href="index.jsp" class="btn btn-info pull-left">Buy something</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
		function mobilenumber() {
	       if(document.getElementById('phonenumber').value != ""){

	       var y = document.getElementById('phonenumber').value;
	       if(isNaN(y)||y.indexOf(" ")!=-1)
	       {
	          alert("Invalid Mobile No.");
	          document.getElementById('phonenumber').focus();
	          return false;
	       }

	       if (y.length>11 || y.length<10)
	       {
	            alert("Mobile No. should be 10 digit");
	            document.getElementById('phonenumber').focus();
	            return false;
	       }


	       }
	    }
	</script>
</html>