package pos.backup;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

import pos.item.InventoryItem;
import pos.item.ReturnItem;

/**
 * This class reads in data to the server from a backup CSV file
 * @deprecated No longer necessary with a SQL server
 */
public class BackupReader {
	BufferedReader in;
	String filename;
	
	/**
	 * Creates a BackupReader object linked to a server CSV file
	 * 
	 * @param filename path to the CSV file, including the CSV file and extension
	 */
	public BackupReader(String filename){
		this.filename = filename;
	}
	
	public ArrayList<InventoryItem> readInventoryFromFile(){
		try{
			 in = new BufferedReader(new FileReader(filename)); 
		 } catch (Exception e){
			 e.printStackTrace();
		 }
		ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
		boolean isReading = false;
		
		try{
			String s = in.readLine();
			while(s != null){
				if(s.equals("INVENTORY")){
					isReading = true;
					s = in.readLine();
				}
				if(s.equals("RETURN")){
					isReading = false;
					s = in.readLine();
				}
				if(s.startsWith("SKU")){
					s = in.readLine();
				}
				if(isReading && !s.isEmpty()){
					s = s.replaceAll("\"", " ");
					StringTokenizer st = new StringTokenizer(s, ",");
					InventoryItem i = new InventoryItem();
					i.SKU = Integer.parseInt(st.nextToken().trim());
					i.UPC = st.nextToken().trim();
					i.name = st.nextToken().trim();
					i.brand = st.nextToken().trim();
					i.color = st.nextToken().trim();
					i.size = st.nextToken().trim();
					i.type = st.nextToken().trim();
					i.gender = st.nextToken().trim();
					i.client = st.nextToken().trim();
					i.date = st.nextToken().trim();
					i.notes = st.nextToken().trim();
					i.price = st.nextToken().trim();
					i.cost = st.nextToken().trim();
					i.quantity = Integer.parseInt(st.nextToken().trim());
					inventoryItems.add(i);
				}
				s = in.readLine();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return inventoryItems;
	}
	
	public ArrayList<ReturnItem> readReturnFromFile(){
		try{
			 in = new BufferedReader(new FileReader(filename)); 
		 } catch (Exception e){
			 e.printStackTrace();
		 }
		ArrayList<ReturnItem> returnItems = new ArrayList<ReturnItem>();
		boolean isReading = false;
		
		try{
			String s = in.readLine();
			while(s != null){
				if(s.equals("RETURN")){
					isReading = true;
					s = in.readLine();
				}
				if(s.equals("INVENTORY")){
					isReading = false;
					s = in.readLine();
				}
				if(s.startsWith("SKU")){
					s = in.readLine();
				}
				if(isReading && !s.isEmpty()){
					s = s.replaceAll("\"", " ");
					StringTokenizer st = new StringTokenizer(s, ",");
					ReturnItem i = new ReturnItem();
					i.SKU = Integer.parseInt(st.nextToken().trim());
					i.UPC = st.nextToken().trim();
					i.name = st.nextToken().trim();
					i.brand = st.nextToken().trim();
					i.color = st.nextToken().trim();
					i.size = st.nextToken().trim();
					i.type = st.nextToken().trim();
					i.gender = st.nextToken().trim();
					i.client = st.nextToken().trim();
					i.date = st.nextToken().trim();
					i.notes = st.nextToken().trim();
					i.price = st.nextToken().trim();
					i.cost = st.nextToken().trim();
					i.quantity = Integer.parseInt(st.nextToken().trim());
					i.status = st.nextToken().trim();
					returnItems.add(i);
				}
				s = in.readLine();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return returnItems;
	}
	
	public void close(){
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
