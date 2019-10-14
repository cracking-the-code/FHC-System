package storeProcess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MqttActionHandler implements IMqttActionListener
{
	private static Logger logger = LogManager.getLogger(MqttActionHandler.class);
	
	enum Action 
	{
		CONNECT,
		DISCONNECT,
		SUBSCRIBE,
		PUBLISH
	}
	
	private Action action;
	
	@Override
	public void onSuccess(IMqttToken asyncActionToken)
	{
		logger.info("On success!!!!!!!!!!!!!!");
		logger.info(asyncActionToken.getTopics().toString());
	}

	@Override
	public void onFailure(IMqttToken asyncActionToken, Throwable exception)
	{
		logger.warn("Cannot Stablish the connection!!!");
		logger.warn(exception);
	}

	public Action getAction() { return action; }
	public void setAction(Action action) { this.action = action; }
}
