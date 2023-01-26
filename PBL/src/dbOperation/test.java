package dbOperation;
import org.json.*;

import connect.connection;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class test {
	public static void main(String[] arg) {
//		ItemTableOP d = new ItemTableOP();
//		JSONObject obj = d.itemPurchase("VM3","p1",10);
//		JSONObject obj1 = d.itemPurchase("VM1","p2");

		productDataRetrival pdr = new productDataRetrival();
		JSONArray d = new JSONArray();
		d = pdr.getProductData();
		System.out.println(d.toString());
		
//		ItemTableOP B = new ItemTableOP();
//		B.setTable();
//
//		System.out.println(obj.toString());
//		
//		
////		recordTableOP t = new recordTableOP();
////		t.refilled("VM1","p1");
		
		try {
			Connection con = connection.dbConnect();
			Statement st = con.createStatement();
			
//			st.executeUpdate("INSERT INTO product VALUES(100001,  'Coca-Cola', 'drink', 30 , null)");
//					st.executeUpdate("INSERT INTO product VALUES(100002,	'Coca-Cola C2',	'drink', 40, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100003,	'Coca-Cola Light',	'drink', 30, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100004,	'Coca-Cola Orange',	'drink', 50, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100005,  'Coca-Cola Raspberry',	'drink', 50, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100006,	'Coca-Cola Vanilla', 'drink', 70, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100007,	'Diet Coke', 'drink', 30, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100008,	'Diet Coke Plus', 'drink',	60,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100009,	'Limca', 'drink', 30, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100010,	'Sprite', 'drink',	40,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100011,	'Sprite Plus',	'drink', 30, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100012,	'Sprite Cranberry',	'drink', 50, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100013,	'Mirinda', 	'drink', 50, null)");
//					st.executeUpdate("INSERT INTO product VALUES(100014,	'Pepsi',	'drink',	70,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100015,	'Pepsi Fire',	'drink',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100016,	'Pepsi Lime',	'drink',	60,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100017,	'7 Up',	'drink',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100018,	'Dr Pepper',	'drink',	40,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100019,	'Squirt',	'drink',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100020,	'Hamoud',	'drink',	50,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100021,	'Orange Blaze',	'drink',	50,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100022,	'Fiji',	'drink',	70,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100023,	'Aquafina',	'drink',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100024,	'Nestle',	'drink',	60,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100025,	'Copella',	'drink',	40,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100026,	'Aperol',	'drink',	40,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100027,	'French Fries',	'frozen',	50,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100028,	'Soya Chaap',	'frozen',	180,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100029,	'Frozen Chapati',	'frozen',	100,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100030,	'Frozen Paratha',	'frozen',	150,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100031,	'Spring Roll',	'frozen',	150,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100032,	'Chicken Seekh Kebab',	'frozen',	240,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100033,	'Cheese Corn Nuggets',	'frozen',	140,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100034,	'Frozen Burger',	'frozen',	100,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100035,	'Sweet Corn',	'frozen',	130,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100036,	'Veggi Cutlets',	'frozen',	130,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100037,	'Lays Classic Chips',	'snack',	50,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100038,	'Lays Cheddar Jalapeno Chips',	'snack',	60,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100039,	'Lays Cream and Onion Chips',	'snack',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100040,	'Lays Sweet Heat Chips',	'snack',	60,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100041,	'Lays Chile Limon Chips',	'snack',	60,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100042,	'Balaji Simple Salted Chips',	'snack',	20,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100043,	'Balaji TomChi Chips',	'snack',	20,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100044,	'Balaji Sweet Tomato Chips',	'snack',	20,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100045,	'Diamond Classic Chips',	'snack',	20,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100046,	'Diamond Tomato Chips',	'snack',	20,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100047,	'Too Yumm Indian Masala Chips',	'snack',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100048,	'Too Yumm Spanish Tomato Chips',	'snack',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100049,	'Too Yumm Kashmiri Chilli Chips',	'snack',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100050,	'Kurkure Classic',	'snack',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100051,	'Kurkure Tomato',	'snack',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100052,	'Dairy Milk Cadbury - Small',	'chocolate',	10,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100053,	'Dairy Milk Cadbury - Medium',	'chocolate',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100054,	'Dairy Milk Cadbury - Large', 	'chocolate',	50,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100055,	'Dairy Milk Silk',	'chocolate',	45,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100056,	'Dairy Milk Bubble',	'chocolate',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100057,	'Dairy Milk Dry Fruit',	'chocolate',	120,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100058,	'5-Star Chocobar',	'chocolate',	20,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100059,	'Kit-Kat',	'chocolate',	25,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100060,	'Kit-Kat Extra',	'chocolate',	50,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100061,	'Munch',	'chocolate',	20,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100062,	'Crispello',	'chocolate',	40,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100063,	'Amul Cacao',	'chocolate',	100,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100064,	'Amul Dark Chocolate',	'chocolate',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100065,	'Hersheys Classic',	'chocolate',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100066,	'Hersheys Dark',	'chocolate',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100067,	'Cornetto Classic',	'icecream',	50,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100068,	'Cornetto Chocolate Hugs',	'icecream',	100,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100069,	'Cornetto Black and White',	'icecream',	100,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100070,	'Amul Chocobar',	'icecream',	30,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100071,	'Amul Choco Almond',	'icecream',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100072,	'Amul Sweetheart',	'icecream',	100,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100073,	'Mother Dairy Strawberry Crush',	'icecream',	100,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100074,	'Mother Dairy Mewa Malai',	'icecream',	150,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100075,	'Kwality Walls Double Chocolate',	'icecream',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100076,	'Kwality Walls Cornetto',	'icecream',	120,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100077,	'Vadilal Volcano Chocolate',	'icecream',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100078,	'Vadilal Mango Treat',	'icecream',	150,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100079,	'Vadilal Butterscotch',	'icecream',	80,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100080,	'Vadilal Prime Kesar',	'icecream',	200,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100081,	'Amul Pista Badam',	'icecream',	240,	null)");
//					st.executeUpdate("INSERT INTO product VALUES(100082,	'Amul Belgian Chocolate',	'icecream',	150,	null)");
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		
//		JSONArray obj = new JSONArray();
//		JSONObject ob = new JSONObject();
//		ob = (JSONObject) obj.get(0);
//		String[] d = null;
//		int i = d.length;
//		System.out.println(obj);
	}
}
