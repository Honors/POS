package pos.log;

import java.net.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class Query extends JsonTool {
  public void example() {
    Gson gson = new Gson();
    String query = (new JSON($("name", "matt"))).toString();
    String resp = Fetcher.POST("http://10.2.18.112:8080/query", query);
    ArrayList<Map<String, String>> parsed = gson.fromJson(resp, ArrayList.class);
    System.out.print(parsed.get(0).get("name"));
  }
}

