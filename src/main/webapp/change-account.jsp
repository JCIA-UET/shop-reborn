<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<title>JCIA Shop - ChangeAccount</title>
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
	
	<div class="container">
	    <div class="well col-md-6 center-block text-center">
	    	<h2> Your Account</h2>
	    	
	    	<form action="AccountService" method="post" class="form-horizontal" id="myForm">
		    	<legend>Account</legend>
		    		<div class="form-group required">
			            <label class="col-sm-4 control-label" for="address">Username</label>
			            <div class="col-sm-8">
			                <input name="username" placeholder="Username" id="username" class="form-control" type="text" required="required" value='${account.username}' readonly>
	            		</div>
	            		
	                </div>
	    		<legend>Personal Details</legend>
	    		<div class="form-group required ">
		            <label class="col-sm-4 control-label" for="realname">Full Name</label>
		            <div class="col-sm-8 ">
		                <input name="fullname" placeholder="Full Name" id="realname" class="form-control" type="text" required="required" value='${account.realName}' readonly>
            		</div>
            		
                </div>
                <div class="form-group required">
		            <label class="col-sm-4 control-label" for="phonenumber">Phone number</label>
		            <div class="col-sm-8">
		                <input name="phonenumber" placeholder="Phone number" id="phonenumber" class="form-control" type="text" required="required" value='${account.phone}' readonly>
            		</div>
            		
                </div>
                <legend>Address</legend>
                <div class="form-group">
		            <label class="col-sm-4 control-label" for="city">City</label>
		            <div class="col-sm-8">
		                <input name="city" placeholder="City" id="city" class="form-control" type="text" required="required" value='${account.city}' readonly>
            		</div>
            		
                </div>
                <div class="form-group required">
		            <label class="col-sm-4 control-label" for="address">Address</label>
		            <div class="col-sm-8">
		                <input name="address" placeholder="Address" id="address" class="form-control" type="text" required="required" value='${account.address}' readonly>
            		</div>
            		
                </div>
                
                <div class="form-group required">
		            <label class="col-sm-4 control-label" for="password">Password</label>
		            <div class="col-sm-8">
		              		<p>No change over three days</p>
            		</div>
                </div>
                <input type="hidden" name="action" value="edit-info">
                <div class="form-group required">
                	<input value="Save" class=" col-sm-6 btn btn-primary" type="submit" style="display:none" id="btn-save">
               		 <a class="btn btn-danger col-sm-6" href="index.jsp" role="button" style="display:none" id="btn-cancel">Cancel</a>
               	</div>
                <input value="Edit" class="btn btn-primary" type="button" onclick="edit()" id="btn-edit" >
                <a class="btn btn-danger" href="index.jsp" role="button" id="btn-backhome">Back Home</a>
               
	    	</form>
	    </div>
	</div>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		function edit(){
			document.getElementById("realname").readOnly = false;
			document.getElementById("phonenumber").readOnly = false;
			document.getElementById("city").readOnly = false;
			document.getElementById("address").readOnly = false;
			document.getElementById("btn-edit").style.display = 'none' ;
			document.getElementById("btn-backhome").style.display = 'none';
			document.getElementById("btn-save").style.display = 'block' ;
			document.getElementById("btn-cancel").style.display = 'block' ;
		}
	</script>
</body>
</html>