package pos.core;

import java.util.*;

import pos.lib.ConfigElements;
import pos.lib.Reference;
 
public class Config
{
	
   private Properties configFile;
   private static final boolean external = false;
   
   public Config()
   {
    configFile = new java.util.Properties();
    try {
      configFile.load(this.getClass().getClassLoader().getResourceAsStream("./properties.cfg"));
    }catch(Exception eta){
        copyDefaultConfigFile();
    }
   }
 
   public String getProperty(String key)
   {
    return this.configFile.getProperty(key);
   }
   
   public void copyDefaultConfigFile(){
	   //TODO: make the file copy
	   if(external){
		   	configFile.put(ConfigElements.SERVER_ADDRESS, "10.2.18.112");
		   	configFile.put(ConfigElements.SQLDB_PORT, "3306");
		   	configFile.put(ConfigElements.SQLDB_NAME, "SpiritStore");
		   	configFile.put(ConfigElements.SQLDB_USERNAME, "root");
		   	configFile.put(ConfigElements.SQLDB_PASSWORD, "eagles");
		   	configFile.put(ConfigElements.SERVER_PORT, "8080");
		   	configFile.put(ConfigElements.LOGGING, "true");
	   } else {
		    configFile.put(ConfigElements.SERVER_ADDRESS, "localhost");
		   	configFile.put(ConfigElements.SQLDB_PORT, "3306");
		   	configFile.put(ConfigElements.SQLDB_NAME, "SpiritStore");
		   	configFile.put(ConfigElements.SQLDB_USERNAME, "root");
		   	configFile.put(ConfigElements.SQLDB_PASSWORD, "ericpaul");
		   	configFile.put(ConfigElements.SERVER_PORT, "8080");
		   	configFile.put(ConfigElements.LOGGING, "false");
	   }
   }
}