package dataLayer;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import infraLayer.ConfigDB;

public class SpMySQL implements SpPersistence
{
	private static Logger logger = LogManager.getLogger(SpMySQL.class);
	private ConfigDB confDB = ConfigDB.getInstance();
	
	
	public SpMySQL() 
	{
		
	}
	
	@Override
	public void insertSubMessage(SubMessage_Dto subMsg) 
	{	
		try(Connection con = DriverManager.getConnection(confDB.getUrlDB(), confDB.getUserDB(), confDB.getPassDB()))
		{
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(messageInsert(subMsg));
            {
            	if (rs.next())
            	{
            	}
                
                System.out.println(rs.getString(1));
            }
		}
		catch(SQLException e)
		{
			logger.error("");
		}
	}

	@Override
	public void insertDevMeasure(DeviceMeasurement_Dto devMeasur) 
	{
		
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
		String query = "INSERT INTO Tbl_SubMessage \n"
				+ "VALUES ( \n"
				+ message.getIdDev() + ", \n"
				+ message.getIdMessage() + ", \n"
				+ message.getReceivedTime() + ", \n"
				+ message.getProcessedTime() + ", \n"
				+ message.getTopic() + ", \n"
				+ message.getMessage() + ", \n"
				+ ")";
		return query;
	}
}
