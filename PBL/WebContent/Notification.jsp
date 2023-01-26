<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="org.json.JSONArray,org.json.JSONObject,dbOperation.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Notification</title>
</head>

<style type="text/css">
	*{
			margin:  0;
			padding: 0;
			box-sizing: border-box;
		}
		body{
			background:#2F4F4F	;
			font-family: arial;
		}
		.container{
			position: absolute;
			left: 50%;
			top: 10%;
			transform: translate(-50%, -50%);
			width: 1000px;
			height: 50px;
			border-bottom:5px solid #FFFAFA;
			line-height: 50px;
			background: #2F4F4F	;
			overflow: hidden;

		}
		.container span{
			color: #FFF8DC ;
			font-size: 30px;
			
			display: inline-block;
			padding: 2px 20px;
			text-transform: uppercase;
		}
		.container span:before{
			position: absolute;
			content: "";
			width: 90%;
			height: 100%;
			background: #2F4F4F ;
			z-index: -1;
			tranformation:rotate(45deg);
		}
		.table-container{
			padding: 0 10%;
			margin: 40px auto 0;


		}
		.heading{
			font-size: 30px;
			text-align: center;
			color: #f1f1f1;
			margin-bottom: 40px;
			margin-top: 180px;
		}
		.table{
			width: 100%;
			border-collapse: collapse;


		}
		.table thead{
			background-color: #ee2828;
		}
		.table thead tr th{
			font-size: 14px;
			fount-weight:600;
			letter-spacing: 0.35px;
			color: #ffffff;
			opacity: 1;
			padding: 12px;
			vertical-align: top;
			border: 1px solid #dee2e658;

		}
		.table tbody tr td{
			font-size: 14px;
			letter-spacing: 0.35px;
			font-weight: normal;
			color: #f1f1f1;
			background-color: #3c3f44;
			padding: 8px;

			text-align: center;
			border: 1px solid #dee2e685;

		}
		.table .text_open{
			font-size: 14px;
			font-weight: bold;
			letter-spacing: 0.35px;
			color: #FF1046;

		}
		.table tbody tr td .btn{
			width: 130px;
			text-decoration: none;
			line-height: 35px;
			display: inline-block;
			background-color: #FF1046;
			font-weight: medium;
			color: #FFFFFF;
			text-align: center;
			vertical-align: middle;
			user-select: none;
			border: 1px solid transparent;
			font-size: 14px;
			opacity: 1;



		}
	@media (max-width: 768px){
		.table thead{
			display: none;
		}
		.table, .table tbody,.table tr,.table td{
			display: block;
			width: 100%;

		}
	}

///

.grid--50-50 {
    display: grid;
    grid-template-columns: 50% 50%;
    align-items: center;
}

.field input {
    font-size: 16px;
    line-height: 28px;
    padding: 8px 16px;
    width: 13%;
    min-height: 44px;
    border: unset;
    border-radius: 4px;
    outline-color: rgb(84 105 212 / 0.5);
    background-color: rgb(255, 255, 255);
    box-shadow: rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(60, 66, 87, 0.16) 0px 0px 0px 1px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px;
}

input[type="submit"] {
    background-color: #3c3f44;
    box-shadow: rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(0, 0, 0, 0.12) 0px 1px 1px 0px, 
                rgb(255, 16, 70) 0px 0px 0px 1px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(0, 0, 0, 0) 0px 0px 0px 0px, 
                rgba(60, 66, 87, 0.08) 0px 2px 5px 0px;
    color: #fff;
    font-weight: 600;
    cursor: pointer;
    float: left;
    margin-left: 130px;
    margin-bottom: 50px;
}

</style>
<body>
		<%  
			String vmid=request.getParameter("vmid");
            JSONArray objArr= new JSONArray();
			notificationTableOP objVMR = new notificationTableOP();
        	objArr=objVMR.getNotificationData(vmid);
        %>

 <div class="container">
		<span>Vending Administration</span>
	</div>
    
    <div class ="table-container">
    	<h1 class ="heading" >Notifications</h1>

    	<div class="field padding-bottom--24">
     	<a href="V_Details.html">
    <input type="submit" name="V_details" value="V_details">
</a>    

    	<div class="field padding-bottom--24">
     	<a href="Admin.html">
    <input type="submit" name="Home" value="Home">
</a>
	
     <div class="field padding-bottom--24">
     	<a href="record.jsp">
    <input type="submit" name="Record" value="Record">
</a>
    </div>
     <div class="field padding-bottom--24">
     	<a href="Coins.jsp">
    <input type="submit" name="Coins" value="Coins">
</a>
    </div>	 

    	<table class="table">
    		<thead>
    			<tr>
    				
    				<th>Vn_id</th>
    				<th>Product_id</th>
    				<th>description</th>
    				<th>time_stamp</th>
    				
    			</tr>
    		</thead>
    		<tbody>
    		
    		<%
			for (int i = 0; i < objArr.length(); i++) {
				JSONObject obj = new JSONObject();
				obj = (JSONObject) objArr.get(i);
			%>
    			<tr>
    				<td date-label="Vn_id"><%out.print(obj.get("vmid"));%></td>
    				<td date-label="Product_id"><%out.print(obj.get("pid"));%></td>
    				<td date-label="description"><%out.print(obj.get("disc"));%></td>
    				<td date-label="time_stamp"><%out.print(obj.get("time"));%></td>
    				
    			</tr>
    		
			<%
				}
			%>
    		</tbody>
    	</table>
    </div>
</body>
</html>