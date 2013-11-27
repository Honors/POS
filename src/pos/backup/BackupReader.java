package pos.backup;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

import pos.item.InventoryItem;

/**
 * This class reads in data to the server from a backup CSV file
 */
public class BackupReader {
	BufferedReader in;
	
	/**
	 * Creates a BackupReader object linked to a server CSV file
	 * 
	 * @param filename path to the CSV file, including the CSV file and extension
	 */
	public BackupReader(String filename){
		try{
			 in = new BufferedReader(new FileReader(filename)); 
		 } catch (Exception e){
			 e.printStackTrace();
		 }
	}
	
	/**
	 * TODO Figure out what this does
	 * 
	 * @return
	 */
	public ArrayList<InventoryItem> readFromLog(){
		ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
		try{
			System.out.println("  test1");
			String s = in.readLine();
			System.out.println("  test2");
			s = in.readLine();
			while (!s.endsWith("\"")){
				s += "\n" + in.readLine();
			}
			System.out.println("  test3" + "\n\n" + s + "\n\n");
			while (s != null){
				StringTokenizer z = new StringTokenizer(s, ",");
				String t = "";
				InventoryItem i = new InventoryItem();
				System.out.println("  test4");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.SKU = Integer.parseInt(t);
				System.out.println(t + "  test6");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.UPC = t;
				System.out.println(t + "  test7");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.name = t;
				System.out.println(t + "  test8");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.brand = t;
				System.out.println(t + "  test9");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.color = t;
				System.out.println(t + "  test10");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.size = t;
				System.out.println(t + "  test11");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.type = t;
				System.out.println(t + "  test12");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.gender = t;
				System.out.println(t + "  test13");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.client = t;
				System.out.println(t + "  test14");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.date = t;
				System.out.println(t + "  test15");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.notes = t;
				System.out.println(t + "  test16");
				t = z.nextToken();
				System.out.println("  test16/2");
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.price = t;
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.cost = t;
				System.out.println(t + "  test17");
				t = z.nextToken();
				t = t.replace((char)34, ' ');
				t = t.trim();
				i.quantity = Integer.parseInt(t);
				System.out.println(t + "  test18");
				items.add(i);
				System.out.println("  test19");
				System.out.println(i.toStringInsert());
				
				s = in.readLine();
				while (!s.endsWith("\"")){
					s += "\n" + in.readLine();
				}
				
			}
		} catch(Exception e){
			System.out.println(e);
		}
		return items;
	}
}
