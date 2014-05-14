package pos.log;

import java.net.*;
import java.util.*;
import java.io.*;

import pos.core.Config;
import pos.lib.ConfigElements;

import com.google.gson.*;

public class Fetcher {
	
  private static Config properties = new Config();
	
  public static String POST(String targetURL, String urlParameters)
  {
	if(properties.getProperty(ConfigElements.LOGGING).equals("true")){
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
	      DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
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
	} else {
		return "";
	}
  }

  public static boolean update(String type, String message) {
	if(properties.getProperty(ConfigElements.LOGGING).equals("true")){
		Config properties = new Config();
	    Map<String, String> contents = new HashMap<String, String>();
	    contents.put("type", type);
	    contents.put("message", message);
	    Gson gson = new Gson();
	    String body = gson.toJson(contents);
	    String http = httpWrap((String)properties.getProperty(ConfigElements.SERVER_ADDRESS)) + ":" + (String)properties.getProperty(ConfigElements.SERVER_PORT) + "/";
	    String resp = Fetcher.POST(http, body);
	    Map<String, Boolean> parsedResp = gson.fromJson(resp, Map.class);
	    return parsedResp.get("success");
	} else {
		return true;
	}
  }

  public static ArrayList<String> read(String type) {
	if(properties.getProperty(ConfigElements.LOGGING).equals("true")){
		Config properties = new Config();
	    Gson gson = new Gson();
	    String http = httpWrap((String)properties.getProperty(ConfigElements.SERVER_ADDRESS)) + ":" + (String)properties.getProperty(ConfigElements.SERVER_PORT) + "/" + type;
	    String resp = Fetcher.POST(http, "{}");
	    ArrayList<String> parsedResp = gson.fromJson(resp, ArrayList.class);
	    return parsedResp;
	} else {
		return new ArrayList<String>();
	}
  }

  public static boolean verify() {
	if(properties.getProperty(ConfigElements.LOGGING).equals("true")){
		  try {
			  Config properties = new Config();
			  String resp = Fetcher.POST(httpWrap(properties.getProperty(ConfigElements.SERVER_ADDRESS) + ":" + properties.getProperty(ConfigElements.SERVER_PORT)) +  "/status", "{}");
			  return resp.contains("success");
		  } catch(Exception e) {
			  e.printStackTrace();
		      return false;
		  }
	  } else {
		  return true;
	  }
  }
  
  private static String httpWrap(String toHttp){
	  if(toHttp.startsWith("http://"))
		  return toHttp;
	  else
		  return "http://" + toHttp;
  }
}