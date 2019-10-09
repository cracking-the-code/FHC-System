package dataLayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_DeviceMeasurement")
public class DeviceMeasurement implements Serializable
{
	@Id
	@Column(name = "IdMeasurement", unique = true)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "IdDev", nullable = false)
	private String idDev;
	
	@Column(name = "TimeMeasure", nullable = false)
	private Date timeMeasure;
	
	@Column(name = "Potency", nullable = true)
	private float potency;
	
	@Column(name = "Voltage", nullable = true)
	private float voltage;
	
	@Column(name = "Charge", nullable = true)
	private float charge;
	
	@Column(name = "Temperature", nullable = true)
	private float temperature;
	
	@Column(name = "Misc01", nullable = true)
	private float misc01;
	
	@Column(name = "Misc02", nullable = true)
	private float misc02;
	
	@Column(name = "Misc03", nullable = true)
	private float misc03;
	
	@Column(name = "Misc04", nullable = true)
	private float misc04;
	
	@Column(name = "Misc05", nullable = true)
	private float misc05;
	
	@Column(name = "Misc06", nullable = true)
	private float misc06;
	
	@Column(name = "Misc07", nullable = true)
	private float misc07;
	
	@Column(name = "Misc08", nullable = true)
	private float misc08;
	
	@Column(name = "Misc09", nullable = true)
	private float misc09;
	
	@Column(name = "Misc10", nullable = true)
	private float misc10;
	
	@Column(name = "Misc11", nullable = true)
	private float misc11;
	
	@Column(name = "Misc12", nullable = true)
	private float misc12;
	
	@Column(name = "Misc13", nullable = true)
	private float misc13;
	
	@Column(name = "Misc14", nullable = true)
	private float misc14;
	
	@Column(name = "Misc15", nullable = true)
	private float misc15;
	
	@Column(name = "Misc16", nullable = true)
	private float misc16;
	
	@Column(name = "Misc17", nullable = true)
	private float misc17;
	
	@Column(name = "Misc18", nullable = true)
	private float misc18;
	
	@Column(name = "Misc19", nullable = true)
	private float misc19;
	
	@Column(name = "Misc20", nullable = true)
	private float misc20;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdDev() {
		return idDev;
	}

	public void setIdDev(String idDev) {
		this.idDev = idDev;
	}

	public Date getTimeMeasure() {
		return timeMeasure;
	}

	public void setTimeMeasure(Date timeMeasure) {
		this.timeMeasure = timeMeasure;
	}

	public float getPotency() {
		return potency;
	}

	public void setPotency(float potency) {
		this.potency = potency;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getMisc01() {
		return misc01;
	}

	public void setMisc01(float misc01) {
		this.misc01 = misc01;
	}

	public float getMisc02() {
		return misc02;
	}

	public void setMisc02(float misc02) {
		this.misc02 = misc02;
	}

	public float getMisc03() {
		return misc03;
	}

	public void setMisc03(float misc03) {
		this.misc03 = misc03;
	}

	public float getMisc04() {
		return misc04;
	}

	public void setMisc04(float misc04) {
		this.misc04 = misc04;
	}

	public float getMisc05() {
		return misc05;
	}

	public void setMisc05(float misc05) {
		this.misc05 = misc05;
	}

	public float getMisc06() {
		return misc06;
	}

	public void setMisc06(float misc06) {
		this.misc06 = misc06;
	}

	public float getMisc07() {
		return misc07;
	}

	public void setMisc07(float misc07) {
		this.misc07 = misc07;
	}

	public float getMisc08() {
		return misc08;
	}

	public void setMisc08(float misc08) {
		this.misc08 = misc08;
	}

	public float getMisc09() {
		return misc09;
	}

	public void setMisc09(float misc09) {
		this.misc09 = misc09;
	}

	public float getMisc10() {
		return misc10;
	}

	public void setMisc10(float misc10) {
		this.misc10 = misc10;
	}

	public float getMisc11() {
		return misc11;
	}

	public void setMisc11(float misc11) {
		this.misc11 = misc11;
	}

	public float getMisc12() {
		return misc12;
	}

	public void setMisc12(float misc12) {
		this.misc12 = misc12;
	}

	public float getMisc13() {
		return misc13;
	}

	public void setMisc13(float misc13) {
		this.misc13 = misc13;
	}

	public float getMisc14() {
		return misc14;
	}

	public void setMisc14(float misc14) {
		this.misc14 = misc14;
	}

	public float getMisc15() {
		return misc15;
	}

	public void setMisc15(float misc15) {
		this.misc15 = misc15;
	}

	public float getMisc16() {
		return misc16;
	}

	public void setMisc16(float misc16) {
		this.misc16 = misc16;
	}

	public float getMisc17() {
		return misc17;
	}

	public void setMisc17(float misc17) {
		this.misc17 = misc17;
	}

	public float getMisc18() {
		return misc18;
	}

	public void setMisc18(float misc18) {
		this.misc18 = misc18;
	}

	public float getMisc19() {
		return misc19;
	}

	public void setMisc19(float misc19) {
		this.misc19 = misc19;
	}

	public float getMisc20() {
		return misc20;
	}

	public void setMisc20(float misc20) {
		this.misc20 = misc20;
	}
	
}
