package main;

import infraLayer.ConfigClass;
import storeProcess.SpManager;
import storeProcess.SpManagerInterface;
import storeProcess.StoreConfig;
import storeProcess.StoreProcess;

public class MainProcess {

	public static void main(String[] args)
	{	
		ConfigClass conf = ConfigClass.getInstance();
		
		SpManagerInterface spMonitor = new SpManager(5);
		
		spMonitor.startSub();
	}
}
