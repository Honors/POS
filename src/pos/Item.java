package pos;


public class Item {
	
	public final static int NEW_PRODUCT = 0;
	public final static int EDIT_PRODUCT = 1;
	public final static int VIEW_PRODUCT = 2;
	public final static int RETURN_PRODUCT = 3;
	
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
	
	//TODO allow for return info
	public Item(){
		SKU = -1;
		name = "NEW PRODUCT";
		quantity = 1;
	}
	
	public Item(String u, Keys key){
		u = u.toUpperCase();
		SKU = -1;
		UPC = u;
		name = "NEW PRODUCT";
		quantity = 1;
		if (u.length() == 10){
			brand = key.getBrand(u.substring(0, 2));
			color = key.getColor(u.substring(2, 5));
			size = key.getSize(u.substring(5,7));
			type = key.getType(u.substring(7, 9));
			gender = key.getGender(u.substring(9));
			name = brand + " " + color + " " + type;
		}
	}
	
	public Item(int _SKU, String _UPC, String _name, String _brand, String _color, String _size, String _type, String _gender, String _client, String _date, String _notes, String _price, String _cost, int _quantity){
		SKU = _SKU;
		UPC = _UPC;
		name = _name;
		brand = _brand;
		color = _color;
		size = _size;
		type = _type;
		gender = _gender;
		client = _client;
		date = _date;
		notes = _notes;
		price = _price;
		cost = _cost;
		quantity = _quantity;
	}
	
	public String toStringFormatted(){
		String s = "";
		//s += "SKU: " + SKU;
		s += "UPC: " + UPC;
		s += "\nName: " + name;
		s += "\nPrice: " + price;
		s += "\nCost: " + cost;
		s += "\nQuantity: " + quantity;
		return s;
	}
	
	public String toStringInsert(){
		String s = "";
		s += SKU;
		s += ", '" + UPC;
		s += "', '" + name;
		s += "', '" + brand;
		s += "', '" + color;
		s += "', '" + size;
		s += "', '" + type;
		s += "', '" + gender;
		s += "', '" + client;
		s += "', '" + date;
		s += "', '" + notes;
		s += "', '" + price;
		s += "', '" + cost;
		s += "', " + quantity;
		return s;
	}
	
	public String toSrtingCSV(){
		String s = "\"";
		s += SKU;
		s += "\",\""  + UPC;
		s += "\",\""  + name;
		s += "\",\""  + brand;
		s += "\",\""  + color;
		s += "\",\""  + size;
		s += "\",\""  + type;
		s += "\",\""  + gender;
		s += "\",\""  + client;
		s += "\",\""  + date;
		s += "\",\"" + notes;
		s += "\",\""  + price;
		s += "\",\""  + cost;
		s += "\",\""  + quantity;
		s += "\"";
		return s;
	}
	
	public String toStringUpdate(){
		String s = "SKU=";
		s += SKU;
		s += ", UPC='" + UPC;
		s += "', NAME='" + name;
		s += "', BRAND='" + brand;
		s += "', COLOR='" + color;
		s += "', SIZE='" + size;
		s += "', TYPE='" + type;
		s += "', GENDER='" + gender;
		s += "', CLIENT='" + client;
		s += "', DATE='" + date;
		s += "', NOTES='" + notes;
		s += "', PRICE='" + price;
		s += "', COST='" + cost;
		s += "', QUANTITY=" + quantity;
		return s;
	}
}
