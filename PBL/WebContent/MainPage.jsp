<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="org.json.JSONArray,org.json.JSONObject,dbOperation.*,java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>VM</title>
    <link rel="stylesheet" href="vending_machine_themes.css">
</head>

<body>
  
    <div class="menu">
        <div class="title">Other vending machines</div>
        <ul class="nav">
            <!-- Dynamic content here -->
            
            <%
            
            JSONArray objArr= new JSONArray();
            vmDataRetrival objVMR = new vmDataRetrival();
        	objArr=objVMR.getVMData();
            for(int i=0;i<objArr.length();i++){
            	JSONObject obj = new JSONObject();
            	obj = (JSONObject) objArr.get(i);
            %>
            
            
            <li><a href="MainPageOther.jsp?vmid=<% out.print(obj.get("id"));%>"><%out.print(obj.get("id")); %> :<br><%out.print(obj.get("type")); %></a></li>
            
			<%}
			%>
            
        </ul>
    </div>
    
    
    <a href="Admin.jsp"><button class="admin">Admin</button></a>
   
   <script>
        function myFunction(name) {
   
			alert("Collect Your Product "+name);
			
        }
    </script>
    <div class="aboutVM">
        
        <%
        JSONObject obj = new JSONObject();
            obj = (JSONObject) objArr.get(0);
            %>
        <h3><%out.print(obj.get("id")); %></h3>
        <label>Type: <%out.print(obj.get("type")); %><br>
            Address: <%out.print(obj.get("address_street")); %>,
             		 <%out.print(obj.get("address_city")); %> - 
             		 <%out.print(obj.get("address_pincode"));%><br>
             		 
            max product: <%out.print(obj.get("p_max")); %> <br>
        </label>
        
    </div>
    <div class="vendingMachine">
        <div class="showcase">
        <%
        	JSONObject o = new JSONObject();
        	o = (JSONObject) objArr.get(0);
        	String[] arr = (String[])o.get("product_array");
        	productDataRetrival pdr = new productDataRetrival();
        	for(int j=0;j<arr.length;j++){
        		JSONObject d = new JSONObject();
        		d = pdr.getProductData(arr[j]);
        		int s=0;
        %>
        
        <div onclick="myFunction()">
                <img src="data:image/jpeg;base64,<%out.print(d.get("image")); %>" alt="" width="80px" height="80px" >
                Rs. <%out.print(d.get("price")); %>
            </div>
          <%s++;} %>
            
        </div>
        
        <div class="stopper" style="left: 10px;"></div>
        <div class="stopper" style="right: 10px;"></div>
        <div class="trey"></div>
        <div class="treyLid"></div>
        
        <div class="sideButtons">
            <img src="sideButtons.jpg" alt="sideButtons">
        </div>
        
        <div class="info">
            click on product to buy.
        </div>
        
    </div>
</body>

</html>