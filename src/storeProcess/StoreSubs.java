package storeProcess;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import dataLayer.SpMySQL;
import dataLayer.SpPersistence;
import dataLayer.SubMessage_Dto;
import infraLayer.ConfigClass;

public class StoreSubs implements StoreProcess
{
	private static Logger logger = LogManager.getLogger(StoreSubs.class);
	private ConfigClass conf = ConfigClass.getInstance();
	
	private List<String> lstSub;
	private SpPersistence perDB;
	
	private MqttClient client;
	private MqttConnectOptions connOpts;
	private MemoryPersistence persistence;
	
	public StoreSubs()
	{
		perDB = new SpMySQL();
		configureMqtt();
	}
	
	private void configureMqtt() 
	{
		try 
		{
			client = new MqttClient(conf.getServerURI(), conf.getClientID(), persistence);
			
			connOpts.setCleanSession(true);
			connOpts.setUserName(conf.getUserName());
			connOpts.setPassword(conf.getPassword().toCharArray());
		} 
		catch (MqttException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void startSubs()
	{
		try
		{
			logger.info("Conectando al servidor: " + conf.getServerURI());
			client.connect(connOpts);
			client.setCallback(getCallback());
			
			client.subscribe("Home/#");
			
			logger.info("Coneccion Exitosa!!!");
		}
		catch(Exception me)
		{

			logger.error("Un Error ha sucedido: " + me.toString());
			logger.error("\nmsg " + me.getMessage() + 
						"\nloc " + me.getLocalizedMessage() + 
						"\ncause " + me.getCause() + 
						"\nexcep " + me);
		}
	}
	
	public MqttCallback getCallback() 
	{
		return new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception
			{
				String time = new Timestamp(System.currentTimeMillis()).toString();
				
				
				
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
		} 
		catch (MqttException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setSubscribers(List<String> lstSub) { this.lstSub = lstSub;	}

	@Override
	public List<String> getSubscribers() { return lstSub; }

}
