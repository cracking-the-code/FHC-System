package storeProcess;

import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface SpInterface
{
	void storeMessage(MqttMessage msg);
	void setReceivedMessage(Date time);
	void setTopic(String topic);
}
