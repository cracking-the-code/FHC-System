package main;

import dataLayer.SpDataBaseI;
import dataLayer.SpMySQL;
import infraLayer.ConfigClass;
import storeProcess.SpManager;
import storeProcess.SpManagerInterface;

public class MainProcess {

	public static void main(String[] args)
	{	
		ConfigClass conf = ConfigClass.getInstance();
		SpDataBaseI db = SpMySQL.getInstance();
		int connections = conf.getSpConnections();
		
		SpManagerInterface spMonitor = new SpManager(connections);
		spMonitor.startSub();
	}
}
