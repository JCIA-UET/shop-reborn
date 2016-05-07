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

    <title>Update Product</title>

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
					<h1 class="page-header">Update category</h1>
					<div class="panel panel-default">
						<div class="panel-heading">
							Please upload image first
						</div>
						<div class="panel-body">
							<div class="col-md-10">
								<form action="ResourcesService" role="form" method="post" enctype="multipart/form-data">
									<div class="form-group">
		                                <label>Image</label>
		                                <br/>
		                                <c:if test="${not empty product.imageLink}">
		                                	<img src="../${product.imageLink}" style="width:50px; height:auto;"/>
		                                </c:if>
		                                <input type="file" name="file"/>
		                                <input type="hidden" name="action" value="uploadimage"/>
		                                <button type="submit">Upload</button>
	                                </div>
								</form>
								<form action="AdProductService" role="form" method="post">
									<input type="hidden" name="productid" value="${product.id}"/>
									<div class="form-group">
		                                <label>Product Name</label>
		                                <input class="form-control" type="text" name="name" value="${product.name}" required>
	                                </div>
									<div class="form-group">
		                                <label>Category</label>
		                                <select class="form-control" name="categoryid">
		                                	<c:forEach var="c" items="${categories}">
		                                		<option value="${c.id}">${c.name}</option>
		                                	</c:forEach>
		                                </select>
	                                </div>
									<div class="form-group">
		                                <label>Price</label>
		                                <input class="form-control" type="number" step="0.01" name="price" value="${product.price}" required>
	                                </div>
									<div class="form-group">
		                                <label>Quantity</label>
		                                <input class="form-control" type="number" id="quantity" name="quantity" value="${product.quantity}" required>
	                                </div>
	                                <div class="form-group">
		                                <label>Description</label>
		                                <textarea rows="10" class="form-control" name="description" required>${product.description}</textarea>
	                                </div>
	                                
	                                <input type="hidden" name="action" value="updateproduct"/>
	                                <button type="submit" class="btn btn-primary pull-right">Submit</button>
								</form>
							</div>
						</div>
					</div>
    
	<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>