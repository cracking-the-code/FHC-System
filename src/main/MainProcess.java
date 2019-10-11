package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import storeProcess.SpManager;
import storeProcess.SpManagerInterface;
import storeProcess.StoreConfig;
import storeProcess.StoreProcess;

public class MainProcess {

	public static void main(String[] args)
	{
		StoreProcess storeP = new StoreProcess();
		StoreConfig storeC = new StoreConfig();
		
		SpManagerInterface spMonitor = new SpManager(storeP);
		SpManagerInterface spConfigu = new SpManager(storeC);
		
		spMonitor.setConnections(5);
		spMonitor.setTopic("");
		spMonitor.startSub();
		
		spConfigu.setConnections(1);
		spMonitor.setTopic("");
		spConfigu.startSub();
	}
}
