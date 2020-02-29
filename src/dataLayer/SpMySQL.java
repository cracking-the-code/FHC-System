package dataLayer;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpMySQL implements SpDataBaseI
{
	private static SpMySQL uniqueInstance;
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	private static Logger logger = LogManager.getLogger(SpMySQL.class);
	
	public static synchronized SpMySQL getInstance() {
		if(uniqueInstance == null)
		{
			try 
			{
				uniqueInstance = new SpMySQL();
			} 
			catch (Exception e) 
			{
				logger.error(e.getMessage());
			}
		}
		return uniqueInstance;
	}
	
	private SpMySQL()
	{
		logger.info("Initializing Data Base Connection...");
		
		ENTITY_MANAGER_FACTORY = Persistence
	            .createEntityManagerFactory("FHC_System");
	}
	
	@Override
	public void insertSubMessage(SubMessage subMsg) 
	{
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try 
		{
			logger.info("The message: " + subMsg.getIdMessage() + " will be stored in Tbl_SubMessage");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			subMsg.setProcessedTime(time);
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(subMsg);
			et.commit();
		}
		catch(Exception e)
		{
			if(et != null)
				et.rollback();
			
			logger.error("Cannot save in table Tbl_SubMessage the message: " + subMsg.getIdMessage());
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void insertDevMeasure(DeviceMeasurement devMeasur)
	{
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try 
		{
			logger.info("The measurement: " + devMeasur.getId() + " Will be stored in Tbl_DeviceMeasurement");
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(devMeasur);
			et.commit();
		}
		catch(Exception e)
		{
			if(et != null)
				et.rollback();
			
			logger.info("Cannot save in table Tbl_DeviceMeasurement the measurement: " + devMeasur.getId());
			logger.error("\nmsg " + e.getMessage() + 
					 	 "\nloc " + e.getLocalizedMessage() + 
					 	 "\ncause " + e.getCause() + 
					 	 "\nexcep " + e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void insertDevMqttInf(DeviceMqttInfo devMqtt)
	{
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try 
		{
			logger.info("The info: " + devMqtt.getIdDev() + " will be stored in Tbl_DeviceMqttInf");
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(devMqtt);
			et.commit();
		}
		catch(Exception e)
		{
			if(et != null)
				et.rollback();
			
			logger.info("Cannot save in table Tbl_DeviceMqttInf the info: " + devMqtt.getIdDev());
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void insertDevConfig(DeviceConfig devConf)
	{
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try 
		{
			logger.info("The configuration: " + devConf.getIdDev() + " will be stored in Tbl_DeviceConfig");
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(devConf);
			et.commit();
		}
		catch(Exception e)
		{
			if(et != null)
				et.rollback();
			
			logger.info("Cannot save in table Tbl_DeviceConfig the configuration: " + devConf.getIdDev());
			logger.error("\nmsg " + e.getMessage() + 
					 	 "\nloc " + e.getLocalizedMessage() + 
					 	 "\ncause " + e.getCause() + 
					 	 "\nexcep " + e);
		}
		finally
		{
			em.close();
		}
	}
	
	@Override
	public void closeDB() 
	{
		logger.info("Closing Data Base Connection...");
		ENTITY_MANAGER_FACTORY.close();
	}
	
	@Override
	public void reconnectDB()
	{
		try 
		{
			logger.info("Restarting Data Base Connection...");
			logger.info("Closing Data Base Connection...");
			ENTITY_MANAGER_FACTORY.close();
			
			logger.info("Initializing Data Base Connection...");
			ENTITY_MANAGER_FACTORY = Persistence
		            .createEntityManagerFactory("FHC_System");
		}
		catch(Exception e)
		{
			logger.warn("Is not possible to Restart the DB Connection");
			throw e;
		}
	}
	
	@Override
	public Boolean isConnected() { return ENTITY_MANAGER_FACTORY.isOpen(); }
}
