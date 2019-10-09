package dataLayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_DeviceConfig")
public class DeviceConfig 
{
	@Id
	@Column(name = "IdDev", unique = true)
	private String IdDev;
	
	@Column(name = "QoS", nullable = true)
	private int QoS;
	
	@Column(name = "CleanSession", nullable = true)
	private Boolean CleanSession;
	
	@Column(name = "Persistence", nullable = true)
	private Boolean Persistence;
	
	@Column(name = "FrecuencySamplig", nullable = true)
	private int FrecuencySamplig;
	
	@Column(name = "Potency", nullable = true)
	private Boolean Potency;
	
	@Column(name = "Voltage", nullable = true)
	private Boolean Voltage;
	
	@Column(name = "Charge", nullable = true)
	private Boolean Charge;
	
	@Column(name = "Temperature", nullable = true)
	private Boolean Temperature;
	
	@Column(name = "Misc01", nullable = true)
	private Boolean Misc01;
	
	@Column(name = "Misc02", nullable = true)
	private Boolean Misc02;
	
	@Column(name = "Misc03", nullable = true)
	private Boolean Misc03;
	
	@Column(name = "Misc04", nullable = true)
	private Boolean Misc04;
	
	@Column(name = "Misc05", nullable = true)
	private Boolean Misc05;
	
	@Column(name = "Misc06", nullable = true)
	private Boolean Misc06;
	
	@Column(name = "Misc07", nullable = true)
	private Boolean Misc07;
	
	@Column(name = "Misc08", nullable = true)
	private Boolean Misc08;
	
	@Column(name = "Misc09", nullable = true)
	private Boolean Misc09;
	
	@Column(name = "Misc10", nullable = true)
	private Boolean Misc10;
	
	@Column(name = "Misc11", nullable = true)
	private Boolean Misc11;
	
	@Column(name = "Misc12", nullable = true)
	private Boolean Misc12;
	
	@Column(name = "Misc13", nullable = true)
	private Boolean Misc13;
	
	@Column(name = "Misc14", nullable = true)
	private Boolean Misc14;
	
	@Column(name = "Misc15", nullable = true)
	private Boolean Misc15;
	
	@Column(name = "Misc16", nullable = true)
	private Boolean Misc16;
	
	@Column(name = "Misc17", nullable = true)
	private Boolean Misc17;
	
	@Column(name = "Misc18", nullable = true)
	private Boolean Misc18;
	
	@Column(name = "Misc19", nullable = true)
	private Boolean Misc19;
	
	@Column(name = "Misc20", nullable = true)
	private Boolean Misc20;

	public String getIdDev() {
		return IdDev;
	}

	public void setIdDev(String idDev) {
		IdDev = idDev;
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

	public int getFrecuencySamplig() {
		return FrecuencySamplig;
	}

	public void setFrecuencySamplig(int frecuencySamplig) {
		FrecuencySamplig = frecuencySamplig;
	}

	public Boolean getPotency() {
		return Potency;
	}

	public void setPotency(Boolean potency) {
		Potency = potency;
	}

	public Boolean getVoltage() {
		return Voltage;
	}

	public void setVoltage(Boolean voltage) {
		Voltage = voltage;
	}

	public Boolean getCharge() {
		return Charge;
	}

	public void setCharge(Boolean charge) {
		Charge = charge;
	}

	public Boolean getTemperature() {
		return Temperature;
	}

	public void setTemperature(Boolean temperature) {
		Temperature = temperature;
	}

	public Boolean getMisc01() {
		return Misc01;
	}

	public void setMisc01(Boolean misc01) {
		Misc01 = misc01;
	}

	public Boolean getMisc02() {
		return Misc02;
	}

	public void setMisc02(Boolean misc02) {
		Misc02 = misc02;
	}

	public Boolean getMisc03() {
		return Misc03;
	}

	public void setMisc03(Boolean misc03) {
		Misc03 = misc03;
	}

	public Boolean getMisc04() {
		return Misc04;
	}

	public void setMisc04(Boolean misc04) {
		Misc04 = misc04;
	}

	public Boolean getMisc05() {
		return Misc05;
	}

	public void setMisc05(Boolean misc05) {
		Misc05 = misc05;
	}

	public Boolean getMisc06() {
		return Misc06;
	}

	public void setMisc06(Boolean misc06) {
		Misc06 = misc06;
	}

	public Boolean getMisc07() {
		return Misc07;
	}

	public void setMisc07(Boolean misc07) {
		Misc07 = misc07;
	}

	public Boolean getMisc08() {
		return Misc08;
	}

	public void setMisc08(Boolean misc08) {
		Misc08 = misc08;
	}

	public Boolean getMisc09() {
		return Misc09;
	}

	public void setMisc09(Boolean misc09) {
		Misc09 = misc09;
	}

	public Boolean getMisc10() {
		return Misc10;
	}

	public void setMisc10(Boolean misc10) {
		Misc10 = misc10;
	}

	public Boolean getMisc11() {
		return Misc11;
	}

	public void setMisc11(Boolean misc11) {
		Misc11 = misc11;
	}

	public Boolean getMisc12() {
		return Misc12;
	}

	public void setMisc12(Boolean misc12) {
		Misc12 = misc12;
	}

	public Boolean getMisc13() {
		return Misc13;
	}

	public void setMisc13(Boolean misc13) {
		Misc13 = misc13;
	}

	public Boolean getMisc14() {
		return Misc14;
	}

	public void setMisc14(Boolean misc14) {
		Misc14 = misc14;
	}

	public Boolean getMisc15() {
		return Misc15;
	}

	public void setMisc15(Boolean misc15) {
		Misc15 = misc15;
	}

	public Boolean getMisc16() {
		return Misc16;
	}

	public void setMisc16(Boolean misc16) {
		Misc16 = misc16;
	}

	public Boolean getMisc17() {
		return Misc17;
	}

	public void setMisc17(Boolean misc17) {
		Misc17 = misc17;
	}

	public Boolean getMisc18() {
		return Misc18;
	}

	public void setMisc18(Boolean misc18) {
		Misc18 = misc18;
	}

	public Boolean getMisc19() {
		return Misc19;
	}

	public void setMisc19(Boolean misc19) {
		Misc19 = misc19;
	}

	public Boolean getMisc20() {
		return Misc20;
	}

	public void setMisc20(Boolean misc20) {
		Misc20 = misc20;
	}
}
