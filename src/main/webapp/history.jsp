<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JCIA Shop - Order History</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="assests/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="assests/js/history.js"></script>
    <!-- custom style -->
	<link href="assests/css/custom.css" rel="stylesheet"/>
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="container">
		<div class="col-lg-12 col-md-12 col-sm-12">
			<legend><h3>Order History</h3></legend>
			<div id="main-table">
				<table class="table table-hover">
					<thead class="">
						<tr>
							<th>Order ID</th>
							<th>Order Time</th>
							<th>TotalCash</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="htr-body">
					<c:forEach var="order" items="${orderlist}" varStatus="indexVar">
						<tr>
							<td>${order.id}</td>
							<td>${order.getOrderDate()}</td>
							<td>${order.total}</td>
							<td>${order.status}</td>
							<td><button class="btn btn-info" onclick='loadData("${order.id}")' data-toggle="modal" data-target="#orderModal">Detail</td>
						</tr>
						<div class="modal fade" id="orderModal" role="dialog">
    						<div class="modal-dialog">
								<div class="modal-content">
		        					<div class="modal-header">
		          						<button type="button" class="close" data-dismiss="modal">&times;</button>
		          						<h4 class="modal-title">Order Detail</h4>
		        					</div>
		        					<div class="modal-body" id="body">
		        					</div>
		        					<div class="modal-footer">
		          						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        					</div>
		      					</div>
		      				</div>
		      			</div>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>