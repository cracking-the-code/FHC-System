package main;

import storeProcess.SpManager;
import storeProcess.SpManagerInterface;

public class MainProcess {

	public static void main(String[] args)
	{
		SpManagerInterface sp = new SpManager();
		
		sp.setConnections(5);
		sp.startSub();
	}
}
