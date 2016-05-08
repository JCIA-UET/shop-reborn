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
    <title>Customers</title>

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
	<h1 class="page-header">Customers List</h1>
	<table id="tableUser" class="table table-striped table-borderd">
		<thead>
               <tr>
	               <th>ID</th>
		           <th>Username</th>
		           <th>Real Name</th>
		           <th>Phone</th>
		            <th>City</th>
               </tr>
        </thead>
	
	<tbody>
		<c:forEach var="account" items="${listAccount}">
			<tr>
				<td>${account.getId()}</td>
				<td>${account.getUsername()}</td>
				<td>${account.getRealName()}</td>
				<td>${account.getPhone()}</td>
				<td>${account.getCity()}</td>
				<td class="text-right">
                    <a href="UserService?action=gusbid&userid=${account.id}">
	                   <button class="btn btn-primary"><span class="fa fa-pencil-square-o"></span></button>
	                 </a>
	                  <button onclick="document.getElementById('removeform${account.id}').submit();" class="btn btn-danger"><span class="fa fa-trash-o"></span></button>
	                  	<form action="UserService" method="post" id="removeform${account.id}">
	                    					<input type="hidden" name="userid" value="${account.id}">
	                    					<input type="hidden" name="action" value="removeUser">
	                    </form>			
	           
                 </td>				
			</tr>
		</c:forEach>
	</tbody>
	</table>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>