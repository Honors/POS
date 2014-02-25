package pos.log;

import java.net.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;

class Query extends JsonTool {
  public static void main(String[] args) {
    Gson gson = new Gson();
    String query = (new JSON($("name", "matt"))).toString();
    String resp = Fetcher.POST("http://10.2.18.112:8080/query", query);
    ArrayList<Map<String, String>> parsed = gson.fromJson(resp, ArrayList.class);
    System.out.print(parsed.get(0).get("name"));
  }
}

