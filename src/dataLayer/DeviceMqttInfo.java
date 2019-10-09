package dataLayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_DeviceMqttInfo")
public class DeviceMqttInfo 
{
	@Id
	@Column(name = "IdDev", unique = true)
	private String IdDev;
	
	@Column(name = "DevMqttUserId", nullable = false)
	private String DevMqttUserId;
	
	@Column(name = "DevMqttUser", nullable = false)
	private String DevMqttUser;
	
	@Column(name = "DevMqttPass", nullable = false)
	private String DevMqttPass;
	
	@Column(name = "QoS", nullable = true)
	private int QoS;
	
	@Column(name = "CleanSession", nullable = true)
	private Boolean CleanSession;
	
	@Column(name = "Persistence", nullable = true)
	private Boolean Persistence;

	public String getIdDev() {
		return IdDev;
	}

	public void setIdDev(String idDev) {
		IdDev = idDev;
	}

	public String getDevMqttUserId() {
		return DevMqttUserId;
	}

	public void setDevMqttUserId(String devMqttUserId) {
		DevMqttUserId = devMqttUserId;
	}

	public String getDevMqttUser() {
		return DevMqttUser;
	}

	public void setDevMqttUser(String devMqttUser) {
		DevMqttUser = devMqttUser;
	}

	public String getDevMqttPass() {
		return DevMqttPass;
	}

	public void setDevMqttPass(String devMqttPass) {
		DevMqttPass = devMqttPass;
	}

	public int getQoS() {
		return QoS;
	}

	public void setQoS(int qoS) {
		QoS = qoS;
	}

	public Boolean getCleanSession() {
		return CleanSession;
	}

	public void setCleanSession(Boolean cleanSession) {
		CleanSession = cleanSession;
	}

	public Boolean getPersistence() {
		return Persistence;
	}

	public void setPersistence(Boolean persistence) {
		Persistence = persistence;
	}
}
