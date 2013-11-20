package pos.backup;

import java.io.*;
import java.util.ArrayList;

import pos.core.ServerManager;
import pos.model.InventoryItem;
import pos.core.OutputWindow;


public class BackupWriter {
	PrintWriter out;
	BufferedReader in;
	OutputWindow parent;
	ServerManager inventory;
	
	public BackupWriter(String filename, OutputWindow w, ServerManager m){
		parent = w;
		inventory = m;
		try{
			out  = new PrintWriter(new FileWriter(filename)); 
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void dumpToCSV(){
		dumpToCSV(inventory.searchInventory("SKU > -1"));
	}
	
	public void dumpToCSV(ArrayList<InventoryItem> i){
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
