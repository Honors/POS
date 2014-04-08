package pos.log;

import pos.core.UpdateableContent;
import pos.core.UpdateableContentController;
import pos.item.InventoryItem;
import pos.lib.ReadableLoggerLabels;
import pos.lib.Reference;

public class LogInfoGenerator extends JsonTool {

	public static void postUpdate(String label, String username, String time, String content){
		final String item = (new JSON(
				$("label", label), 
				$("username", username),
				$("time", time), 
				$("content", content))).toString();
		
		(new Thread(new Runnable() {
			public void run() {
				Fetcher.update(Reference.READABLE_LOG_IDENTIFIER, item);
				UpdateableContentController.postUpdate(UpdateableContent.READABLE_LOG_UPDATED, item);
			}
		})).start();
		
	}
	
	public static String getContentChangeIdentifier(String identifier){
		if(identifier.equals("Brand")){
			return UpdateableContent.BRAND_UPDATED;
		} else if(identifier.equals("Type")){
			return UpdateableContent.TYPE_UPDATED;
		} else if(identifier.equals("Color")){
			return UpdateableContent.COLOR_UPDATED;
		} else if(identifier.equals("Size")){
			return UpdateableContent.SIZE_UPDATED;
		} else if(identifier.equals("Gender")){
			return UpdateableContent.GENDER_UPDATED;
		} else if(identifier.equals("Client")){
			return UpdateableContent.CLIENT_UPDATED;
		} else {
			return "";
		}
	}
	
	private static String format(String label, String username, String time){
		return "[" + label + "] (" + username + " " + time + ") ";
	}
	
	public static String generateTransactionIncomingItemStatement(String username, String upc, String name, int oldVal, int newVal){
		String label = ReadableLoggerLabels.INCOMING_TRANSACTION;
		String time = TimeStamp.simpleDateAndTime();
		String content = "UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(UpdateableContent.INVENTORY_UPDATED, upc);
		return format(label,username,time) + content;
	}
	
	public static String generateTransactionOutgoingItemStatement(String username, String upc, String name, int oldVal, int newVal){
		String label = ReadableLoggerLabels.OUTGOING_TRANSACTION;
		String time = TimeStamp.simpleDateAndTime();
		String content =  "UPC: " + upc + ", Name: " + name + ", " + oldVal + "  ->  " + newVal;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(UpdateableContent.INVENTORY_UPDATED, upc);
		return format(label,username,time) + content;
	}
	
	public static String generateTransactionReturnItemStatement(String username, String upc, String name, int quantity, String status){
		String label = ReadableLoggerLabels.RETURN_TRANSACTION;
		String time = TimeStamp.simpleDateAndTime();
		String content = "UPC: " + upc + ", Name: " + name + ", " + quantity + " " + status;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(UpdateableContent.RETURN_UPDATED, upc);
		return format(label,username,time) + content;
	}
	
	public static String generateReturnEditItemStatement(String username, String upc, String name, int quantity, String oldStatus, String newStatus){
		String label = ReadableLoggerLabels.RETURN_ITEM;
		String time = TimeStamp.simpleDateAndTime();
		String content = "UPC: " + upc + ", Name: " + name + ", " + quantity + " " + oldStatus + "  ->  " + newStatus;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(UpdateableContent.INVENTORY_UPDATED, upc);
		return format(label,username,time) + content;
	}
	
	public static String generateElementNewStatement(String username, String type, String element){
		String label = ReadableLoggerLabels.ELEMENT;
		String time = TimeStamp.simpleDateAndTime();
		String content = "New " + type + ": " + element;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(getContentChangeIdentifier(type), element);
		return format(label,username,time) + content;
	}

	public static String generateElementEditStatement(String username, String type, String oldElement, String newElement){
		String label = ReadableLoggerLabels.ELEMENT;
		String time = TimeStamp.simpleDateAndTime();
		String content = "Edit " + type + ": " + oldElement + "  ->  " + newElement;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(getContentChangeIdentifier(type), oldElement);
		return format(label,username,time) + content;
	}
	
	public static String generateElementDeleteStatement(String username, String type, String delElement, String migElement){
		String label = ReadableLoggerLabels.ELEMENT;
		String time = TimeStamp.simpleDateAndTime();
		String content = "Delete " + type + ": " + delElement + ", migrated to " + migElement;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(getContentChangeIdentifier(type), delElement);
		return format(label,username,time) + content;
	}
	
