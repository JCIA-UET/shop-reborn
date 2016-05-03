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
											<input class="form-control" id="qtt" type="text" name="quantity" value="${p.quantity}"/>
										</td>
										<td class="tb-cnt-unit">${p.price}</td>
										<td class="tb-cnt-total">${p.price * p.quantity}</td>
										<td class="tb-cnt-act">
											<form action="TransactionService" method="post">
												<input type="hidden" name="productid" value="${p.id }"/>
												<input type="hidden" name="qtt" id="update" value="${p.quantity}" onclick="getValue()" />
												<input type="hidden" name="action" value="update"/>
												<button class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="Update">
													<span class="glyphicon glyphicon-refresh"></span>
												</button>
											</form>
											
											<form action="TransactionService" method="post">
												<input type="hidden" name="productid" value="${p.id }"/>
												<input type="hidden" name="action" value="delete"/>
												<button class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="Remove">
													<span class="glyphicon glyphicon-remove-sign"></span>
												</button>
											</form>
										</td>
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
<script>
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();   
	});
	
	function getValue() {
		var qtt = document.getElementById('qtt').value;
		document.getElementById('update').value = qtt;
	}
</script>
</html>