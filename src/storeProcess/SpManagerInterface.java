package storeProcess;

import java.util.List;

public interface SpManagerInterface 
{
	void startSub();
	void stopSub();
	void setConnections(int conn);
	int getConnections();
}
