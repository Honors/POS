package pos.core;

public class Item {

	public final static int NEW_PRODUCT = 0;
	public final static int EDIT_PRODUCT = 1;
	public final static int VIEW_PRODUCT = 2;
	public final static int RETURN_PRODUCT = 3;
	
	public final static String STATUS_PENDING = "Pending";
	public final static String STATUS_TO_VENDER = "Return to Vender";
	public final static String STATUS_TO_INVENTORY = "Return to Inventory";
	
	public int SKU;
	public String UPC;
	public String name;
	public String brand;
	public String color;
	public String size;
	public String type;
	public String gender;
	public String client;
	public String date;
	public String notes;
	public String price;
	public String cost;
	public int quantity;

	
	public String toStringFormatted(){
		return null;
	}
	
	public String toStringInsert(){
		return null;
	}
	
	public String toSrtingCSV(){
		return null;
	}
	
	public String toStringUpdate(){
		return null;
	}

}
