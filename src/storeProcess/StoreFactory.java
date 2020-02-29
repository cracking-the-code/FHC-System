package storeProcess;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class StoreFactory implements Runnable 
{
	private static Logger logger = LogManager.getLogger(StoreFactory.class);
	
	private String topic;
	private MqttMessage message;
	private SpInterface sp;
	
	private Timestamp time;
	
	public StoreFactory() { }
	
	@Override
	public void run()
	{
		sp = spFactory(this.topic);
		sp.setReceivedMessage(this.time);
		sp.setTopic(this.topic);
		
		try {	sp.storeMessage(this.message); }
		catch(Exception e) 
		{
			logger.fatal("FATAL ERROR IN STORAGE MESSAGE!!!");
			logger.fatal(e.getMessage());
		}
	}
	
	public void messageArrived(String topic, MqttMessage message, Timestamp time)
	{
		this.topic = topic;
		this.message = message;
		this.time = time;
	}
	
	private SpInterface spFactory(String topic)
	{
		if(topic.contains("home/Monitoreo/"))
			topic = "home/Monitoreo/#";
		else if(topic.contains("home/GetConfiguracion/"))
			topic = "home/GetConfiguracion/#";
		
		switch(topic)
		{
			case "home/Monitoreo/#":
				return new StoreProcess();
			case "home/GetConfiguracion/#":
				return new StoreConfig();
			default:
				return null;
		}
	}
}
