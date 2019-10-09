package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import storeProcess.SpManager;
import storeProcess.SpManagerInterface;

public class MainProcess {

	public static void main(String[] args)
	{
		SpManagerInterface spMonitor = new SpManager();
		SpManagerInterface spConfigu = new SpManager();
		
		spMonitor.setConnections(5);
		spMonitor.startSub();
	}
}