	public static String generateInventoryNewItemStatement(String username, InventoryItem newItem){
		String label = ReadableLoggerLabels.INVENTORY_ITEM;
		String time = TimeStamp.simpleDateAndTime();
		String content = "New Item:" + System.lineSeparator() +
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
				   "    Client: " + newItem.client;
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(UpdateableContent.INVENTORY_UPDATED, newItem.UPC);
		return format(label,username,time) + content;
	}
	
	public static String generateInventoryEditItemStatement(String username, InventoryItem oldItem, InventoryItem newItem){
		String label = ReadableLoggerLabels.INVENTORY_ITEM;
		String time = TimeStamp.simpleDateAndTime();
		String content ="Edit Item:" + System.lineSeparator() + "";
		content += (!oldItem.name.equals(newItem.name)) ? "    *Name: " + oldItem.name +"  ->  " + newItem.name + "" + System.lineSeparator() : "    Name: " + oldItem.name +"" + System.lineSeparator() + "";
		content += "    UPC: " + oldItem.UPC +"" + System.lineSeparator() + "";
		content += (!oldItem.cost.equals(newItem.cost)) ? "    *Cost: " + oldItem.cost + "  ->  " + newItem.cost + "" + System.lineSeparator() : "";
		content += (!oldItem.price.equals(newItem.price)) ?"    *Price: " + oldItem.price + "  ->  " + newItem.price + "" + System.lineSeparator() : "";
		content += (!oldItem.brand.equals(newItem.brand)) ? "    *Brand: " + oldItem.brand +"  ->  " + newItem.brand + "" + System.lineSeparator() : "";
		content += (!oldItem.type.equals(newItem.type)) ? "    *Type: " + oldItem.type +"  ->  " + newItem.type + "" + System.lineSeparator() : "";
		content += (!oldItem.color.equals(newItem.color)) ? "    *Color: " + oldItem.color +"  ->  " + newItem.color + "" + System.lineSeparator() : "";
		content += (!oldItem.size.equals(newItem.size)) ? "    *Size: " + oldItem.size +"  ->  " + newItem.size + "" + System.lineSeparator() : "";
		content += (!oldItem.gender.equals(newItem.gender)) ? "    *Gender: " + oldItem.gender +"  ->  " + newItem.gender + "" + System.lineSeparator() : "";
		content += (!oldItem.client.equals(newItem.client)) ? "    *Client: " + oldItem.client +"  ->  " + newItem.client + "" + System.lineSeparator() : "";
		postUpdate(label, username, time, content);
		UpdateableContentController.postUpdate(UpdateableContent.INVENTORY_UPDATED, oldItem.UPC);
		return format(label,username,time) + content;
	}
	
	public static String generateServerCommandStatement(String username, String command, String result){
		String label = ReadableLoggerLabels.SERVER;
		String time = TimeStamp.simpleDateAndTime();
		String content =  "Execute" + System.lineSeparator() + 
				          "    Command: " + command + System.lineSeparator() + 
				          "    Result: " + result;
		postUpdate(label, username, time, content);
		return format(label,username,time) + content; 
	}
	
	public static String generateServerBackupStatement(String username, String filePath){
		String label = ReadableLoggerLabels.BACKUP;
		String time = TimeStamp.simpleDateAndTime();
		String content = "Path: " + filePath;
		postUpdate(label, username, time, content);
		return format(label,username,time) + content;
	}
	
	public static String generateServerRestoreStatement(String username, String filePath){
		String label = ReadableLoggerLabels.RESTORE;
		String time = TimeStamp.simpleDateAndTime();
		String content = "Path: " + filePath;
		postUpdate(label, username, time, content);
		return format(label,username,time) + content;
	}
	
	public static String generateProgramStartStatement(String username){
		String label = ReadableLoggerLabels.POS;
		String time = TimeStamp.simpleDateAndTime();
		String content = "Instance Began";
		postUpdate(label, username, time, content);
		return format(label,username,time) + content;
	}
	
	public static String generateLabelStatement(String username, String name, String upc, String filePath){
		String label = ReadableLoggerLabels.UPC_LABEL;
		String time = TimeStamp.simpleDateAndTime();
		String content = "Name: " + name + " UPC: " + upc + " Path: " + filePath;
		postUpdate(label, username, time, content);
		return format(label,username,time) + content;
	}
}
