package pos.item;

import pos.lib.Reference;


public class ReturnItem extends Item{
	
	public String status;
	
	//TODO allow for return info
	public ReturnItem(){
		SKU = -1;
		name = "NEW PRODUCT";
		quantity = 1;
		status = Reference.STATUS_PENDING;
	}
	
	public ReturnItem(int _SKU, String _UPC, String _name, String _brand, String _color, String _size, String _type, String _gender, String _client, String _date, String _notes, String _price, String _cost, int _quantity, String _status){
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
		status = _status;
	}
	
	public ReturnItem(InventoryItem item){
		SKU = item.SKU;
		UPC = item.UPC;
		name = item.name;
		brand = item.brand;
		color = item.color;
		size = item.size;
		type = item.type;
		gender = item.gender;
		client = item.client;
		date = item.date;
		notes = item.notes;
		price = item.price;
		cost = item.cost;
		quantity = item.quantity;
		status = Reference.STATUS_PENDING;
	}
	
	public ReturnItem(Item item){
		SKU = item.SKU;
		UPC = item.UPC;
		name = item.name;
		brand = item.brand;
		color = item.color;
		size = item.size;
		type = item.type;
		gender = item.gender;
		client = item.client;
		date = item.date;
		notes = item.notes;
		price = item.price;
		cost = item.cost;
		quantity = item.quantity;
		status = Reference.STATUS_PENDING;
	}
	
	public ReturnItem(InventoryItem item, String _status){
		SKU = item.SKU;
		UPC = item.UPC;
		name = item.name;
		brand = item.brand;
		color = item.color;
		size = item.size;
		type = item.type;
		gender = item.gender;
		client = item.client;
		date = item.date;
		notes = item.notes;
		price = item.price;
		cost = item.cost;
		quantity = item.quantity;
		status = _status;
	}
	
	public ReturnItem(Item item, String _status){
		SKU = item.SKU;
		UPC = item.UPC;
		name = item.name;
		brand = item.brand;
		color = item.color;
		size = item.size;
		type = item.type;
		gender = item.gender;
		client = item.client;
		date = item.date;
		notes = item.notes;
		price = item.price;
		cost = item.cost;
		quantity = item.quantity;
		status = _status;
	}
	
	@Override
	public String toStringFormatted(){
		String s = "";
		//s += "SKU: " + SKU;
		s += "UPC: " + UPC;
		s += "\nName: " + name;
		s += "\nPrice: " + price;
		s += "\nCost: " + cost;
		s += "\nQuantity: " + quantity;
		s += "\nStatus: " + status;
		return s;
	}
	
	@Override
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
		s += ", '" + status + "'";
		return s;
	}
	
	@Override
	public String toSrtingCSV(){
		String s = "\"";
		s += SKU;
		s += "\",\"" + UPC;
		s += "\",\"" + name;
		s += "\",\"" + brand;
		s += "\",\"" + color;
		s += "\",\"" + size;
		s += "\",\"" + type;
		s += "\",\"" + gender;
		s += "\",\"" + client;
		s += "\",\"" + date;
		s += "\",\"" + notes;
		s += "\",\"" + price;
		s += "\",\"" + cost;
		s += "\",\"" + quantity;
		s += "\",\"" + status + "\"";
		return s;
	}
	
	@Override
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
		s += ", STATUS='" + status + "'";
		return s;
	}
}
