package dataLayer;

public interface SpDataBaseI
{
	void insertSubMessage(SubMessage subMsg);
	void insertDevMeasure(DeviceMeasurement devMeasur);
	void closeDB();
}
