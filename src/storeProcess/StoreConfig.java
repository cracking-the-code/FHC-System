package storeProcess;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.Gson;

import dataLayer.DeviceConfig;
import dataLayer.DeviceMeasurement;
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
		logger.info("Se almacenara Mensaje en DB");
		
		try 
		{
			String payload = new String(msg.getPayload());	
			Gson gson = new Gson();
			System.out.println(payload);
			
			JsonMonitoring jsonMessage = gson.fromJson(payload, JsonMonitoring.class);
			JsonGetConfiguration jsonConfig = gson.fromJson(payload, JsonGetConfiguration.class);
			
			DeviceConfig devConf = deserializeDevConfig(jsonConfig);
			SubMessage subMessage = deserializeSubMessage(jsonMessage,payload);
			
			
			db.insertDevConfig(devConf);
			db.insertSubMessage(subMessage);
		}
		catch(Exception e)
		{
			logger.error(e);
		}
	}
	
	private DeviceConfig deserializeDevConfig(JsonGetConfiguration json) 
	{
		DeviceConfig devConf = new DeviceConfig();
		
		try 
		{
			devConf.setIdDev(Integer.toString(json.getId()));
			devConf.setQoS(json.getMqtt().getQoS());
			devConf.setCleanSession(json.getMqtt().getCleanSession());
			devConf.setPersistence(json.getMqtt().getPersistence());
			devConf.setFrecuencySamplig(json.getParameters().getSample());
			devConf.setPotency(json.getParameters().getPower());
			devConf.setVoltage(json.getParameters().getVoltage());
			devConf.setCharge(json.getParameters().getCharge());
			devConf.setMisc01(json.getParameters().getMisc01());
	        devConf.setMisc02(json.getParameters().getMisc02());
	        devConf.setMisc03(json.getParameters().getMisc03());
	        devConf.setMisc04(json.getParameters().getMisc04());
	        devConf.setMisc05(json.getParameters().getMisc05());
	        devConf.setMisc06(json.getParameters().getMisc06());
	        devConf.setMisc07(json.getParameters().getMisc07());
	        devConf.setMisc08(json.getParameters().getMisc08());
	        devConf.setMisc09(json.getParameters().getMisc09());
	        devConf.setMisc10(json.getParameters().getMisc10());
	        devConf.setMisc11(json.getParameters().getMisc11());
	        devConf.setMisc12(json.getParameters().getMisc12());
	        devConf.setMisc13(json.getParameters().getMisc13());
	        devConf.setMisc14(json.getParameters().getMisc14());
	        devConf.setMisc15(json.getParameters().getMisc15());
	        devConf.setMisc16(json.getParameters().getMisc16());
	        devConf.setMisc17(json.getParameters().getMisc17());
	        devConf.setMisc18(json.getParameters().getMisc18());
	        devConf.setMisc19(json.getParameters().getMisc19());
	        devConf.setMisc20(json.getParameters().getMisc20());
			
		}
		catch(Exception e)
		{
			logger.error("An error happened during deserialization: " + json.getId());
			logger.error(e.getMessage());
		}
		
		return devConf;
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
			logger.error("An error happened during deserialization: " + json.getDate());
			logger.error(e.getMessage());
		}
		
		return subMessage;
	}

	@Override
	public void setReceivedMessage(Date time) { this.receivedTime = time; }

	@Override
	public void setTopic(String topic) { this.topic = topic; }

}
