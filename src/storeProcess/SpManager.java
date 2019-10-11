package storeProcess;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import infraLayer.ConfigClass;

public class SpManager implements SpManagerInterface
{
	private static Logger logger = LogManager.getLogger(SpManager.class);
	private ConfigClass conf = ConfigClass.getInstance();
	
	private int connections;
	private String topic;
	
	private MqttClient client;
	private MqttConnectOptions connOpts;
	private MemoryPersistence persistence;
	
	private SpInterface sp;
	
	public SpManager(SpInterface sp) 
	{
		configureMqtt();
		this.sp = sp;
	}
	
	public SpManager(SpInterface sp, int connections) 
	{
		configureMqtt();
		this.sp = sp;
		this.connections = connections;
	}
	
	private void configureMqtt() 
	{
		try 
		{
			logger.info("Starting the Mqtt Configuration...");
			
			client = new MqttClient(conf.getServerURI(), conf.getClientID(), persistence);
			connOpts = new MqttConnectOptions();
			
			connOpts.setCleanSession(true);
			connOpts.setUserName(conf.getUserName());
			connOpts.setPassword(conf.getPassword().toCharArray());
			
			logger.info("The Mqtt protocol has been configured successfully!!!");
		} 
		catch (Exception e) 
		{
			logger.error("An error has happened during Mqtt configuration");
			logger.error(e);
		}
	}
	
	@Override
	public void startSub()
	{
		try
		{
			logger.info("Conecting to the Mqtt Server: " + conf.getServerURI() + "...");
			
			client.connect(connOpts);
			client.setCallback(getCallback());
			client.subscribe(this.topic);
			
			logger.info("Successful subscription to topic: " + this.topic);
		}
		catch(Exception me)
		{

			logger.error("An error has happened: " + me.toString());
			logger.error("\nmsg " + me.getMessage() + 
						"\nloc " + me.getLocalizedMessage() + 
						"\ncause " + me.getCause() + 
						"\nexcep " + me);
		}
	}
	
	private MqttCallback getCallback() 
	{
		return new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception
			{
				/*==================================================================
				 * ES AQUI DONDE VIENE EL ALGORITMO DE BALANCEO DE CARGA
				 * PERO EN LO QUE SE APLICA HACEMOS ESTO...
				 ===================================================================*/
				Timestamp time = new Timestamp(System.currentTimeMillis());
								
				sp.setReceivedMessage(time);
				sp.setTopic(topic);
				sp.storeMessage(message);
				
                logger.info("\nMensaje Recibido" +
                        	"\n\tTime:    " + time + 
                        	"\n\tTopic:   " + topic + 
                        	"\n\tMessage: " + new String(message.getPayload()) + 
                        	"\n\tQoS:     " + message.getQos() + "\n");
			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken cause)
			{
				try
				{
					logger.info("Connection to Solace messaging lost!" + cause.getMessage());
				} 
				catch (MqttException e)
				{
					logger.error(e.getMessage());
				}
			}
			
			@Override
			public void connectionLost(Throwable token) { }
		};
	}

	@Override
	public void stopSub() 
	{
		try 
		{
			client.close();
			logger.info("The connection has been closed successfully!!!");
		} 
		catch (MqttException e) 
		{
			logger.error("An Error ocurred related to closing connection");
			logger.error("Error: " + e.getMessage());
		}
	}

	@Override
	public void setConnections(int conn) { this.connections = conn; }
	@Override
	public int getConnections() { return this.connections; }

	@Override
	public void setTopic(String topic) { this.topic = topic; }
	@Override
	public String getTopic() { return this.topic; }
}
