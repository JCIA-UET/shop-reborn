<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>JCIA Shop - Checkout</title>
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
		<c:when test="${empty sessionScope.account}">
			<div class="container">
				<div class="main-content col-lg-12 col-md-12 col-sm-12">
					<h2>
						Oops! Look like you haven't login yet. Please <a
							href="/shop/login.jsp">login</a> to complete your order. <br>
						Or fill out all information about you to make order immediately:
					</h2>
					<div class="well col-md-6">
						<form action="TransactionService" method="post"
							class="form-horizontal" id="myForm">
							<legend>Personal Details</legend>
							<div class="alert alert-danger" style="display: none"
								id="alert-usernamefail">The username really existed !</div>
							<div class="form-group required">
								<label class="col-sm-3 control-label" for="realname">Full
									Name</label>
								<div class="col-sm-9">
									<input name="fullname" placeholder="Full Name" id="realname"
										class="form-control" type="text" required="required">
								</div>
							</div>
							<div class="form-group required">
								<label class="col-sm-3 control-label" for="phonenumber">Phone
									number</label>
								<div class="col-sm-9">
									<input name="phonenumber" placeholder="Phone number"
										id="phonenumber" class="form-control" type="text"
										required="required">
								</div>
							</div>
							<legend>Address</legend>
							<div class="form-group">
								<label class="col-sm-3 control-label" for="city">City</label>
								<div class="col-sm-9">
									<input name="city" placeholder="City" id="city"
										class="form-control" type="text" required="required">
								</div>
							</div>
							<div class="form-group required">
								<label class="col-sm-3 control-label" for="address">Address</label>
								<div class="col-sm-9">
									<input name="address" placeholder="Address" id="address"
										class="form-control" type="text" required="required">
								</div>
							</div>
							
							<input type="hidden" name="username" value="">
							<input type="hidden" name="password" value="">
							<input type="hidden" name="action" value="checkout">
							
							<button class="btn btn-danger">Checkout</button>
						</form>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="main-content col-lg-12 col-md-12 col-sm-12">
					<p>Everything is done. All items are delivered to your address</p>
				</div>
			</div>
		</c:otherwise>
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