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

    <title>Products</title>

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

	<!-- header -->
<jsp:include page="header.jsp"></jsp:include>
        
		<!-- content -->
                    <h1 class="page-header">Products List</h1>
                    
                    <a href="AdProductService?action=req2addp">
                    	<button class="btn btn-primary pull-right"><span class="fa fa-plus"></span></button>
                    </a>
                    
                    <table id="tbProducts" class="table table-striped table-borderd">
                    	<thead>
                    		<tr>
	                    		<th>Product</th>
	                    		<th>Category</th>
	                    		<th>Price</th>
	                    		<th class="text-right">Quantity</th>
	                    		<th class="text-right">Action</th>
                    		</tr>
                    	</thead>
                    	<tbody>
                    		<c:forEach var="p" items="${productsList}">
                    			<tr>
                    				<td>${p.name}</td>
                    				<td>${p.categoryId}</td>
                    				<td><fmt:formatNumber type="currency" value="${p.price}"></fmt:formatNumber></td>
                    				<td class="text-right">${p.quantity}</td>
                    				<td class="text-right">
                    					<a href="AdProductService?action=gpbyid&productid=${p.id}">
	                    					<button class="btn btn-primary"><span class="fa fa-pencil-square-o"></span></button>
	                    				</a>
	                    				<button onclick="document.getElementById('removeform${p.id}').submit();" class="btn btn-danger"><span class="fa fa-trash-o"></span></button>
	                    				
	                    				<form action="AdProductService" method="post" id="removeform${p.id}">
	                    					<input type="hidden" name="productid" value="${p.id}">
	                    					<input type="hidden" name="action" value="removeproduct">
	                    				</form>
                    				</td>
                    			</tr>
                    		</c:forEach>
                    	</tbody>
                    </table>
               
	<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>