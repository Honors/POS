package pos;

import java.sql.*;
import java.util.ArrayList;

public class InventoryManager {
	
	private Connection con;
	
	public InventoryManager(String host, String username, String password){
		try{
			con = DriverManager.getConnection(host, username, password);
			System.out.println("CONNECTION SUCCESSFUL");
			con.setAutoCommit(false);
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	public ArrayList<Item> searchInventory(String s){
		ArrayList<Item> results = new ArrayList<Item>();
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM INVENTORY WHERE " + s).executeQuery();
			con.commit();
			while (r.next()){
				Item i = new Item();
				i.SKU = r.getInt("SKU");
				i.UPC = r.getString("UPC");
				i.name = r.getString("NAME");
				i.brand = r.getString("BRAND");
				i.color = r.getString("COLOR");
				i.size = r.getString("SIZE");
				i.type = r.getString("TYPE");
				i.gender = r.getString("GENDER");
				i.client = r.getString("CLIENT");
				i.date = r.getString("DATE");
				i.notes = r.getString("NOTES");
				i.price = r.getString("PRICE");
				i.cost = r.getString("COST");
				i.quantity = r.getInt("QUANTITY");
				results.add(i);
			}
		} catch (Exception e){
			System.out.println(e);
		}
		return results;
	}
	
	public String exec(String q){
		String s = "";
		try{
			con.prepareStatement(q).execute();
			con.commit();
			s += q + " :: SUCCEEDED";
		} catch (Exception e){
			System.out.println(e);
			s += e.toString();
		}
		return s;
	}
	
	public String updateInventoryItem(Item i){
		try{
			con.prepareStatement("UPDATE Inventory SET " + i.toStringUpdate() + " WHERE SKU=" + i.SKU).execute();
			con.commit();
			return "SUCCESS";
		} catch (Exception e){
			System.out.println(e);
			return "FAILED: SQL EXCEPTION:\n\n" + e;
		}
	}
	////////////////////////////////////////////////////////////////
	public String insertInventoryItem(Item i){
		if (getInventoryItem(i.SKU).SKU != -1){
			return "FAILED: DUPLICATE SKU";
		}
		try{
			con.prepareStatement("INSERT INTO Inventory VALUES (" + i.toStringInsert() + ")").execute(); 
			con.commit();
		} catch (Exception e){
			System.out.println(e);
			return "FAILED. SQL EXCEPTION:\n" + e;
		}
		return "SUCCESS: ITEM INSERTED";
	}
	
	public String removeInventoryItem(Item i){
		try{
			con.prepareStatement("DELETE FROM INVENTORY WHERE SKU=" + i.SKU).execute();
			con.commit();
		} catch (Exception e){
			System.out.println(e);
				return "FAILED. SQL EXCEPTION:\n" + e;
		}
		return "SUCCESS: ITEM REMOVED";
	}
	
	public Item getInventoryItem(int SKU){
		//java.sql.SQLException: Invalid cursor state - no current row.
		Item i = new Item();
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM Inventory WHERE SKU = " + SKU).executeQuery();
			con.commit();
			r.next();
			i.SKU = r.getInt("SKU");
			i.UPC = r.getString("UPC");
			i.name = r.getString("NAME");
			i.brand = r.getString("BRAND");
			i.color = r.getString("COLOR");
			i.size = r.getString("SIZE");
			i.type = r.getString("TYPE");
			i.gender = r.getString("GENDER");
			i.client = r.getString("CLIENT");
			i.date = r.getString("DATE");
			i.notes = r.getString("NOTES");
			i.price = r.getString("PRICE");
			i.quantity = r.getInt("QUANTITY");
			
		}	catch (Exception e){
			System.out.println("[No Duplicate] " + e);
			return new Item();
		}
		
		return i;
	}
	
	public int getNumberOfInventoryItems(){
		try{
			ResultSet r = con.prepareStatement("SELECT COUNT(SKU) AS SKUs From Inventory").executeQuery();
			con.commit();
			r.next();
			return r.getInt("SKUs");
		} catch (Exception e){
			System.out.println(e);
		}
		return -1;
	}
	
	public int getMaxInventorySKU(){
		int i = -1;
		try{
			ResultSet r = con.prepareStatement("SELECT * From Inventory").executeQuery();
			con.commit();
			while (r.next()){
				i = r.getInt("SKU");
			}
		} catch(Exception e){
			System.out.println(e);
		}
		return i;
	}
	
	public String dumpAllInventoryFormatted(){
		String s  = "";
		try{
			ResultSet r = con.prepareStatement("SELECT * From Inventory").executeQuery();
			con.commit();
			while (r.next()){
				Item i = new Item();
				i.SKU = r.getInt("SKU");
				i.UPC = r.getString("UPC");
				i.name = r.getString("NAME");
				i.brand = r.getString("BRAND");
				i.color = r.getString("COLOR");
				i.size = r.getString("SIZE");
				i.type = r.getString("TYPE");
				i.gender = r.getString("GENDER");
				i.client = r.getString("CLIENT");
				i.date = r.getString("DATE");
				i.notes = r.getString("NOTES");
				i.price = r.getString("PRICE");
				i.cost = r.getString("COST");
				i.quantity = r.getInt("QUANTITY");
				s += i.toStringFormatted() + "\n";
			}
		} catch(Exception e){
			System.out.println(e);
		}
		
		return s;
	}
	
	public void restoreInventoryFromBackup(String filename){
		BackupReader backup = new BackupReader(filename);
		ArrayList<Item> i = backup.readFromLog();
		deleteAllInventory();
		while (!i.isEmpty()){
			insertInventoryItem(i.remove(0));
		} 
		
	}
	
	public void deleteAllInventory(){
		try{
			con.prepareStatement("DELETE FROM INVENTORY").execute();
		} catch(Exception e){
			System.out.println(e);
		}
	}

	
}
