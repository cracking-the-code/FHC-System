package storeProcess;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dataLayer.DeviceMeasurement;
import dataLayer.SpDataBaseI;
import dataLayer.SpMySQL;
import dataLayer.SubMessage;

public class StoreProcess implements SpInterface
{
	private static Logger logger = LogManager.getLogger(StoreProcess.class);
	
	private String topic;
	private Date receivedTime;
	
	private SpDataBaseI db;
	
	public StoreProcess() 
	{
		db = new SpMySQL();
	}
	
	@Override
	public void storeMessage(MqttMessage msg) 
	{
		logger.info("Beginning the Store Process");
		
		try 
		{
			String payload = new String(msg.getPayload());	
			Gson gson = new Gson();
			JsonElement jsonElement = new JsonParser().parse(payload);
			
			logger.info("Begins the json deserialization");
			JsonMonitoring jsonMessage = gson.fromJson(jsonElement, JsonMonitoring.class);
			
			logger.info("Begins the object deserialization");
			DeviceMeasurement measure = deserializeMeasure(jsonMessage);
			SubMessage subMessage = deserializeSubMessage(jsonMessage,payload);
			
			logger.info("Begins the persistence in database");
			db.insertDevMeasure(measure);
			db.insertSubMessage(subMessage);
		}
		catch(Exception e)
		{
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	private DeviceMeasurement deserializeMeasure(JsonMonitoring json) 
	{
		DeviceMeasurement measure = new DeviceMeasurement();
		
		try 
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			//formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			measure.setIdDev(json.getId());
			measure.setTimeMeasure(formatter.parse(json.getDate()));
			measure.setPotency(json.getMeasure().getPower());
			measure.setVoltage(json.getMeasure().getVoltage());
			measure.setCharge(json.getMeasure().getCurrent());
			measure.setTemperature(json.getMeasure().getTemp());
			measure.setMisc01(json.getMeasure().getHumidity());
			measure.setMisc02(json.getMeasure().getMisc02());
			measure.setMisc03(json.getMeasure().getMisc03());
			measure.setMisc04(json.getMeasure().getMisc04());
			measure.setMisc05(json.getMeasure().getMisc05());
			measure.setMisc06(json.getMeasure().getMisc06());
			measure.setMisc07(json.getMeasure().getMisc07());
			measure.setMisc08(json.getMeasure().getMisc08());
			measure.setMisc09(json.getMeasure().getMisc09());
			measure.setMisc10(json.getMeasure().getMisc10());
			measure.setMisc11(json.getMeasure().getMisc11());
			measure.setMisc12(json.getMeasure().getMisc12());
			measure.setMisc13(json.getMeasure().getMisc13());
			measure.setMisc14(json.getMeasure().getMisc14());
			measure.setMisc15(json.getMeasure().getMisc15());
			measure.setMisc16(json.getMeasure().getMisc16());
			measure.setMisc17(json.getMeasure().getMisc17());
			measure.setMisc18(json.getMeasure().getMisc18());
			measure.setMisc19(json.getMeasure().getMisc19());
			measure.setMisc20(json.getMeasure().getMisc20());
		}
		catch(Exception e)
		{
			logger.error("Error al deserealizar el mensaje: " + json.getDate());
			logger.error(e.getMessage());
		}
		
		return measure;
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
