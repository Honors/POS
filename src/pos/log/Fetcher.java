package pos.log;

import java.net.*;
import java.util.*;
import java.io.*;

import pos.lib.Reference;

import com.google.gson.*;

class Fetcher {
  public static String POST(String targetURL, String urlParameters)
  {
    URL url;
    HttpURLConnection connection = null;
    try {
      //Create connection
      url = new URL(targetURL);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type",
           "application/x-www-form-urlencoded");

      connection.setRequestProperty("Content-Length", "" +
               Integer.toString(urlParameters.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");

      connection.setUseCaches (false);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      //Send request
      DataOutputStream wr = new DataOutputStream (
                  connection.getOutputStream ());
      wr.writeBytes (urlParameters);
      wr.flush ();
      wr.close ();

      //Get Response
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      String line;
      StringBuffer response = new StringBuffer();
      while((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();

    } catch (Exception e) {

      e.printStackTrace();
      return null;

    } finally {

      if(connection != null) {
        connection.disconnect();
      }
    }
  }

  public static boolean update(String type, String message) {
    Map<String, String> contents = new HashMap<String, String>();
    contents.put("type", type);
    contents.put("message", message);
    Gson gson = new Gson();
    String body = gson.toJson(contents);
    String resp = Fetcher.POST(httpWrap(Reference.SERVER_ADDRESS) + ":" + Reference.SERVER_PORT + "/", body);
    Map<String, Boolean> parsedResp = gson.fromJson(resp, Map.class);
    return parsedResp.get("success");
  }

  public static ArrayList<String> read(String type) {
    Gson gson = new Gson();
    String resp = Fetcher.POST(httpWrap(Reference.SERVER_ADDRESS) + ":" + Reference.SERVER_PORT + "/" + type, "{}");
    ArrayList<String> parsedResp = gson.fromJson(resp, ArrayList.class);
    return parsedResp;
  }

  private static String httpWrap(String toHttp){
	  if(toHttp.startsWith("http://"))
		  return toHttp;
	  else
		  return "http://" + toHttp;
  }
  
  private void example(){
    Fetcher.update("activity", "Hello, World");
    System.out.println(Fetcher.read("activity").get(0));
  }
}