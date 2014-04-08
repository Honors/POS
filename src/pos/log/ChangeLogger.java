package pos.log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pos.core.UpdateableContent;
import pos.core.UpdateableContentController;
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
				UpdateableContentController.postUpdate(UpdateableContent.CHANGE_LOG_UPDATED, item);
			}
		})).start();
		
	}
	
	public static ArrayList<ChangeLogItem> read(){
		ArrayList<String> items = Fetcher.read(Reference.CHANGE_LOG_IDENTIFIER);
		ArrayList<ChangeLogItem> parses = new ArrayList<ChangeLogItem>();
		int i = 0;
		for( String elm : items ) {
			parses.add(JSON.fromString(elm, ChangeLogItem.class));
			parses.get(i).index = i;
			i++;
		}
		return parses;
	}
	
	public static String[][] getLoggedChanges(Date start, Date end){
		ArrayList<ChangeLogItem> changeItems = read();
		ArrayList<ChangeLogItem> validItems = new ArrayList<ChangeLogItem>();
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy_HH.mm.ss");
		for(ChangeLogItem change : changeItems){
			try {
				Date changeDate = df.parse(change.date);
				if(start.before(changeDate) && end.after(changeDate))
					validItems.add(change);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String[][] data = new String[validItems.size()][5];
		for(int i = 0; i < validItems.size(); i++){
			data[i][0] = validItems.get(i).date;
			data[i][1] = validItems.get(i).sku;
			data[i][2] = validItems.get(i).identifier;
			data[i][3] = validItems.get(i).status;
			data[i][4] = validItems.get(i).change;
		}
		return data;
	}
}
