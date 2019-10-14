package storeProcess;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
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
	
	private MqttAsyncClient client;
	private MqttConnectOptions connOpts;
	private MemoryPersistence persistence;
	
	private ExecutorService pool;
	private StoreFactory storeFactoryProcess;
	
	public SpManager(int connections) 
	{
		this.connections = connections;
		pool = Executors.newFixedThreadPool(connections);
		storeFactoryProcess = new StoreFactory();
		configureMqtt();
	}
	
	public SpManager(SpInterface sp, int connections) 
	{
		configureMqtt();
		this.connections = connections;
	}
	
	private void configureMqtt() 
	{
		try 
		{
			logger.info("Starting the Mqtt Configuration...");
			
			client = new MqttAsyncClient(conf.getServerURI(), conf.getClientID(), persistence);
			connOpts = new MqttConnectOptions();
			
			connOpts.setCleanSession(true);
			connOpts.setUserName(conf.getUserName());
			connOpts.setPassword(conf.getPassword().toCharArray());
			connOpts.setAutomaticReconnect(true);
			//Setting the callback to accept the messages as soon as the client is connected
			client.setCallback(getCallback());
			
			logger.info("The Mqtt protocol has been configured successfully!!!");
		} 
		catch (Exception e) 
		{
			logger.error("An error has happened during Mqtt configuration");
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void startSub()
	{
		try
		{
			logger.info("Conecting to: " + conf.getServerURI() + " Mqtt Server");
			IMqttToken token = client.connect(connOpts);
			
			token.waitForCompletion();
			logger.info("Connected to the: " + conf.getServerURI() + " Mqtt Server");
						
			logger.info("Subscribe to the Topic: " + this.topic);

			
			client.subscribe("home/Monitoreo/#",0,null,new MqttActionHandler());
			client.subscribe("home/GetConfiguracion/#",0,null,new MqttActionHandler());
			
			
			logger.info("Sucsubscription to topic: " + this.topic); 
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
		return new MqttCallback()
		{
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception
			{
				Timestamp time = new Timestamp(System.currentTimeMillis());
				
				logger.info("\nMensaje Recibido");
				storeFactoryProcess.setTime(time);
				
				storeFactoryProcess.messageArrived(topic, message);
				pool.execute(storeFactoryProcess);
				
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
}
