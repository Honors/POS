package pos.core;

import java.util.ArrayList;


public class UpdateableContentController {
	
	private static ArrayList<UpdateableContent> activeContent = new ArrayList<UpdateableContent>();
	
	public static void addActiveContent(UpdateableContent content){
		activeContent.add(content);
	}
	
	public static void removeActiveContent(UpdateableContent content){
		activeContent.remove(content);
	}
	
	public static void postUpdate(String updateIdentifier, String info){
		for(UpdateableContent content : activeContent){
			content.update(updateIdentifier, info);
		}
	}
}
