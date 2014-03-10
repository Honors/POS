package pos.core;

import java.sql.*;
import java.util.ArrayList;

import pos.backup.BackupReader;
import pos.lib.Reference;
import pos.item.InventoryItem;
import pos.item.ReturnItem;

public class ServerManager {
	
	private boolean isConnected;
	private boolean isValidLogin;
	private boolean isAdmin;
	
	private String username;
	
	private String address;
	private String port;
	private String database;
	
	private Connection con;
	private Config properties;
	
	//create remove update delete count
	
	public ServerManager(String username, String password){
		properties = new Config();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			address = properties.getProperty(Reference.SERVER_ADDRESS);
			port = properties.getProperty(Reference.SQLDB_PORT);
			database = properties.getProperty(Reference.SQLDB_NAME);
			String host = "jdbc:mysql://" + address + ":" + port + "/" + database;
			con = DriverManager.getConnection(host, properties.getProperty(Reference.SQLDB_USERNAME), properties.getProperty(Reference.SQLDB_PASSWORD));
			System.out.println("CONNECTION SUCCESSFUL");
			con.setAutoCommit(false);
			isConnected = true;
			isAdmin = login(username, password);
		} catch (Exception e){
			System.out.println(e);
			e.printStackTrace();
			isConnected = false;
			isValidLogin = false;
			isAdmin = false;
		}
	}
	
	public boolean connected(){
		return isConnected;
	}
	
	public boolean login(String username, String password){
		this.username = username;
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM LOGIN WHERE username = '" + username + "'").executeQuery();
			if(r.next()){
				String pass = r.getString("password");
				if(pass.equals(password)){
					isValidLogin = true;
					return r.getBoolean("admin");
				} else {
					isValidLogin = false;
					return false;
				}
			} else {
				isValidLogin = false;
				return false;
			}
		} catch(Exception e){
			e.printStackTrace();
			isValidLogin = false;
			return false;
		}
	}
	
	public boolean validLogin(){
		return isValidLogin;
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String exec(String q){
		String s = "";
		try{
			con.prepareStatement(q).execute();
			con.commit();
			s += "SUCCESS";
		} catch (Exception e){
			System.out.println(e);
			s += e.toString();
		}
		return s;
	}
	
	public String getElement(int identifier){
		String element;
		switch(identifier){
		case Reference.BRAND:  element = "BRAND";
							   break;
		case Reference.COLOR:  element = "COLOR";
							   break;
		case Reference.GENDER: element = "GENDER";
						       break;
		case Reference.TYPE:   element = "TYPE";
							   break;
		case Reference.CLIENT: element = "CLIENT";
							   break;
		case Reference.SIZE:   element = "SIZE";
							   break;
		default:			   element = null;
						       break;
		}
		return element;
	}
	
	public String getItemElement(int identifier){
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
		// find criteria
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
		// update {SKU: i.SKU} i.toJSON()
		try{
			con.prepareStatement("UPDATE INVENTORY SET " + i.toStringUpdate() + " WHERE SKU=" + i.SKU).execute();
			con.commit();
			return "SUCCESS";
		} catch (Exception e){
			System.out.println(e);
			return "FAILED: SQL EXCEPTION:\n\n" + e;
		}
	}

	public String updateInventoryElement(int identifier, String oldElement, String newElement){
		// update {identifier: oldElement} {identifier: newElement}
		// update {identifier: {$ne: oldElement}} {identifier: identifier}
		try{
			String statement = "UPDATE INVENTORY SET " + getItemElement(identifier) + " = case when " + getElement(identifier) + " = "+ wrap(identifier, oldElement) +" then " + wrap(identifier, newElement) + " else " + getElement(identifier) + " end";
			con.prepareStatement(statement).execute();
			con.commit();
			return "SUCCESS";
		} catch (Exception e){
			
			return "FAILED. SQL EXCEPTION:\n" + e;
		}
	}
	
	public String insertInventoryItem(InventoryItem i){
		// insert i.toJSON()
		if (getInventoryItem(i.SKU).SKU != -1){
			return "FAILED: DUPLICATE SKU";
		}
		try{
			con.prepareStatement("INSERT INTO INVENTORY VALUES (" + i.toStringInsert() + ")").execute(); 
			con.commit();
		} catch (Exception e){
			System.out.println(e);
			return "FAILED. SQL EXCEPTION:\n" + e;
		}
		return "SUCCESS: ITEM INSERTED";
	}
	
	public String removeInventoryItem(InventoryItem i){
		// remove {SKU: i.SKU}
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
		// find {SKU: SKU}
		InventoryItem i = new InventoryItem();
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM INVENTORY WHERE SKU = " + SKU).executeQuery();
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
		// find -> count
		try{
			ResultSet r = con.prepareStatement("SELECT COUNT(SKU) AS SKUs From INVENTORY").executeQuery();
			con.commit();
			r.next();
			return r.getInt("SKUs");
		} catch (Exception e){
			System.out.println(e);
		}
		return -1;
	}
	
	public int getMaxInventorySKU(){
		// find {}
		int i = -1;
		try{
			ResultSet r = con.prepareStatement("SELECT * From INVENTORY").executeQuery();
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
		// find {}
		String s  = "";
		try{
			ResultSet r = con.prepareStatement("SELECT * From INVENTORY").executeQuery();
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
		// remove {}
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
	public ArrayList<ReturnItem> searchReturn(String s){
		// find criteria
		ArrayList<ReturnItem> results = new ArrayList<ReturnItem>();
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM " + database + ".RETURN WHERE " + s).executeQuery();
			con.commit();
			while (r.next()){
				ReturnItem i = new ReturnItem();
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
				i.status = r.getString("STATUS");
				results.add(i);
			}
		} catch (Exception e){
			System.out.println(e);
		}
		return results;
	}
	
	public String updateReturnItem(ReturnItem i){
		// update {SKU: i.SKU} i.toJSON
		try{
			con.prepareStatement("UPDATE " + database + ".RETURN SET " + i.toStringUpdate() + " WHERE SKU=" + i.SKU).execute();
			con.commit();
			return "SUCCESS";
		} catch (Exception e){
			System.out.println(e);
			return "FAILED: SQL EXCEPTION:\n\n" + e;
		}
	}
	
	public String insertReturnItem(ReturnItem i){
		if (getReturnItem(i.SKU).SKU != -1){
			return "FAILED: DUPLICATE SKU";
		}
		try{
			con.prepareStatement("INSERT INTO " + database + ".RETURN VALUES (" + i.toStringInsert() + ")").execute(); 
			con.commit();
		} catch (Exception e){
			System.out.println(e);
			return "FAILED. SQL EXCEPTION:\n" + e;
		}
		return "SUCCESS: ITEM INSERTED";
	}
	
	public ReturnItem getReturnItem(int SKU){
		ReturnItem i = new ReturnItem();
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM " + database + ".RETURN WHERE SKU = " + SKU).executeQuery();
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
			i.status = r.getString("STATUS");
			
		}	catch (Exception e){
			System.out.println("[No Duplicate] " + e);
			return new ReturnItem();
		}
		
		return i;
	}

	public int getNumberOfReturnItems(){
		
		return -1;
	}

	public int getMaxReturnSKU(){
		// find {}
		int i = -1;
		try{
			ResultSet r = con.prepareStatement("SELECT * From " + database + ".RETURN").executeQuery();
			con.commit();
			while (r.next()){
				i = r.getInt("SKU");
			}
		} catch(Exception e){
			System.out.println(e);
		}
		return i;
	}
	
	public void deleteAllReturn(){
		
	}
	
	public void deleteAllElements(int identifier){
		// remove {}
		try{
			con.prepareStatement("DELETE FROM " + getElement(identifier)).execute();
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	//DESCRIPTION ELEMENTS
	public void writeAllElements(int identifier, ArrayList<String> elements){
		deleteAllElements(identifier);
		for(int i = 0; i < elements.size(); i++){
			insertElement(identifier, i, elements.get(i));
		}
	}
	
	public String insertElement(int identifier, int index, String element){
		// insert {id: index, element: element}
		try{
			con.prepareStatement("INSERT INTO " + getElement(identifier) + " VALUES ( " + index + ", '" + element + "' )").execute(); 
			con.commit();
		} catch (Exception e){
			System.out.println(e);
			return "FAILED. SQL EXCEPTION:\n" + e;
		}
		return "SUCCESS: ELEMENT INSERTED";
	}
	
	public ArrayList<String> readElements(int identifier){
		// find {}
		ArrayList<String> elements = new ArrayList<String>();
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM " + getElement(identifier)).executeQuery();
			con.commit();
			while(r.next()){
				elements.add(r.getString("element"));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return elements;
	}
	
	//LOGINS
	
	public Object[][] getLoginData(){
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM LOGIN", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery();
			con.commit();
			r.last();
			int size = r.getRow();
			r.beforeFirst();
			
			Object[][] loginData = new Object[size][3];
		
			for(int collumn = 0; collumn < size; collumn++){
				r.next();
				loginData[collumn][0] = r.getString("username");
				loginData[collumn][1] = r.getString("password");
				loginData[collumn][2] = r.getBoolean("admin") ? Reference.ADMIN : Reference.USER;
			}
			return loginData;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String> getLoginUsernames(){
		try{
			ResultSet r = con.prepareStatement("SELECT * FROM LOGIN").executeQuery();
			con.commit();

			ArrayList<String> usernames = new ArrayList<String>();
		
			while(r.next())
				usernames.add(r.getString("username"));
			return usernames;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String insertLogin(String u, String p, boolean a){
		try{
			con.prepareStatement("INSERT INTO LOGIN VALUES ( '" + u + "', '" + p + "', " + a + ")").execute(); 
			con.commit();
		} catch (Exception e){
			System.out.println(e);
			return "FAILED. SQL EXCEPTION:\n" + e;
		}
		return "SUCCESS: LOGIN INSERTED";
	}
	
	public void deleteLogin(String delUser){
		try{
			con.prepareStatement("DELETE FROM LOGIN WHERE username='" + delUser + "'").execute();
			con.commit();
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void deleteAllLogins(){
		// remove {}
		try{
			con.prepareStatement("DELETE FROM LOGIN").execute();
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void writeAllLogins(String[][] logins){
		deleteAllLogins();
		System.out.println(logins.length);
		for(int i = 0; i < logins.length; i++){
			insertLogin(logins[i][0], logins[i][1], logins[i][2].equals(Reference.ADMIN));
		}
	}
}
