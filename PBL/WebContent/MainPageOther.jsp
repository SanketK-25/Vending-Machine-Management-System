<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="org.json.JSONArray,org.json.JSONObject,dbOperation.*,java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>VM</title>
<link rel="stylesheet" href="vending_machine_themes.css">
</head>

<body>
	<script>
		var vmid = document.getElementById('vmid');
	</script>
	
	<script>
		function myFunction() {
			alert("Collect your product");
		}
	</script>
	<%
	String vmid=request.getParameter("vmid");
	System.out.print("dbchdbu - "+vmid);
	%>
	<div class="menu">
		<div class="title">Other vending machines</div>
		<ul class="nav">
			<%
				JSONArray objArr = new JSONArray();
			vmDataRetrival objVMR = new vmDataRetrival();
			objArr = objVMR.getVMData();
			for (int i = 0; i < objArr.length(); i++) {
				JSONObject obj = new JSONObject();
				obj = (JSONObject) objArr.get(i);
			%>

			<li><a href="MainPageOther.jsp?vmid=<% out.print(obj.get("id"));%>">
					<%
						out.print(obj.get("id"));
					%> :<br>
					<%
						out.print(obj.get("type"));
					%>
			</a></li>

			<%
				}
			%>

		</ul>
	</div>


	<a href="login.html"><button class="admin">Admin</button></a>


	<div class="aboutVM">
		
		<%
			JSONObject obj = new JSONObject();
		vmDataRetrival objVMR1 = new vmDataRetrival();
		obj = objVMR1.getVMData(vmid);
		%>
		<h3><%
						out.print(obj.get("id"));
					%></h3>
		<label>Type: <%
			out.print(obj.get("type"));
		%><br> Address:
			<%
			out.print(obj.get("address_street"));
		%>, <%
			out.print(obj.get("address_city"));
		%>
			- <%
			out.print(obj.get("address_pincode"));
		%><br> max product: <%
			out.print(obj.get("p_max"));
		%>
			<br>
		</label>
	</div>
	<div class="vendingMachine">
		<div class="showcase">
			<%
			String[] arr = (String[]) obj.get("product_array");
			productDataRetrival pdr = new productDataRetrival();
			for (int j = 0; j < arr.length; j++) {
				JSONObject d = new JSONObject();
				d = pdr.getProductData(arr[j]);
			%>
			<div onclick="myFunction()">
				<img src="data:image/jpeg;base64,<%out.print(d.get("image"));%>"
					alt="" width="80px" height="80px"> Rs.
				<%
					out.print(d.get("price"));
				%>
			</div>
			<%
				}
			%>

		</div>

		<div class="stopper" style="left: 10px;"></div>
		<div class="stopper" style="right: 10px;"></div>
		<div class="trey"></div>
		<div class="treyLid"></div>

		<div class="sideButtons">
			<img src="sideButtons.jpg" alt="sideButtons">
		</div>

		<div class="info">click on product to buy.</div>

	</div>
</body>

</html>