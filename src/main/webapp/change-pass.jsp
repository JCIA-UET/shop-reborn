<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
	<title>JCIA Shop - Change Password</title>
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
	<div class= "container">
		 <div class="well col-md-6">
		 	<h2>Change Password</h2>
		 	<form action="AccountService" method="post" class="form-horizontal" id="myForm">
		 		<div class="form-group required">
		            <label class="col-sm-3 control-label" for="address">Username</label>
		            <div class="col-sm-9">
		                <input name="username" placeholder="Username" id="username" class="form-control" type="text" value="${account.realName}" disabled>
            		</div>
                </div>
                 <div class="form-group required">
		            <label class="col-sm-3 control-label" for="password">Password</label>
		            <div class="col-sm-9">
		                <input name="password" placeholder="Password" id="password" class="form-control" type="password"  required="required">
            			<div class="alert alert-danger" style="display: none" id="alert-passnotmatch">
	   				 	pass not match!
 					</div>
            		</div>
                </div>
                
                 <div class="form-group required">
		            <label class="col-sm-3 control-label" for="password">New Password</label>
		            <div class="col-sm-9">
		                <input name="newpassword" placeholder="New Password" id="newpassword" class="form-control" type="password"  required="required">
            		</div>
                </div>
                <div class="form-group required">
		            <label class="col-sm-3 control-label" for="re-password">Re-type New Password</label>
		            <div class="col-sm-9">
		                <input name="re-new-password" placeholder="Re-type New Password" id="re-new-password" class="form-control" type="password" required="required">
            			<div class="alert alert-danger" style="display: none" id="alert-repassnotmatch">
	   				 	 RE-NEW-PASSWORD NOT MATCH!!!
 					</div>
            		</div>
                </div>
                 <input type="hidden" name="action" value="savepass">
                <div class="col-sm-3"></div>
                <div class="col-sm-2">
                	 <input type="button" class="btn btn-primary pull-left" id="btn-savepass" onclick="save()" value="Save">
                	
                </div>
                <div class="col-sm-2">
                	<a href="index.jsp" role="button" class="btn btn-danger">Cancel</a>
                </div>
               <input type="hidden" id="passreal" value = "${account.password}">
               <%-- <input type="hidden" id="message" value='${message}'> --%>
		 	</form>
		 	 
		 </div>
		
		 
	</div>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	
<script type="text/javascript">
	function save(){
		var passreal = document.getElementById("passreal").value;
		var pass = document.getElementById("password").value;
		var newpass = document.getElementById("newpassword").value;
		var renewpass = document.getElementById("re-new-password").value;
		
		if(passreal != pass){
			document.getElementById("alert-passnotmatch").style.display ="block";
		}
		if(newpass != renewpass){
			document.getElementById("alert-repassnotmatch").style.display="block";
		}
		if(passreal == pass && newpass == renewpass ){
			document.getElementById("myForm").submit();
		}
	}
/* 	if(document.getElementById("message").value != ""){
		alert(document.getElementById("message").value);
	} */
</script>

	
</body>
</html>