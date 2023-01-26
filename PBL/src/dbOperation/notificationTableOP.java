package dbOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;
import connect.connection;

//This class deals with the operation related to the Table NOTIFICATION

public class notificationTableOP {
	
	//This function is used to create a refill whenever purchase is done and the product count is below limit
	// notificationTableOP obj = new notificationTableOP()
	// obj.refillNotification("VM1","p1");
	//returns JSON object with error status
	
	public JSONObject refillNotification(String vm_id, String product_id) {
		JSONObject obj = new JSONObject();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();		
			
			st.executeQuery("insert into notifications values ('"+vm_id+"', '"+product_id+"','Product Refill' , '"+last_modify+"')");
			obj.put("error", false);
		}
		catch(Exception e){
			System.out.println(e);
			obj.put("error", true);
		}
		
		return obj;
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/

	//This function is used to create a coinbox full whenever purchase is done and the balance in machine is passed the limit
	// notificationTableOP obj = new notificationTableOP()
	// obj.coinboxFullNotification("VM1","p1");
	//returns JSON object with error status
	
	public JSONObject coinboxFullNotification(String vm_id, String product_id) {
		JSONObject obj = new JSONObject();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();		
			
			st.executeQuery("insert into notifications values ('"+vm_id+"', '"+product_id+"','Coinbox Full' , '"+last_modify+"')");
			obj.put("error", false);			
		}
		catch(Exception e){
			System.out.println(e);
			obj.put("error", true);
		}		
		return obj;
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/

	//This function is used to create a product expired whenever purchase is done and the product in machine is passed the expiry
	// notificationTableOP obj = new notificationTableOP()
	// obj.productExiredNotification("VM1","p1");
	//returns JSON object with error status
	
	public JSONObject productExiredNotification(String vm_id, String product_id) {
		JSONObject obj = new JSONObject();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();		
			
			st.executeQuery("insert into notifications values ('"+vm_id+"', '"+product_id+"','Product Expired' , '"+last_modify+"')");
			obj.put("error", false);
		}
		catch(Exception e){
			System.out.println(e);
			obj.put("error", true);
		}
		return obj;
	}
	
	
	
	public static JSONArray getNotificationData(String vmid) {
		JSONArray arr = new JSONArray();
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select p.VM_ID as vm_id,"
					+ "p.PRODUCT_ID as p_id, "
					+ "p.DESCRIPTION as disc, "
					+ "p.TIMETAMP as time "
					+ "from notifications p");
			
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("vmid", rs.getString("vm_id"));
				obj.put("pid", rs.getString("p_id"));
				obj.put("disc", rs.getString("disc"));
				obj.put("time", rs.getString("time"));
				
				arr.put(obj);
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return arr;
	}
}
