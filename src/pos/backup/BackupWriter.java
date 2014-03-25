package pos.backup;

import java.io.*;
import java.util.ArrayList;

import pos.core.ServerManager;
import pos.item.InventoryItem;
import pos.item.ReturnItem;
import pos.core.OutputWindow;

/**
 * This class writes server data to a backup CSV file
 */
public class BackupWriter {
	PrintWriter out;
	BufferedReader in;
	OutputWindow parent;
	ServerManager server;
	
	/**
	 * Links a BackupWriter object to a file, OutputWindow and ServerManager
	 * 
	 * @param filename path to the CSV file, including the CSV file and extension
	 * @param w window to print backup results on
	 * @param m server to pull information from
	 */
	public BackupWriter(String filename, OutputWindow w, ServerManager m){
		parent = w;
		server = m;
		try{
			out  = new PrintWriter(new FileWriter(filename)); 
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * Exports all the Inventory to a CSV file
	 */
	public void exportInventoryToCSV(){
		exportInventoryToCSV(server.searchInventory("SKU > -1"));
	}
	
	/**
	 * Exports all the Return to a CSV file
	 */
	public void exportReturnToCSV(){
		exportReturnToCSV(server.searchReturn("SKU > -1"));
	}
	
	/**
	 * Exports defined Inventory items to a CSV file
	 * @param i Inventory items to be exported
	 */
	public void exportInventoryToCSV(ArrayList<InventoryItem> i){
		out.println("INVENTORY");
		out.println("SKU,UPC,NAME,BRAND,COLOR,SIZE,TYPE,GENDER,CLIENT,DATE,NOTES,PRICE,COST,QUANTITY");
		while (!i.isEmpty()){
			out.println(i.remove(0).toSrtingCSV());
		}
		out.println("");
	}
	
	/**
	 * Exports defined Return items to a CSV file
	 * @param i Return items to be exported
	 */
	public void exportReturnToCSV(ArrayList<ReturnItem> i){
		out.println("RETURN");
		out.println("SKU,UPC,NAME,BRAND,COLOR,SIZE,TYPE,GENDER,CLIENT,DATE,NOTES,PRICE,COST,QUANTITY,STATUS");
		while (!i.isEmpty()){
			out.println(i.remove(0).toSrtingCSV());
			
		}
		out.println("");
	}
	
	/**
	 * Ends the output stream
	 */
	public void close(){
		out.close();
	}
}
