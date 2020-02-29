package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataLayer.SpDataBaseI;
import dataLayer.SpMySQL;
import infraLayer.ConfigClass;
import storeProcess.SpManager;
import storeProcess.SpManagerInterface;

public class MainProcess {

	private static Logger logger = LogManager.getLogger(MainProcess.class);
	
	public static void main(String[] args)
	{	
		ConfigClass conf = ConfigClass.getInstance();
		SpDataBaseI db = SpMySQL.getInstance();
		
		SpManagerInterface spMonitor = new SpManager(conf.getSpConnections());
		Boolean connection = (spMonitor.isConnected() && db.isConnected()) ? true : false;
		
		while(connection) 
		{
			try
			{
				spMonitor.startSub();
			}
			catch(Exception e)
			{
				logger.fatal("¡¡¡¡¡¡¡¡¡¡A SUPER FATAL ERROR HAS BEEN OCCUR!!!!!!!!!!");
			}
			finally
			{
				db.reconnectDB();
				spMonitor.restartClient();
			}
		}
	}
}
