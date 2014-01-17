package pos.lib;

import java.util.ArrayList;
import java.util.Arrays;

public class Reference {
	
	public static final int SKU = 0;
	public static final int UPC = 1;
	public static final int NAME = 2;
	public static final int BRAND = 3;
	public static final int COLOR = 4;
	public static final int SIZE = 5;
	public static final int TYPE = 6;
	public static final int GENDER = 7;
	public static final int CLIENT = 8;
	public static final int DATE = 9;
	public static final int NOTES = 10;
	public static final int PRICE = 11;
	public static final int COST = 12;
	public static final int QUANTITY = 13;
	public static final int STATUS = 14;
	
	public final static int NEW_PRODUCT = 15;
	public final static int EDIT_PRODUCT = 16;
	public final static int VIEW_PRODUCT = 17;
	public final static int RETURN_PRODUCT = 18;
	
	public final static String STATUS_PENDING = "Pending";
	public final static String STATUS_TO_VENDER = "Return to Vender";
	public final static String STATUS_TO_INVENTORY = "Return to Inventory";
	
	public final static ArrayList<String> STATUSES = new ArrayList<String>(Arrays.asList(STATUS_PENDING, STATUS_TO_VENDER, STATUS_TO_INVENTORY));
	
	public final static String REPORT_INVENTORY_ITEM = "Inventory Item Report";
	public final static String REPORT_INVENTORY_STOCK = "Inventory Stock Status Report";
	
	public final static ArrayList<String> REPORT_TYPES = new ArrayList<String>(Arrays.asList(REPORT_INVENTORY_STOCK, REPORT_INVENTORY_ITEM));
	
	
}
