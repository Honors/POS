package pos.core;

import java.sql.*;
import java.util.ArrayList;

import pos.backup.BackupReader;
import pos.model.InventoryItem;

public class ServerManager {
	
	public static final int BRAND = 0;
	
	private Connection con;
	
	public ServerManager(String host, String username, String password){
		try{
			con = DriverManager.getConnection(host, username, password);
			System.out.println("CONNECTION SUCCESSFUL");
			con.setAutoCommit(false);
		} catch (Exception e){
			System.out.println(e);
		}
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
	
	public String getElement(int identifier){
		String element;
		switch(identifier){
		case Reference.SKU:      element = "SKU";
							     break;
		case Reference.UPC:      element = "UPC";
							     break;
		case Reference.NAME:     element = "NAME";
							     break;
		case Reference.BRAND:    element = "BRAND";
							     break;
		case Reference.COLOR:    element = "COLOR";
							     break;
		case Reference.SIZE:     element = "SIZE";
							     break;
		case Reference.TYPE:     element = "TYPE";
							     break;
		case Reference.GENDER:   element = "GENDER";
							     break;
		case Reference.CLIENT:   element = "CLIENT";
							     break;
		case Reference.DATE:     element = "DATE";
							     break;
		case Reference.NOTES:    element = "NOTES";
							     break;
		case Reference.PRICE:    element = "PRICE";
								 break;
		case Reference.COST:     element = "COST";
								 break;
		case Reference.QUANTITY: element = "QUANTITY";
								 break;
		case Reference.STATUS:   element = "STATUS";
								 break;
		default:				 element = null;
								 break;
		}
		return element;
	}
	
	public String wrap(int identifier, String element){
		return (identifier == Reference.SKU || identifier == Reference.QUANTITY) ? element : "'" + element + "'";
	}
	
	//INVENTORY MANAGEMENT METHODS
	public ArrayList<InventoryItem> searchInventory(String s){
		ArrayList<InventoryItem> results = new ArrayList<InventoryItem>();
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM INVENTORY WHERE " + s).executeQuery();
			con.commit();
			while (r.next()){
				InventoryItem i = new InventoryItem();
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
	
	public String updateInventoryItem(InventoryItem i){
		try{
			con.prepareStatement("UPDATE Inventory SET " + i.toStringUpdate() + " WHERE SKU=" + i.SKU).execute();
			con.commit();
			return "SUCCESS";
		} catch (Exception e){
			System.out.println(e);
			return "FAILED: SQL EXCEPTION:\n\n" + e;
		}
	}

	public void updateInventoryElement(int identifier, String oldElement, String newElement){
		try{
			String statement = "UPDATE Inventory SET " + getElement(identifier) + " = case when " + getElement(identifier) + " = "+ wrap(identifier, oldElement) +" then " + wrap(identifier, newElement) + " else " + getElement(identifier) + " end";
			System.out.println(statement);
			con.prepareStatement(statement).execute();
			con.commit();
			System.out.println("success");
		} catch (Exception e){
			
			System.out.println("FAILED. SQL EXCEPTION:\n" + e);
		}
	}
	
	public String insertInventoryItem(InventoryItem i){
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
	
	public String removeInventoryItem(InventoryItem i){
		try{
			con.prepareStatement("DELETE FROM INVENTORY WHERE SKU=" + i.SKU).execute();
			con.commit();
		} catch (Exception e){
			System.out.println(e);
				return "FAILED. SQL EXCEPTION:\n" + e;
		}
		return "SUCCESS: ITEM REMOVED";
	}
	
	public InventoryItem getInventoryItem(int SKU){
		//java.sql.SQLException: Invalid cursor state - no current row.
		InventoryItem i = new InventoryItem();
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
			return new InventoryItem();
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
				InventoryItem i = new InventoryItem();
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
		ArrayList<InventoryItem> i = backup.readFromLog();
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

	public String generateInventoryUPC(){
		String upc = String.valueOf(getMaxInventorySKU() + 1);
		int initialLength = upc.length();
		for(int i = initialLength; i < 10; i++){
			upc += String.valueOf(0);
		}
		return upc;
	}
	
	//RETURN MANAGEMENT METHODS
	public ArrayList<InventoryItem> searchReturn(String s){
		
		return null;
	}
	
	public String updateReturnItem(InventoryItem i){
		
		return null;
	}
	
	public String insertReturnItem(InventoryItem i){
		
		return null;
	}
	
	public String removeReturnItem(InventoryItem i){
		
		return null;
	}
	
	public InventoryItem getReturnItem(int SKU){
		
		return null;
	}

	public int getNumberOfReturnItems(){
		
		return -1;
	}

	public int getMaxReturnSKU(){
		
		return -1;
	}
	
	public String dumpAllReturnFormatted(){
		
		return null;
	}
	
	public void restoreReturnFromBackup(String filename){
		
	}
	
	public void deleteAllReturn(){
		
	}
}
