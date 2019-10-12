package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import infraLayer.ConfigClass;
import storeProcess.SpManager;
import storeProcess.SpManagerInterface;
import storeProcess.StoreConfig;
import storeProcess.StoreProcess;

public class MainProcess {

	public static void main(String[] args)
	{
		ConfigClass conf = ConfigClass.getInstance();
		
		StoreProcess storeP = new StoreProcess();
		StoreConfig storeC = new StoreConfig();
		
		SpManagerInterface spMonitor = new SpManager(storeP);
		SpManagerInterface spConfigu = new SpManager(storeC);
		
		spMonitor.setConnections(5);
		System.out.print(conf.getMonitorTopic().toString());
		spMonitor.startSub();
		
		spConfigu.setConnections(1);
		spConfigu.setTopic(conf.getGetConfTopic());
		spConfigu.startSub();
	}
}
