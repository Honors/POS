package pos.log;

import pos.item.InventoryItem;

public class LogInfoGenerator {

	public static String generateTransactionIncomingItemStatement(String upc, String name, int oldVal, int newVal){
		return "[+] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal;
	}
	
	public static String generateTransactionOutgoingItemStatement(String upc, String name, int oldVal, int newVal){
		return "[-] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal;
	}
	
	public static String generateTransactionReturnItemStatement(String upc, String name, int quantity, String status){
		return "[#] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + quantity + " " + status;
	}
	
	public static String generateReturnEditItemStatement(String upc, String name, int quantity, String oldStatus, String newStatus){
		return "[Return] (" + TimeStamp.simpleDateAndTime() + ") UPC: " + upc + ", Name: " + name + ", " + quantity + " " + oldStatus + "  ->  " + newStatus;
	}
	
	public static String generateElementNewStatement(String type, String element){
		return "[" + type + "] (" + TimeStamp.simpleDateAndTime() + ") New: " + element;
	}

	public static String generateElementEditStatement(String type, String oldElement, String newElement){
		return "[" + type + "] (" + TimeStamp.simpleDateAndTime() + ") Edit: " + oldElement + "  ->  " + newElement;
	}
	
	public static String generateElementDeleteStatement(String type, String delElement, String migElement){
		return "[" + type + "] (" + TimeStamp.simpleDateAndTime() + ") Delete: " + delElement + "  ->  " + migElement;
	}
	
	public static String generateInventoryNewItemStatement(InventoryItem newItem){
		return "[Inventory] ("  + TimeStamp.simpleDateAndTime() + ") New Item:\n" +
			   "    Name: " + newItem.name +"\n" +
			   "    UPC: " + newItem.UPC +"\n" +
			   "    Cost: " + newItem.cost +"\n" +
			   "    Price: " + newItem.price +"\n" +
			   "    Quantity: " + newItem.quantity +"\n" +
			   "    Brand: " + newItem.brand +"\n" +
			   "    Type: " + newItem.type +"\n" +
			   "    Color: " + newItem.color +"\n" +
			   "    Size: " + newItem.size +"\n" +
			   "    Gender: " + newItem.gender +"\n" +
			   "    Client: " + newItem.client;
	}
	
	public static String generateInventoryEditItemStatement(InventoryItem oldItem, InventoryItem newItem){
		String statement = "[Inventory] ("  + TimeStamp.simpleDateAndTime() + ") Edit Item:\n";
		statement += (!oldItem.name.equals(newItem.name)) ? "    *Name: " + oldItem.name +"  ->  " + newItem.name + "\n" : "    Name: " + oldItem.name +"\n";
		statement += "    UPC: " + oldItem.UPC +"\n";
		statement += (!oldItem.cost.equals(newItem.cost)) ? "    *Cost: " + oldItem.cost + "  ->  " + newItem.cost + "\n" : "";
		statement += (!oldItem.price.equals(newItem.price)) ?"    *Price: " + oldItem.price + "  ->  " + newItem.price + "\n" : "";
		statement += (!oldItem.brand.equals(newItem.brand)) ? "    *Brand: " + oldItem.brand +"  ->  " + newItem.brand + "\n" : "";
		statement += (!oldItem.type.equals(newItem.type)) ? "    *Type: " + oldItem.type +"  ->  " + newItem.type + "\n" : "";
		statement += (!oldItem.color.equals(newItem.color)) ? "    *Color: " + oldItem.color +"  ->  " + newItem.color + "\n" : "";
		statement += (!oldItem.size.equals(newItem.size)) ? "    *Size: " + oldItem.size +"  ->  " + newItem.size + "\n" : "";
		statement += (!oldItem.gender.equals(newItem.gender)) ? "    *Gender: " + oldItem.gender +"  ->  " + newItem.gender + "\n" : "";
		statement += (!oldItem.client.equals(newItem.client)) ? "    *Client: " + oldItem.client +"  ->  " + newItem.client + "\n" : "";
			  
	    return statement;
	}
	
}
