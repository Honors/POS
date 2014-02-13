package pos.log;

import pos.item.InventoryItem;

public class LogInfoGenerator {

	private static String begin(String identifier, String username){
		return "[" + identifier + "] (" + username + ") (" + TimeStamp.simpleDateAndTime() + ")";
	}
	
	public static String generateTransactionIncomingItemStatement(String username, String upc, String name, int oldVal, int newVal){
		return begin("+",username) + " UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateTransactionOutgoingItemStatement(String username, String upc, String name, int oldVal, int newVal){
		return begin("-",username) + " UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateTransactionReturnItemStatement(String username, String upc, String name, int quantity, String status){
		return begin("*",username) + " UPC: " + upc + ", Name: " + name + ", " + quantity + " " + status + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateReturnEditItemStatement(String username, String upc, String name, int quantity, String oldStatus, String newStatus){
		return begin("Return",username) + " UPC: " + upc + ", Name: " + name + ", " + quantity + " " + oldStatus + "  ->  " + newStatus + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateElementNewStatement(String username, String type, String element){
		return begin("Element",username) + " New " + type + ": " + element + System.lineSeparator() + System.lineSeparator();
	}

	public static String generateElementEditStatement(String username, String type, String oldElement, String newElement){
		return begin("Element",username) + " Edit " + type + ": " + oldElement + "  ->  " + newElement + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateElementDeleteStatement(String username, String type, String delElement, String migElement){
		return begin("Element",username) + " Delete " + type + ": " + delElement + ", migrated to " + migElement + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateInventoryNewItemStatement(String username, InventoryItem newItem){
		return begin("Inventory",username) + " New Item:" + System.lineSeparator() +
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
	
	public static String generateInventoryEditItemStatement(String username, InventoryItem oldItem, InventoryItem newItem){
		String statement = begin("Inventory",username) + " Edit Item:" + System.lineSeparator() + "";
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
	
	public static String generateServerCommandStatement(String username, String command, String result){
		return begin("Server",username) + " Execute" + System.lineSeparator() + "    Command: " + command + "" + System.lineSeparator() + "    Result: " + result + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateServerBackupStatement(String username, String filePath){
		return begin("Backup",username) + " Path: " + filePath + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateServerRestoreStatement(String username, String filePath){
		return begin("Restore",username) + " Path: " + filePath + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateProgramStartStatement(String username){
		return begin("POS",username) + " Instance Began" + System.lineSeparator() + System.lineSeparator();
	}
	
	public static String generateLabelStatement(String username, String name, String upc, String filePath){
		return begin("Label",username) + " Name: " + name + " UPC: " + upc + " Path: " + filePath + System.lineSeparator() + System.lineSeparator();
	}
}
