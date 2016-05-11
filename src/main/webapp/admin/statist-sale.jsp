<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
	
	<link href="../assests/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assests/css/metisMenu.min.css" rel="stylesheet">
    <link href="../assests/css/sb-admin-2.css" rel="stylesheet">
    <link href="../assests/css/statist.css" rel="stylesheet">
    <link href="../assests/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <script type="text/javascript" src="../assests/js/statist.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>

	<!-- content -->
	<h1 class="page-header">Statist Sale</h1>
	<div>
		<c:if test="${result == null && action != null && action != 'undefined'}">
			<div class="alert alert-danger">
				Data not found.
			</div>
		</c:if>
		<c:if test="${result == null && action != null && action == 'undefined'}">
			<div class="alert alert-danger">
				Please choose action first.
			</div>
		</c:if>
		<c:if test="${result != null && action != null}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Number of orders</th>
						<th>Number of products sold</th>
						<th>Revenue</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${result[0].intValue()}</td>
						<td>${result[1].intValue()}</td>
						<td>${result[2].intValue()}</td>
					</tr>
				</tbody>
			</table>
			<hr>
		</c:if>
	</div>
	<div class="row">
		<div class="items-collection">
			<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
				<input type="checkbox" id="dr" autocomplete="off" value="cdr" onclick="dr(this)"/>
				<label for="dr" class="btn btn-default">
					<div class="itemcontent">
						<h5>Daily Revenue</h5>
					</div>
				</label>
			</div>
			<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
				<input type="checkbox" id="mr" autocomplete="off" value="cmr" onclick="mr(this)"/>
				<label for="mr" class="btn btn-default">
					<div class="itemcontent">
						<h5>Monthly Revenue</h5>
					</div>
				</label>
			</div>
			<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
				<input type="checkbox" id="tr" autocomplete="off" value="ctr" onclick="tr(this)"/>
				<label for="tr" class="btn btn-default">
					<div class="itemcontent">
						<h5>Total Revenue</h5>
					</div>
				</label>
			</div>
		</div>
	</div>
	
	<div>
		<form action="StatistService" id="cal" method="post">
			<input type="hidden" id="action" name="action" value="">
			<div id="month-selector">
				<h3>Choose a month</h3>
				<select class="form-control" id="month" name="month" onchange="setDay()" size="1">
					<option value="1">January</option>
				    <option value="2">February</option>
				    <option value="3">March</option>
				    <option value="4">April</option>
				    <option value="5">May</option>
				    <option value="6">June</option>
				    <option value="7">July</option>
				    <option value="8">August</option>
				    <option value="9">September</option>
				    <option value="10">October</option>
				    <option value="11">November</option>
				    <option value="12">December</option>
				</select>
			</div>
			
			<div id="day-selector">
			<h3>Choose a day</h3>
				<select class="form-control" id="day" name="day" onchange="" size="1">
					<option value="1">1</option>
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				    <option value="5">5</option>
				    <option value="6">6</option>
				    <option value="7">7</option>
				    <option value="8">8</option>
				    <option value="9">9</option>
				    <option value="10">10</option>
				    <option value="11">11</option>
				    <option value="12">12</option>
				    <option value="13">13</option>
				    <option value="14">14</option>
				    <option value="15">15</option>
				    <option value="16">16</option>
				    <option value="17">17</option>
				    <option value="18">18</option>
				    <option value="19">19</option>
				    <option value="20">20</option>
				    <option value="21">21</option>
				    <option value="22">22</option>
				    <option value="23">23</option>
				    <option value="24">24</option>
				    <option value="25">25</option>
				    <option value="26">26</option>
				    <option value="27">27</option>
				    <option value="28">28</option>
				    <option value="29">29</option>
				    <option value="30">30</option>
				    <option value="31">31</option>
				</select>
			</div>
			
			<div id="year-selector">
				<h3>Choose a year</h3>
				<select class="form-control" id="year" name="year" onchange="" size="1">
					<option value="2016">2016</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>