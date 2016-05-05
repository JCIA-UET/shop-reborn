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

    <title>New Category</title>

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
					<h1 class="page-header">Add new category</h1>
					<div class="panel panel-default">
						<div class="panel-heading">
							Please complete all following fields
						</div>
						<div class="panel-body">
							<div class="col-md-10">
								<form action="AdCategoryService" role="form" method="post">
									<div class="form-group">
		                                <label>Category Name</label>
		                                <input class="form-control" type="text" name="name" required>
	                                </div>
	                                <div class="form-group">
		                                <label>Description</label>
		                                <textarea rows="10" class="form-control" name="description"></textarea>
	                                </div>
	                                
	                                <button type="submit" class="btn btn-primary pull-right">Submit</button>
								</form>
							</div>
						</div>
					</div>
    
	<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>