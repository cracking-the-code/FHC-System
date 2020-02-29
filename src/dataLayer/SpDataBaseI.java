package dataLayer;

public interface SpDataBaseI
{
	void insertSubMessage(SubMessage subMsg);
	void insertDevMeasure(DeviceMeasurement devMeasur);
	void insertDevMqttInf(DeviceMqttInfo devMqtt);
	void insertDevConfig(DeviceConfig devConf);
	void closeDB();
	void reconnectDB();
	Boolean isConnected();
}
