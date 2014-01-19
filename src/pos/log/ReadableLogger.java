package pos.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ReadableLogger {

	public static void write(String path, String statement){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path + "\\Readable Log.txt", true)));
			out.write(statement);
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
