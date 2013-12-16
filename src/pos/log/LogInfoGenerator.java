package pos.log;

public class LogInfoGenerator {

	public static String generateReturnStatement(String upc, String name, int quantity, String status){
		return "[*] (" + TimeStamp.simpleDateAndTime() + ") UPC: \"" + upc + "\", Name: \"" + name + "\", " + quantity + " " + status;
	}
	
	public static String generateIncomingStatement(String upc, String name, int oldVal, int newVal){
		return "[+] (" + TimeStamp.simpleDateAndTime() + ") UPC: \"" + upc + "\", Name: \"" + name + "\", " + oldVal + "  ->  " + newVal;
	}
	
	public static String generateOutgoingStatement(String upc, String name, int oldVal, int newVal){
		return "[-] (" + TimeStamp.simpleDateAndTime() + ") UPC: \"" + upc + "\", Name: \"" + name + "\", " + oldVal + "  ->  " + newVal;
	}
}
