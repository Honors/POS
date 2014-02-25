package pos.core;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

import org.apache.commons.io.FileUtils;

import pos.lib.Reference;
 
public class Config
{
   Properties configFile;
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
    String value = this.configFile.getProperty(key);
    return value;
   }
   
   public void copyDefaultConfigFile(){
	   //TODO: make the file copy
   	configFile.put(Reference.SERVER_ADDRESS, "10.2.18.112");
   	configFile.put(Reference.SQLDB_PORT, "3306");
   	configFile.put(Reference.SQLDB_NAME, "SpiritStore");
   	configFile.put(Reference.SQLDB_USERNAME, "root");
   	configFile.put(Reference.SQLDB_PASSWORD, "eagles");
   	configFile.put(Reference.SERVER_PORT, "8080");
   }
}