package dataLayer;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import infraLayer.ConfigDB;

public class SpMySQL implements SpDataBaseI
{
	private static Logger logger = LogManager.getLogger(SpMySQL.class);
	private ConfigDB confDB = ConfigDB.getInstance();
	
	
	public SpMySQL() 
	{
		try 
		{
            Class.forName("com.mysql.jdbc.Driver");
        } 
		catch (ClassNotFoundException ex) 
		{
        	logger.error("Error al registrar el driver de MySQL: " + ex);
        }
	}
	
	@Override
	public void insertSubMessage(SubMessage_Dto subMsg) 
	{	
		try(Connection conn = DriverManager.getConnection(confDB.getUrlDB(), confDB.getUserDB(), confDB.getPassDB()))
		{
			Statement stmt = conn.createStatement();
            stmt.executeUpdate(messageInsert(subMsg));
            
            logger.info("Se ha registrado el msg: " + subMsg.getIdMessage() + " en la tabla: Tbl_SubMessage");
            
            conn.close();
		}
		catch(SQLException e)
		{
			logger.error("No se pudo guardar el msg: " + subMsg.getIdMessage() + " en la tabla: Tbl_SubMessage");
			logger.error(e.getMessage());
		}
	}

	@Override
	public void insertDevMeasure(DeviceMeasurement_Dto devMeasur) 
	{
		try(Connection conn = DriverManager.getConnection(confDB.getUrlDB(), confDB.getUserDB(), confDB.getPassDB()))
		{
			Statement stmt = conn.createStatement();
            stmt.executeUpdate(measurementInsert(devMeasur));
            
            logger.info("Se ha registrado la medicion: " + devMeasur.getIdMeasurement() + " en la tabla: Tbl_DeviceMeasurement");
            
            conn.close();
		}
		catch(SQLException e)
		{
			logger.error("No se pudo guardar la medicion: " + devMeasur.getIdMeasurement() + " en la tabla: Tbl_DeviceMeasurement");
			logger.error(e.getMessage());
		}
	}
	
	private String measurementInsert (DeviceMeasurement_Dto measure) 
	{
		String query = "INSERT INTO Tbl_DeviceMeasurement \n"
				+ "VALUES ( \n"
				+ measure.getIdDev() + ", \n"
				+ measure.getTimeMeasure() + ", \n"
				+ measure.getPotency() + ", \n"
				+ measure.getVoltage() + ", \n"
				+ measure.getCharge() + ", \n"
				+ measure.getTemperature() + ", \n"
				+ measure.getMisc01() + ", \n"
				+ measure.getMisc02() + ", \n"
				+ measure.getMisc03() + ", \n"
				+ measure.getMisc04() + ", \n"
				+ measure.getMisc05() + ", \n"
				+ measure.getMisc06() + ", \n"
				+ measure.getMisc07() + ", \n"
				+ measure.getMisc08() + ", \n"
				+ measure.getMisc09() + ", \n"
				+ measure.getMisc10() + ", \n"
				+ measure.getMisc11() + ", \n"
				+ measure.getMisc12() + ", \n"
				+ measure.getMisc13() + ", \n"
				+ measure.getMisc14() + ", \n"
				+ measure.getMisc15() + ", \n"
				+ measure.getMisc16() + ", \n"
				+ measure.getMisc17() + ", \n"
				+ measure.getMisc18() + ", \n"
				+ measure.getMisc19() + ", \n"
				+ measure.getMisc20() + " \n"
				+ ")";
		return query;
	}

	private String messageInsert(SubMessage_Dto message) 
	{
		String date = new Date(0).toString();
		
		String query = "INSERT INTO Tbl_SubMessage \n"
				+ "VALUES ( \n"
				+ message.getIdDev() + ", \n"
				+ message.getIdMessage() + ", \n"
				+ message.getReceivedTime() + ", \n"
				+ date + ", \n"
				+ message.getTopic() + ", \n"
				+ message.getMessage() + ", \n"
				+ ")";
		return query;
	}
}
