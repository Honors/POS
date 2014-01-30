package pos.log;

import pos.item.InventoryItem;

public class LogInfoGenerator {

	public static String generateTransactionIncomingItemStatement(String upc, String name, int oldVal, int newVal){
		return "[+] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateTransactionOutgoingItemStatement(String upc, String name, int oldVal, int newVal){
		return "[-] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateTransactionReturnItemStatement(String upc, String name, int quantity, String status){
		return "[*] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + quantity + " " + status + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateReturnEditItemStatement(String upc, String name, int quantity, String oldStatus, String newStatus){
		return "[Return] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + quantity + " " + oldStatus + "  ->  " + newStatus + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateElementNewStatement(String type, String element){
		return "[Element] (" + TimeStamp.simpleDateAndTime() + ") New " + type + ": " + element + System.lineSeparator() + System.lineSeparator();
	}

	public static String generateElementEditStatement(String type, String oldElement, String newElement){
		return "[Element] (" + TimeStamp.simpleDateAndTime() + ") Edit " + type + ": " + oldElement + "  ->  " + newElement + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateElementDeleteStatement(String type, String delElement, String migElement){
		return "[Element] (" + TimeStamp.simpleDateAndTime() + ") Delete " + type + ": " + delElement + ", migrated to " + migElement + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateInventoryNewItemStatement(InventoryItem newItem){
		return "[Inventory] ("  + TimeStamp.simpleDateAndTime() + ") New Item:" + System.lineSeparator() +
			   "    Name: " + newItem.name +"" + System.lineSeparator() +
			   "    UPC: " + newItem.UPC +"" + System.lineSeparator() +
			   "    Cost: " + newItem.cost +"" + System.lineSeparator() +
			   "    Price: " + newItem.price +"" + System.lineSeparator() +
			   "    Quantity: " + newItem.quantity +"" + System.lineSeparator() +
			   "    Brand: " + newItem.brand +"" + System.lineSeparator() +
			   "    Type: " + newItem.type +"" + System.lineSeparator() +
			   "    Color: " + newItem.color +"" + System.lineSeparator() +
			   "    Size: " + newItem.size +"" + System.lineSeparator() +
			   "    Gender: " + newItem.gender +"" + System.lineSeparator() +
			   "    Client: " + newItem.client + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateInventoryEditItemStatement(InventoryItem oldItem, InventoryItem newItem){
		String statement = "[Inventory] ("  + TimeStamp.simpleDateAndTime() + ") Edit Item:" + System.lineSeparator() + "";
		statement += (!oldItem.name.equals(newItem.name)) ? "    *Name: " + oldItem.name +"  ->  " + newItem.name + "" + System.lineSeparator() : "    Name: " + oldItem.name +"" + System.lineSeparator() + "";
		statement += "    UPC: " + oldItem.UPC +"" + System.lineSeparator() + "";
		statement += (!oldItem.cost.equals(newItem.cost)) ? "    *Cost: " + oldItem.cost + "  ->  " + newItem.cost + "" + System.lineSeparator() : "";
		statement += (!oldItem.price.equals(newItem.price)) ?"    *Price: " + oldItem.price + "  ->  " + newItem.price + "" + System.lineSeparator() : "";
		statement += (!oldItem.brand.equals(newItem.brand)) ? "    *Brand: " + oldItem.brand +"  ->  " + newItem.brand + "" + System.lineSeparator() : "";
		statement += (!oldItem.type.equals(newItem.type)) ? "    *Type: " + oldItem.type +"  ->  " + newItem.type + "" + System.lineSeparator() : "";
		statement += (!oldItem.color.equals(newItem.color)) ? "    *Color: " + oldItem.color +"  ->  " + newItem.color + "" + System.lineSeparator() : "";
		statement += (!oldItem.size.equals(newItem.size)) ? "    *Size: " + oldItem.size +"  ->  " + newItem.size + "" + System.lineSeparator() : "";
		statement += (!oldItem.gender.equals(newItem.gender)) ? "    *Gender: " + oldItem.gender +"  ->  " + newItem.gender + "" + System.lineSeparator() : "";
		statement += (!oldItem.client.equals(newItem.client)) ? "    *Client: " + oldItem.client +"  ->  " + newItem.client + "" + System.lineSeparator() : "";
		statement += System.lineSeparator() + System.lineSeparator();
	    return statement;
	}
	
	public static String generateServerCommandStatement(String command, String result){
		return "[Server] (" + TimeStamp.simpleDateAndTime() + ") Execute" + System.lineSeparator() + "    Command: " + command + "" + System.lineSeparator() + "    Result: " + result + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateServerBackupStatement(String filePath){
		return "[Backup] (" + TimeStamp.simpleDateAndTime() + ") Path: " + filePath + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateServerRestoreStatement(String filePath){
		return "[Restore] (" + TimeStamp.simpleDateAndTime() + ") Path: " + filePath + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateProgramStartStatement(){
		return "[POS] (" + TimeStamp.simpleDateAndTime() + ") Instance Began" + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateLabelStatement(String name, String upc, String filePath){
		return "[Label] (" + TimeStamp.simpleDateAndTime() + ") Name: " + name + " UPC: " + upc + " Path: " + filePath + System.lineSeparator() + System.lineSeparator();
	}
}
