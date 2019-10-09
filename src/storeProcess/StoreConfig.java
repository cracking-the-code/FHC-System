package storeProcess;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import dataLayer.SpDataBaseI;
import dataLayer.SpMySQL;
import dataLayer.SubMessage;

public class StoreConfig implements SpInterface
{
private static Logger logger = LogManager.getLogger(StoreConfig.class);
	
	private String topic;
	private Date receivedTime;
	
	private SpDataBaseI db;
	
	public StoreConfig() 
	{
		db = new SpMySQL();
	}
	
	@Override
	public void storeMessage(MqttMessage msg)
	{
		
	}
	
	private SubMessage deserializeSubMessage(JsonMonitoring json, String message) 
	{
		SubMessage subMessage = new SubMessage();
		
		try 
		{
			subMessage.setIdDev(json.getId());
			subMessage.setIdMessage(json.getDate());
			subMessage.setReceivedTime(receivedTime);
			subMessage.setTopic(topic);
			subMessage.setMessage(message);
		}
		catch(Exception e)
		{
			logger.error("Error al deserealizar el mensaje: " + json.getDate());
			logger.error(e.getMessage());
		}
		
		return subMessage;
	}

	@Override
	public void setReceivedMessage(Date time) { this.receivedTime = time; }

	@Override
	public void setTopic(String topic) { this.topic = topic; }

}
