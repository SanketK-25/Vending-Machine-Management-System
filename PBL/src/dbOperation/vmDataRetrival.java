package dbOperation;

import connect.connection;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.*;

// This class deals with the retrieval data of the Table VENDING MACHINE

public class vmDataRetrival {
	
	// Below Function returns all the vending machine data available in our data base
	//	vmDataRetrival obj = new vmDataRetrival();
	//	obj.getVMData();
	
	public static JSONArray getVMData() {
		JSONArray arr = new JSONArray();
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select b.vm_id as id,"
					+ "b.vm_address.street as street, "
					+ "b.vm_address.city as city, "
					+ "b.vm_address.pincode as pincode ,"
					+ "b.vm_type as type ,"
					+ "b.vm_item_count as item_count, "
					+ "b.vm_product_array as product_array ,"
					+ "b.vm_p_min as p_min ,"
					+ "b.vm_p_max as p_max ,"
					+ "b.vm_balance as balance "
					+ "from vending_machine b");
			
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getString("id"));
				obj.put("address_street", rs.getString("street"));
				obj.put("address_city", rs.getString("city"));
				obj.put("address_pincode", rs.getString("pincode"));
				obj.put("type", rs.getString("type"));
				obj.put("item_count", rs.getString("item_count"));
					Array a = rs.getArray("product_array");
					String[] array = (String[])a.getArray();
				obj.put("product_array", array);
				obj.put("p_min", rs.getString("p_min"));
				obj.put("p_max", rs.getString("p_max"));
				obj.put("balance", rs.getString("balance"));
				
				arr.put(obj);
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return arr;
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	//Below function returns data of vending machine whose id is provided
	//	vmDataRetrival obj = new vmDataRetrival();
	//	obj.getVMData("VM1");
	
	public static JSONObject getVMData(String vm_id) {
		JSONObject obj = new JSONObject();
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select b.vm_id as id,"
					+ "b.vm_address.street as street, "
					+ "b.vm_address.city as city, "
					+ "b.vm_address.pincode as pincode ,"
					+ "b.vm_type as type ,"
					+ "b.vm_item_count as item_count, "
					+ "b.vm_product_array as product_array ,"
					+ "b.vm_p_min as p_min ,"
					+ "b.vm_p_max as p_max ,"
					+ "b.vm_balance as balance "
					+ "from vending_machine b where vm_id='"+vm_id+"'");
			
			System.out.println(rs.getRow());
			while(rs.next()) {
				
				obj.put("id", rs.getString("id"));
				obj.put("address_street", rs.getString("street"));
				obj.put("address_city", rs.getString("city"));
				obj.put("address_pincode", rs.getString("pincode"));
				obj.put("type", rs.getString("type"));
				obj.put("item_count", rs.getString("item_count"));
					Array a = rs.getArray("product_array");
					String[] array = (String[])a.getArray();
				obj.put("product_array", array);
				obj.put("p_min", rs.getString("p_min"));
				obj.put("p_max", rs.getString("p_max"));
				obj.put("balance", rs.getString("balance"));

			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return obj;
	}
}
