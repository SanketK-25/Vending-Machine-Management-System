package dbOperation;

import java.sql.Array;
import java.util.ArrayList;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import connect.connection;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//This class deals with the operation related to the Table ITEM

public class ItemTableOP {
	
	

	//Below function used to insert the data in item table (can IGNORE)
	
	public ItemTableOP() {
		System.out.println("hiiiii");
	}

	public int setTable(){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();		
			
			ArrayList <String> vm_id = new ArrayList<String>();
			ArrayList <Integer> vm_max = new ArrayList<Integer>();
			ResultSet rs = st.executeQuery("select b.vm_id as id,b.vm_p_max as max from vending_machine b");
			while(rs.next()) {
				String id = rs.getString("id");
				int max = rs.getInt("max");
				vm_max.add(max); 
				vm_id.add(id);				
			}
			for(int i=0;i<vm_id.size();i++) {
				ResultSet rs2 = st.executeQuery("select b.vm_product_array as product_array from vending_machine b where vm_id = '"+vm_id.get(i)+"'");
				Array a = null;
				while(rs2.next()) {
					a = rs2.getArray("product_array");
				}
				String[] array = (String[])a.getArray();
				for(int j=0; j<array.length;j++) {
					System.out.println(array[j]);
					st.executeUpdate("insert into item values('"+vm_id.get(i)+"','"+array[j]+"',"+vm_max.get(i)+",'"+last_modify+"')");
				}		
			}			
		}
		catch(Exception e){
			System.out.println(e);
		}	
		
		return 1;
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	//This function is triggered whenever purchase is done
	// ItemTableOP obj = new ItemTableOP()
	// obj.itemPurchase("VM1","p1",2);
	// returns JSON object with error status and related message
	
	public JSONObject itemPurchase(String vm_id, String p_id, int quantity) {
		JSONObject obj = new JSONObject();
		System.out.print("i");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
			int product_count  = 0;
			int product_price  = 0;
//			String expiry = "";
			int p_min  = 0;
			
			ResultSet rs = st.executeQuery("select product_count from item where vm_id = '"+vm_id+"' and product_id = '"+p_id+"'");
			
			while(rs.next()) {
				product_count = rs.getInt("product_count");
			}
			
			rs = st.executeQuery("select vm_p_min,vm_balance from vending_machine where vm_id = '"+vm_id+"'");
			while(rs.next()) {
				product_price = rs.getInt("vm_balance");
				p_min = rs.getInt("vm_p_min");
			}
			
			rs = st.executeQuery("select product_price from product where product_id = '"+p_id+"'");			
			while(rs.next()) {
//				expiry = rs.getDate("product_expiry").toString();
				product_price += rs.getInt("product_price");
			}			
			
//			LocalDate d1 = LocalDate.now();
//			LocalDate d2 = LocalDate.parse(expiry);
			
//			if(d1.compareTo(d2)>0) {     	//CHECK IF THE PRODUCT IS EXPIRED OR NOT
//				obj.put("error", true);
//				obj.put("msg", "Sorry For INconviniance, Product Is expired!! will be refilled soon");
//				notificationTableOP nto = new notificationTableOP();
//				nto.productExiredNotification(vm_id, p_id);      //PUSH THE NOTIFICATION FOR EXPIRED PRODUCT
//			}			
			if(product_count==0) {
				obj.put("error", true);
				obj.put("msg", "Product out of stock!! will be refilled soon");
			}
			else if(product_count-quantity<0) { 		//CHECK IF THERE ARE ENOUGH PRODUCT TO FULLFILL PURCHAES
				obj.put("error", true);
				obj.put("msg", "Not Enough Product In Stock!!");
				obj.put("product_count", product_count);
				
			}
			else {
				product_count -= quantity;
				String last_modify = LocalDateTime.now().format(format);
				st.executeUpdate("update item set product_count = '"+product_count+"', last_modify = '"+last_modify+"' where vm_id = '"+vm_id+"' and product_id = '"+p_id+"'");
				st.executeUpdate("update vending_machine set vm_balance = '"+product_price+"' where vm_id='"+vm_id+"'");
				recordTableOP rto = new recordTableOP();
				rto.sold(vm_id, p_id, quantity);				//CALL FUNCTION TO PUSH THE SALES RECORD IN RECORD TABLE
				obj.put("error", false);
				obj.put("msg", "Data updated successfully");
				if(product_count <= p_min) {					//CHECK IF THE PRODUCT COUNT IS BELOW THRESHOULD
					notificationTableOP nto = new notificationTableOP();
					nto.refillNotification(vm_id, p_id);		//PUSH THE NOTIFICATION FOR REFILL
				}
			}
			if(product_price>5000) {							//CHECK IF THE COLLECTED AMOUNT IN VENDING MACHINE PASSED THRESHOULD
				notificationTableOP nto = new notificationTableOP();
				nto.coinboxFullNotification(vm_id, p_id);		//PUSH THE NOTIFICATION FOR COINBOX IF FULL
			}
		}
		catch(Exception e){
			System.out.println();
		}
		return obj;
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	// This function is triggered whenever purchase is done
	// ItemTableOP obj = new ItemTableOP()
	// obj.itemPurchase("VM1","p1");
	// returns JSON object with error status and related message
	
	public JSONObject itemPurchase(String vm_id, String p_id) {
		
		JSONObject obj = itemPurchase(vm_id, p_id, 1);
		return obj;
		
	}
/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	// This function is used to refill the product in particular vending machine
	// ItemTableOP obj = new ItemTableOP()
	// obj.itemRefill("VM1","p1");
	// return JSON object with error status
	
	public JSONObject itemRefill(String vm_id, String p_id,int quatity) {
		JSONObject obj = new JSONObject();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String last_modify = LocalDateTime.now().format(format);
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();

			int p_max  = 0;
			
			ResultSet rs = st.executeQuery("select vm_p_max from vending_machine where vm_id = '"+vm_id+"'");
			while(rs.next()) {
				p_max = rs.getInt("vm_p_max");
			}
			
			rs = st.executeQuery("select PRODUCT_COUNT from item where vm_id = '"+vm_id+"' and PRODUCT_ID = '"+p_id+"')");
			while(rs.next()) {
				p_max = rs.getInt("PRODUCT_COUNT");
				p_max += quatity;
			}
			
			st.executeUpdate("update item set product_count = '"+p_max+"', last_modify = '"+last_modify+"'");	
			obj.put("error", false);
		}
		catch(Exception e){
			System.out.println();
			obj.put("error", true);
		}
		return obj;
	}
	
}
