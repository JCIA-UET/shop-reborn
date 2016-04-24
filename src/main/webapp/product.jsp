<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>JCIA Shop - ${product.name}</title>
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
	<div class="container well">
		<div class="row">
			<!-- product image -->
			<div class="col-md-6">
				<img src="${product.imageLink}" class="image-responsive"/>
			</div>
			<!-- product information -->
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-12">
						<h1>${product.name}</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 bottom-rule">
						<h2 class="product-price">
							<fmt:formatNumber type="currency" value="${product.price}">
			                </fmt:formatNumber> 
						</h2>
					</div>
				</div>
				<div class="row add-to-cart">
					<div class="col-md-5 product-qty">
				    	<span class="btn btn-default btn-lg btn-qty">
							<span onclick="increaseQty()" class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					    </span>
					
						<input class="btn btn-default btn-lg btn-qty" id="quantity" value="1" />
					
					  	<span class="btn btn-default btn-lg btn-qty">
					   		<span onclick="descreaseQty()" class="glyphicon glyphicon-minus" aria-hidden="true"></span>
					  	</span>
					</div>
					<div class="col-md-4">
						<form action="...">
							<button class="btn btn-lg btn-success">
					   			Add to Cart
					  		</button>
						</form>
					</div>
				</div>
				<div class="row"></div>
				<div class="col-md-12 panel product-description">
					${product.description}
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		var qty = document.getElementById('quantity').value;
		
		function increaseQty() {
			qty++;
			document.getElementById('quantity').value = qty;
		}
		
		function descreaseQty() {
			if (qty <= 0) return ;
			qty--;
			document.getElementById('quantity').value = qty;
		}
	</script>
</body>
</html>    