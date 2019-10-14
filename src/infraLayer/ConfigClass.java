package infraLayer;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConfigClass 
{
	private static ConfigClass uniqueInstance;
	private Properties prop;
	private Path configPath = Paths.get("resources","config.properties");
	private static Logger logger = LogManager.getLogger(ConfigClass.class);
	
	private String serverURI;
	private String clientID;
	private String userName;
	private String password;
	private int qos;
	private String monitorTopic;
	private String getConfTopic;
	private String setConfTopic;
	private int spConnections;
	
	private ConfigClass() throws Exception
	{
		logger.info("Starting the General Configuration...");
		try(InputStream input = new FileInputStream(configPath.toAbsolutePath().toString())) 
		{
			prop = new Properties();
			
			if(input == null)
				throw new Exception("Missing Configuration File for: " + configPath.toAbsolutePath().toString());
			
			prop.load(input);
			
			this.serverURI = prop.getProperty("serverURI");
			this.clientID = prop.getProperty("clientID");
			this.userName = prop.getProperty("userName");
			this.password = prop.getProperty("password");
			this.qos = Integer.parseInt(prop.getProperty("qos"));
			this.monitorTopic = prop.getProperty("MonitorTopic");
			this.getConfTopic = prop.getProperty("getConfTopic");
			this.setConfTopic = prop.getProperty("setConfTopic");
			this.spConnections = Integer.parseInt(prop.getProperty("spConnections"));
			
			logger.info("Successful COnfiguration!!!");
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static synchronized ConfigClass getInstance() {
		if(uniqueInstance == null)
		{
			try 
			{
				uniqueInstance = new ConfigClass();
			} 
			catch (Exception e) 
			{
				logger.error(e.getMessage());
			}
		}
		return uniqueInstance;
	}

	public Properties getProp() {
		return prop;
	}

	public String getServerURI() {
		return serverURI;
	}

	public String getClientID() {
		return clientID;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getQos() {
		return qos;
	}
	
	public String getMonitorTopic() {
		return monitorTopic;
	}
	
	public String getGetConfTopic() {
		return getConfTopic;
	}
	
	public String getSetConfTopic() {
		return setConfTopic;
	}

	public int getSpConnections() {
		return spConnections;
	}
}
