package storeProcess;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface SpInterface
{
	void storeMessage(MqttMessage msg);
}
