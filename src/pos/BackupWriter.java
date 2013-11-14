package pos;

import java.io.*;
import java.util.ArrayList;


public class BackupWriter {
	PrintWriter out;
	BufferedReader in;
	OutputWindow parent;
	InventoryManager inventory;
	
	public BackupWriter(String filename, OutputWindow w, InventoryManager m){
		parent = w;
		inventory = m;
		try{
			out  = new PrintWriter(new FileWriter(filename)); 
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void dumpToCSV(){
		dumpToCSV(inventory.search("SKU > -1"));
	}
	
	public void dumpToCSV(ArrayList<Item> i){
		out.println("SKU,UPC,NAME,BRAND,COLOR,SIZE,TYPE,GENDER,CLIENT,DATE,NOTES,PRICE,COST,QUANTITY");
		while (!i.isEmpty()){
			out.println(i.remove(0).toSrtingCSV());
		}
		parent.writeToOutput("\nSUCCESS\n");
	}
	
	public void close(){
		out.close();
	}
}
