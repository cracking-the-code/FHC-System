package storeProcess;

public interface SpManagerInterface 
{
	void startSub();
	void stopSub();
	void setConnections(int conn);
	int getConnections();
}
