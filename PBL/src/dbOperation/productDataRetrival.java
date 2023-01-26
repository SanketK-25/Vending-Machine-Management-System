package dbOperation;

import connect.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import org.json.*;

//This class deals with the retrieval data of the Table PRODUCTE

public class productDataRetrival {
	
	// Below Function returns all the product data available in our data base
	//	productDataRetrival obj = new productDataRetrival();
	//	obj.getProductData();
	
	public static JSONArray getProductData() {
		JSONArray arr = new JSONArray();
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select p.product_id as id,"
					+ "p.product_name as name, "
					+ "p.product_type as type, "
					+ "p.product_price as price ,"
					+ "p.product_image as image "
					+ "from product p");
			
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getString("id"));
				obj.put("name", rs.getString("name"));
				obj.put("type", rs.getString("type"));
				obj.put("price", rs.getString("price"));
					byte[] imageBytes = rs.getBytes("image");
					String base64encodedImage = Base64.getEncoder().encodeToString(imageBytes);
				obj.put("image", base64encodedImage);
				
				arr.put(obj);
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return arr;
	}
	
/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	//Below function returns data of product whose id is provided
	//	productDataRetrival obj = new productDataRetrival();
	//	obj.getProductData("p1");
	
	public static JSONObject getProductData(String p_id) {
		JSONObject obj = new JSONObject();
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select p.product_id as id,"
					+ "p.product_name as name, "
					+ "p.product_type as type, "
					+ "p.product_price as price ,"
					+ "p.product_image as image "
					+ "from product p where product_id='"+p_id+"'");
			
			while(rs.next()) {
				
				obj.put("id", rs.getString("id"));
				obj.put("name", rs.getString("name"));
				obj.put("type", rs.getString("type"));
				obj.put("price", rs.getString("price"));
					byte[] imageBytes = rs.getBytes("image");
					String base64encodedImage = Base64.getEncoder().encodeToString(imageBytes);
				obj.put("image", base64encodedImage);
			}			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return obj;
	}
}
