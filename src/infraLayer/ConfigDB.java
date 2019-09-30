package infraLayer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigDB
{
	private static ConfigDB uniqueInstance;
	private Properties prop;
	private String configPath = "/home/echeverri/Eclipse/mqttPubSub/resources/dataBase.properties";
	private static Logger logger = LogManager.getLogger(ConfigDB.class);
	
	private String urlDB;
	private String userDB;
	private String passDB;
	
	private ConfigDB() throws Exception
	{
		prop = new Properties();
		
		try(InputStream input = new FileInputStream(configPath)) 
		{
			prop = new Properties();
			
			if(input == null)
				throw new Exception("Missing Configuration File for: " + configPath);
			
			prop.load(input);
			
			this.urlDB = prop.getProperty("urlDB");
			this.userDB = prop.getProperty("userDB");
			this.passDB = prop.getProperty("passDB");
			
			logger.info("Configuracion de Base Datos Exitosa!!!");
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static synchronized ConfigDB getInstance() {
		if(uniqueInstance == null)
		{
			try 
			{
				uniqueInstance = new ConfigDB();
			} 
			catch (Exception e) 
			{
				logger.error(e.getMessage());
			}
		}
		return uniqueInstance;
	}

	public String getUrlDB() {
		return urlDB;
	}

	public void setUrlDB(String urlDB) {
		this.urlDB = urlDB;
	}

	public String getUserDB() {
		return userDB;
	}

	public void setUserDB(String userDB) {
		this.userDB = userDB;
	}

	public String getPassDB() {
		return passDB;
	}

	public void setPassDB(String passDB) {
		this.passDB = passDB;
	}
	
	
}
