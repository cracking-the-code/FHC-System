package storeProcess;

import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class StoreFactory implements Runnable 
{
	private String topic;
	private MqttMessage message;
	private SpInterface sp;
	
	private Timestamp time;
	
	public StoreFactory()
	{
		
	}
	
	public void setTime(Timestamp time) { this.time = time; }
	
	@Override
	public void run()
	{
		//This is the factory
		sp = spFactory(this.topic);
	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sp.setReceivedMessage(this.time);
		sp.setTopic(this.topic);
		sp.storeMessage(this.message);
	}
	
	public void messageArrived(String topic, MqttMessage message)
	{
		this.topic = topic;
		this.message = message;
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
