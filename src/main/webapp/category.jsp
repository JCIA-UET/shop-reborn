<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>JCIA Shop</title>
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
	    <div class="well well-sm">
	        <strong>${categoryName}</strong>
	        <div class="btn-group">
	            <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
	            </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
	                class="glyphicon glyphicon-th"></span>Grid</a>
	        </div>
	    </div>
	    <div id="categories-list" class="row list-group">
	    	<c:if test="${not empty products}">
	    		<c:forEach var="p" items="${products}">
	    			<div class="item  col-xs-4 col-lg-4">
	    				<div class="thumbnail">
	    					<img class="group list-group-image" src="${p.imageLink}" alt="" />
	    					<div class="caption">
			                    <h4 class="group inner list-group-item-heading">
			                        ${p.name}</h4>
			                    <p class="group inner list-group-item-text">
			                    	<c:set var="shortDes" value="${p.description}"></c:set>
			                    	<c:if test="${fn:length(shortDes) > 120}">
			                    		<c:set var="shortDes" value="${fn:substring(shortDes, 0, 120)}..."></c:set>
			                    		${shortDes}
			                    	</c:if>
			                    </p>
			                    <div class="row">
			                        <div class="col-xs-12 col-md-6">
			                            <p class="lead">
			                            	<fmt:formatNumber type="currency" value="${p.price}">
			                            	</fmt:formatNumber> 
			                            </p>
			                        </div>
			                        <div class="col-xs-12 col-md-6">
			                            <a class="btn btn-success" href="#">Add to cart</a>
			                        </div>
			                    </div>
			                </div>
	    				</div>
	    			</div>
	    		</c:forEach>
	    	</c:if>
	    	        
	    </div>
	</div>
		
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>    