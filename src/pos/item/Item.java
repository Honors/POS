package pos.item;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {
	
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

	public String removeLineBreaks(String inputText){
		Pattern p;
		Matcher m;
		p = Pattern.compile("\n");
		m = p.matcher(inputText);
		String str = m.replaceAll("<br>");
		return str;
	}
}
