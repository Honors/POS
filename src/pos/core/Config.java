package pos.core;

import java.util.*;

import pos.lib.ConfigElements;
 
public class Config
{
	
   private Properties configFile;
   private Properties defaultFile;
   private static final boolean external = false;
   
   public Config()
   {
    configFile = new Properties();
    defaultFile = new Properties();
    loadDefaults();
    try {
      configFile.load(this.getClass().getClassLoader().getResourceAsStream("./properties.cfg"));
    }catch(Exception eta){
        configFile = defaultFile;
    }
   }
 
   public String getProperty(String key)
   {
	try{
		return configFile.getProperty(key);
	} catch(Exception e){
		return defaultFile.getProperty(key);
	}
   }
   
   public void loadDefaults(){
	   //TODO: make the file copy
	   if(external){
		   	defaultFile.put(ConfigElements.SERVER_ADDRESS, "10.2.18.112");
		   	defaultFile.put(ConfigElements.SQLDB_PORT, "3306");
		   	defaultFile.put(ConfigElements.SQLDB_NAME, "SpiritStore");
		   	defaultFile.put(ConfigElements.SQLDB_USERNAME, "root");
		   	defaultFile.put(ConfigElements.SQLDB_PASSWORD, "eagles");
		   	defaultFile.put(ConfigElements.SERVER_PORT, "8080");
		   	defaultFile.put(ConfigElements.LOGGING, "true");
	   } else {
		   defaultFile.put(ConfigElements.SERVER_ADDRESS, "localhost");
		   defaultFile.put(ConfigElements.SQLDB_PORT, "3306");
		   defaultFile.put(ConfigElements.SQLDB_NAME, "SpiritStore");
		   defaultFile.put(ConfigElements.SQLDB_USERNAME, "root");
		   defaultFile.put(ConfigElements.SQLDB_PASSWORD, "ericpaul");
		   defaultFile.put(ConfigElements.SERVER_PORT, "8080");
		   defaultFile.put(ConfigElements.LOGGING, "false");
	   }
   }
}