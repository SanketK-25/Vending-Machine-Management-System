package dbOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import connect.connection;

//This class deals with the operation related to the Table COINBOX

public class coinboxTableOP {
	
	//This function is used empty the coinbox of vending machine
	// coinboxTableOP obj = new coinboxTableOP()
	// obj.collectCoinbox("VM1");
	//returns JSON object with error status
	
	public JSONObject collectCoinbox(String vm_id) {
		JSONObject obj = new JSONObject();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();	
			int balance = 0;
			ResultSet rs = st.executeQuery("select vm_balance as balance from vending_machine where vm_id='"+vm_id+"'");

			while(rs.next()) {
				balance = rs.getInt("balance");
			}
			
			st.executeQuery("insert into coinbox values (collection_id.NEXTVAL, '"+vm_id+"',"+balance+",'"+last_modify+"')");
			st.executeUpdate("update vending_machine set vm_balance=0");
			
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
	// obj.coinboxFullNotification("VM1","p1");
	// returns JSON object with record table data
	
	public static JSONArray getCollectionData(String vmid) {
		JSONArray arr = new JSONArray();
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from coinbox where vm_id="+vmid);
			
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getInt("collection_id"));
				obj.put("vm_id", rs.getString("vm_id"));
				obj.put("amount", rs.getInt("amount"));
				obj.put("timetamp", rs.getString("timetamp"));
				
				arr.put(obj);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return arr;
	}
}
