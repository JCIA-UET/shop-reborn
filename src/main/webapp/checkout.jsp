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
		<div class="main-content col-lg-12 col-md-12 col-sm-12">
			<div id="main-table">
				<br>
				<p><b><u>Hint:</u></b> Click on
					<span class="glyphicon glyphicon-refresh"></span>to update after changing item quantity/ 
					Click on 
					<span class="glyphicon glyphicon-remove"></span>to delete item from cart.
				</p>
				<hr style="margin:0px;">
				<c:choose>
					<c:when test="${not empty sessionScope.cart}">
						<table class="table table-hover" >
							<thead>
								<tr>
									<th class="text-center" id="table-content-img">Product image</th>
									<th class="text-left" id="table-content-name">Name</th>
									<th class="text-left" id="table-content-qtt">Quantity</th>
									<th class="text-right" id="table-content-price">Unit price</th>
									<th class="text-right" id="table-content-total">Total price</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="p" items="${sessionScope.cart}">
									<tr>
										<td class="text-center" id="table-content-img"><img src="${p.imageLink}" class="img-thumbnail" alt="" width="50" height="50"/></td>
										<td class="text-left" id="table-content-name">${p.name}</td>
										<td class="text-left" id="table-content-qtt">
										
										</td>
										<td class="text-right" id="table-content-price">${p.price}</td>
										<td class="text-right" id="table-content-total">${p.price * p.quantity}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p>There is nothing in your cart.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>