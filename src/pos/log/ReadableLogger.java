package pos.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import pos.lib.Reference;

public class ReadableLogger {

	public static void write(String path, final String statement) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Readable Log.txt", true)));
			out.write(statement);
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		(new Thread(new Runnable() {
			public void run() {
				Fetcher.update(Reference.READABLE_LOG_IDENTIFIER, statement);
			}
		})).start();
	}
}
