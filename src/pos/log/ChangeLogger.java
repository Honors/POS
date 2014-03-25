package pos.log;

import java.util.ArrayList;

import pos.lib.Reference;

public class ChangeLogger extends JsonTool {

	public static void write(String identifier, String date, int sku, String status, int change){
		final String item = (new JSON(
				$("identifier", identifier), 
				$("date", date),
				$("sku", String.valueOf(sku)), 
				$("status", status),
				$("change",String.valueOf(change)))).toString();
		
		(new Thread(new Runnable() {
			public void run() {
				Fetcher.update(Reference.CHANGE_LOG_IDENTIFIER, item);
			}
		})).start();
		
	}
	
	public static ArrayList<ChangeLogItem> read(){
		ArrayList<String> items = Fetcher.read(Reference.CHANGE_LOG_IDENTIFIER);
		ArrayList<ChangeLogItem> parses = new ArrayList<ChangeLogItem>();
		for( String elm : items ) {
			parses.add(JSON.fromString(elm, ChangeLogItem.class));
		}
		return parses;
	}
}
