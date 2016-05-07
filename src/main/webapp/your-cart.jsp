<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JCIA Shop - Shopping Cart</title>
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
		<div class="well col-lg-12 col-md-12 col-sm-12">
			<div id="main-table">
				<br>
				<p><b><u>Hint:</u></b> Click on
					<span class="glyphicon glyphicon-refresh"></span> to update after changing item quantity/ 
					Click on 
					<span class="glyphicon glyphicon-remove"></span> to delete item from cart.
				</p>
				<hr style="margin:0px;border-color:#c6c6c6;">
				<c:choose>
					<c:when test="${not empty sessionScope.cart}">
						<table class="table table-hover" >
							<thead>
								<tr>
									<th class="tb-cnt-img"><b>Image</b></th>
									<th class="tb-cnt-name"><b>Name</b></th>
									<th class="tb-cnt-qtt"><b>Quantity</b></th>
									<th class="tb-cnt-unit"><b>Unit price</b></th>
									<th class="tb-cnt-total"><b>Total price</b></th>
									<th class="tb-cnt-act"><b>Action</b></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="p" items="${sessionScope.cart}">
									<tr>
										<td class="tb-cnt-img"><img src="${p.imageLink}" class="img-thumbnail" alt="" width="50" height="50"/></td>
										<td class="tb-cnt-name">${p.name}</td>
										<td class="tb-cnt-qtt">
											<input type="hidden" name="productid" value="${p.id}"/>
											<input class="form-control" id="updatedqtt" type="text" name="quantity" value="${p.quantity}"/>
										</td>
										<td class="tb-cnt-unit">
											<fmt:formatNumber type="currency" value="${p.price}">
											</fmt:formatNumber>
										</td>
										<td class="tb-cnt-total">
											<fmt:formatNumber type="currency">${p.price * p.quantity}</fmt:formatNumber>
										</td>
										<td class="tb-cnt-act">
											<form action="TransactionService" id="updateform" method="post">
												<input type="hidden" name="productid" value="${p.id}"/>
												<input type="hidden" id="qttinput" name="qtt" value="${p.quantity}"/>
												<input type="hidden" name="action" value="update"/>
												<button type="submit" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="Update">
													<span class="glyphicon glyphicon-refresh"></span>
												</button>
											</form>
											
											<form action="TransactionService" method="post">
												<input type="hidden" name="productid" value="${p.id }"/>
												<input type="hidden" name="action" value="delete"/>
												<button type="submit" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="Remove">
													<span class="glyphicon glyphicon-remove-sign"></span>
												</button>
											</form>
										</td>
									</tr>
								</c:forEach>
								<tr>
								<td></td>
								<td></td>
								<td></td>
								<td style="font-size:30px;text-align:right;"><b><u>Total:</u></b></h4></td>
								<td style="text-align:right;vertical-align:middle;">
									<fmt:formatNumber type="currency">${totalcash}</fmt:formatNumber>
								</td>
								</tr>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p>There is nothing in your cart.</p>
					</c:otherwise>
				</c:choose>
				<a href="index.jsp" class="btn btn-info pull-left">Continue Shopping</a>
				<a href="checkout.jsp" class="btn btn-success pull-right">Go to Checkout</a>
			</div>
		</div>
	</div>
	
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
<script>
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();   
	});
	
	window.onload = function() {
		document.getElementById('updateform').onsubmit = function() {
			var updatedQtt = document.getElementById('updatedqtt').value;
			var qttInput = document.getElementById('qttinput');
			qttInput.value = updatedQtt;

		};
	}
</script>
</html>