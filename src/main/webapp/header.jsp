<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="context" value="${pageContext.servletContext.contextPath}"/>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${context}">JCIA Shop</a>
      
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
      <!-- Search box -->
      <li>
        	<a href="#search" title="Search">
        		<i class="glyphicon glyphicon-search"></i>
        	</a>
        </li>
      
      <!-- Account manager dropdown -->
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          		<span class="glyphicon glyphicon-user"></span>
          		Account
          		<span class="caret"></span>
          		<ul class="dropdown-menu">
	          		<c:choose>
	          			<c:when test="${not empty sessionScope.account}">
					    	<li><a href="#">My Account</a></li>
					    	<li><a href="#">Order History</a></li>
						    <li role="separator" class="divider"></li>
						    <li><a href="#" onclick="document.getElementById('logoutform').submit()">Logout</a></li>
	          			</c:when>
	          			<c:otherwise>
	          				<li><a href="register.jsp">Register</a></li>
					    	<li><a href="login.jsp">Login</a></li>
	          			</c:otherwise>
	          		</c:choose>
          		</ul>
        	</a>
        </li>
        
        <!-- Shopping cart dropdown -->
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          	<c:set var="numOfItems" value="${fn:length(sessionScope.products)}"></c:set>
          	<span class="glyphicon glyphicon-shopping-cart"></span>
          	 ${numOfItems} - Item(s)
          	<span class="caret"></span>
          </a>
          
          <ul class="dropdown-menu dropdown-cart" role="menu">
              <c:forEach var="item" items="${sessionScope.products}">
	              <li>
	                  <span class="item">
	                    <span class="item-left">
	                        <img src="http://lorempixel.com/50/50/" alt="" />
	                        <span class="item-info">
	                            <span>item.name</span>
	                            <span>item.price</span>
	                        </span>
	                    </span>
	                    <span class="item-right">
	                        <button class="btn btn-xs btn-danger pull-right">x</button>
	                    </span>
	                </span>
	              </li>
              </c:forEach>

              <li class="divider"></li>
              <li><a class="text-center" href="#">View Cart</a></li>
          </ul>
        </li>
        <li>
        	<a href="#" title="Checkout">
        		<i class="glyphicon glyphicon-floppy-save"></i>
        		<span>Checkout</span>
        	</a>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container logo">
	<div class="row">
		<div class="col-md-4">
			<a href="#" > <img alt="logo"  class="img-rounded" src="img/logo.png" width="400" height="150"></a>
		</div>
		<div class="col-md-8">
			<a href="#" > <img alt="logo" class="img-rounded" src="img/slogan.jpg" width="750" height="150"></a>
		</div>
	</div>
</div>

<div class="container">
	<div class="col-md-12 categories-list">
		<div class="dropdown">
			<span href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
				<span class="glyphicon glyphicon-list"></span>
				Categories list
			</span>
			<ul class="dropdown-menu cate-list">
			    <c:forEach var="category" items="${sessionScope.categories}">
			    	<li><a href="#">${category.name}</a></li>
			    </c:forEach>
		  	</ul>
		</div>

	</div>
</div>

<div id="search">
    <button type="button" class="close">×</button>
    <form action="ProductService">
    	<input type="hidden" name="action" value="spbyn"/>
        <input type="search" name ="productname" value="" placeholder="type product keyword(s) here" />
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
</div>
<form action="AccountService" method="post" id="logoutform">
	<input type="hidden" name="action" value="logout">
</form>

