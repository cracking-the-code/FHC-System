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
	
	private MqttClient client;
	private MqttConnectOptions connOpts;
	private MemoryPersistence persistence;
	private StoreProcess sp = new StoreProcess();
	
	public SpManager() 
	{
		configureMqtt();
	}
	
	public SpManager(int connections) 
	{
		configureMqtt();
		this.connections = connections;
	}
	
	private void configureMqtt() 
	{
		try 
		{
			logger.info("Se procede a configurar el cliente Mqtt");
			
			client = new MqttClient(conf.getServerURI(), conf.getClientID(), persistence);
			connOpts = new MqttConnectOptions();
			
			connOpts.setCleanSession(true);
			connOpts.setUserName(conf.getUserName());
			connOpts.setPassword(conf.getPassword().toCharArray());
			
			logger.info("Se ha configurado el cliente correctamente!!!");
		} 
		catch (Exception e) 
		{
			logger.error("Ha sucedido un error en la configuracion Mqtt ");
			logger.error(e);
		}
	}
	
	@Override
	public void startSub()
	{
		try
		{
			logger.info("Conectando al servidor: " + conf.getServerURI());
			
			client.connect(connOpts);
			client.setCallback(getCallback());
			client.subscribe("home/Monitoreo/#");
			
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
	
	private MqttCallback getCallback() 
	{
		return new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception
			{
				
				/*==================================================================
				 * ES AQUI DONDE VIENE EL ALGORITMO DE BALANCEO DE CARGA
				 ===================================================================*/
				String time = new Timestamp(System.currentTimeMillis()).toString();
				
				
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
			logger.info("Se hace cerrado la conexion Mqtt Exitosamente!!!");
		} 
		catch (MqttException e) 
		{
			logger.error("Error en el cerrado de la conexion Mqtt");
			logger.error("Error: " + e.getMessage());
		}
	}

	@Override
	public void setConnections(int conn) { this.connections = conn; }
	@Override
	public int getConnections() { return this.connections; }
}
