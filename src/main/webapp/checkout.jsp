<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JCIA Shop - Checkout</title>
	
	<c:if test="${empty sessionScope.account }">
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
	<c:choose>
		<c:when test="${buyResult == true}">
			<div class="container">
				<div class="main-content col-lg-12 col-md-12 col-sm-12">
					<a href="index.jsp" class="btn btn-info">Continue Shopping</a>
				</div>
			</div>
		</c:when>
		<c:when test="${buyResult == null && empty sessionScope.cart}">
			<div class="container">
				<div class="main-content col-lg-12 col-md-12 col-sm-12">
					<p>There are no item in your cart. <a href="index.jsp">Buy</a> something then checkout.</p>
				</div>
			</div>
		</c:when>
	</c:choose>

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