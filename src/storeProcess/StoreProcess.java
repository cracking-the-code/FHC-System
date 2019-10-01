package storeProcess;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dataLayer.DeviceMeasurement_Dto;
import dataLayer.SpDataBaseI;
import dataLayer.SpMySQL;
import dataLayer.SubMessage_Dto;

public class StoreProcess implements SpInterface 
{
	private static Logger logger = LogManager.getLogger(StoreProcess.class);
	private SpDataBaseI db;
	
	public StoreProcess() 
	{
		db = new SpMySQL();
	}
	
	@Override
	public void storeMessage(MqttMessage msg) 
	{
		String payload = msg.getPayload().toString();
		DeviceMeasurement_Dto measure = deserialiceMeasure(payload);
		SubMessage_Dto subMessage = deserialiceSubMessage(payload,"");
		db.insertDevMeasure(measure);
		db.insertSubMessage(subMessage);
	}
	
	private DeviceMeasurement_Dto deserialiceMeasure(String json) 
	{
		DeviceMeasurement_Dto measure = new DeviceMeasurement_Dto();
		
		JsonParser parser = new JsonParser();
		JsonObject gsonObj = parser.parse(json).getAsJsonObject();
		
		measure.setIdDev(gsonObj.get("Id").getAsString());
		//measure.setPotency(gsonObj.get(""));
		
		return measure;
	}

	private SubMessage_Dto deserialiceSubMessage(String json, String topic) 
	{
		SubMessage_Dto subMessage = new SubMessage_Dto();
		
		JsonParser parser = new JsonParser();
		JsonObject gsonObj = parser.parse(json).getAsJsonObject();
		
		subMessage.setIdDev(gsonObj.get("Id").getAsString());
		subMessage.setIdMessage(gsonObj.get("Date").getAsString());
		subMessage.setReceivedTime(new Date());
		subMessage.setTopic(topic);
		subMessage.setMessage(json);
		
		return subMessage;
	}
}
