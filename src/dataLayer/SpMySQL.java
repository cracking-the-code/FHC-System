package dataLayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import storeProcess.StoreProcess;

public class SpMySQL implements SpDataBaseI
{
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("FHC_System");
	
	private static Logger logger = LogManager.getLogger(SpMySQL.class);
	
	public SpMySQL()
	{
		
	}
	
	@Override
	public void insertSubMessage(SubMessage subMsg) 
	{
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try 
		{
			logger.info("Se procede a guardar en la tabla Tbl_SubMessage el mensaje: " + subMsg.getIdMessage());
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(subMsg);
			et.commit();
		}
		catch(Exception e)
		{
			if(et != null)
				et.rollback();
			logger.error("Error al guardar ne la tabla Tbl_SubMessage el mensaje: " + subMsg.getIdMessage());
			e.printStackTrace();
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
			logger.info("Se procede a guardar en la tabla Tbl_DeviceMeasurement la medicion: " + devMeasur.getId());
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(devMeasur);
			et.commit();
		}
		catch(Exception e)
		{
			if(et != null)
				et.rollback();
			
			logger.info("Error al guardar en la tabla Tbl_DeviceMeasurement la medicion: " + devMeasur.getId());
			e.printStackTrace();
		}
		finally
		{
			em.close();
		}
	}

	@Override
	public void closeDB() 
	{
		ENTITY_MANAGER_FACTORY.close();
	}
}
