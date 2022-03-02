package POM_task.Utils;

import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class ReadJsonData {


	@SuppressWarnings({ "deprecation", "unchecked" })
	public static String[]  getData() {
		JsonParser parser = new JsonParser();
		ArrayList<String> listdata = new ArrayList<String>();
		
		
		
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\elcot\\git\\repository\\MyTasks\\src\\POM_task\\Utils\\Json_data.json"));
			JsonObject jArray = (JsonObject) obj;
			
			//JsonArray jArray = (JsonArray) obj; 
			System.out.println("size : "+jArray.size());
			/*
			if (jArray != null) { 
			   for (String i=0;i<jArray.size();i++){ 
			    listdata.add(jArray.get(i).getAsString());
			   } 
			} 
			*/
			System.out.println(jArray.get("searchItem"));
			System.out.println(jArray.get("selectItem"));
			listdata.add(jArray.get("searchItem").getAsString());
			listdata.add(jArray.get("selectItem").getAsString());
			
		}  
		catch (Exception e) {
			e.printStackTrace();
		 
		}
		
		System.out.println(listdata);
		return (String[]) listdata.toArray();
	}
	
public static void main(String[] args) {
	ReadJsonData.getData();
}
}
