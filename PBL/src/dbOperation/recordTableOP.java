package dbOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import connect.connection;
import java.time.format.DateTimeFormatter;

//This class deals with the operation related to the Table RECORD

public class recordTableOP {
	
	//This function is used insert the refilling record of product whenever refilling is done
	// recordTableOP obj = new recordTableOP()
	// obj.refilled("VM1","p1");
	//returns JSON object with error status
	
	public JSONObject refilled(String vm_id, String product_id) {
		JSONObject obj = new JSONObject();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();		
			
			st.executeQuery("insert into records values (record_id.NEXTVAL, '"+vm_id+"','"+product_id+"','"+last_modify+"', null ,'Refilled')");
			obj.put("error", false);
		}
		catch(Exception e){
			System.out.println(e);
			obj.put("error", true);
		}
		return obj;
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/

	//This function used insert the sales record of product whenever purchase is done
	// notificationTableOP obj = new notificationTableOP()
	// obj.coinboxFullNotification("VM1","p1");
	//returns JSON object with error status
	
	public JSONObject sold(String vm_id, String product_id, int quantity) {
		JSONObject obj = new JSONObject();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();		
			
			st.executeQuery("insert into records values (record_id.NEXTVAL, '"+vm_id+"','"+product_id+"','"+last_modify+"','"+quantity+"','Sold')");
			obj.put("error", false);
		}
		catch(Exception e){
			System.out.println(e);
			obj.put("error", true);
		}
		return obj;
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/

	// This function used get the records data 
	// notificationTableOP obj = new notificationTableOP()
	// obj.getRecordData();
	// returns JSON object with record table data
	
	public static JSONArray getRecordData(String vmid) {
		JSONArray arr = new JSONArray();
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from records where vm_id = "+vmid);
			
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getInt("record_id"));
				obj.put("vm_id", rs.getString("vm_id"));
				obj.put("product_id", rs.getString("product_id"));
				obj.put("timetamp", rs.getString("timetamp"));
				obj.put("product_quantity", rs.getInt("product_quantity"));
				obj.put("action", rs.getString("action"));
				
				arr.put(obj);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return arr;
	}
}
