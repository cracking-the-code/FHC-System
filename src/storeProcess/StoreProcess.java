package storeProcess;

import java.util.List;

public interface StoreProcess
{
	void startSubs();
	void stopSub();
	void setSubscribers(List<String> lstSub);
	List<String> getSubscribers();
}
