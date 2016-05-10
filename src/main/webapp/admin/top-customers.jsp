<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Top Customers</title>

    <link href="../assests/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assests/css/metisMenu.min.css" rel="stylesheet">
    <link href="../assests/css/sb-admin-2.css" rel="stylesheet">
    <link href="../assests/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<h1 class="page-header">Top buying Customers</h1>
	<div class=row>
		<form action="StatisService" method="get">
		<input type="hidden" name="action" value="gtc">
		<div class="col-md-2">
			<select class="form-control" id="sel1" name="threshold">
			    <option>5</option>
			    <option>10</option>
			    <option>15</option>
			    <option>20</option>
			    <option>25</option>
			    <option>30</option>
			    <option>35</option>
			    <option>40</option>
			    <option>45</option>
			    <option>50</option>
			 </select>
		</div>
		<div class="col-md-1">
			<button class="btn btn-primary" type="submit">Sort</button>
		</div>
		</form>
	</div>
	<div class="table-responsive">  
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No.</th>
					<th>Customer Name</th>
					<th>Number of Orders</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${customers}" varStatus="stt"> 
					<tr>
						<td>${stt.count}</td>
						<td>${c.realName}</td>
						<td>${c.numOfOrders}</td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>