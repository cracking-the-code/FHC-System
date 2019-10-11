package storeProcess;

public class JsonGetConfiguration
{
	private int Id;
	private Boolean Active;
	private ParameterObject Parameters;
	private MqttObject Mqtt;
	
	public int getId() { return Id; }
	public void setId(int id) { Id = id; }
	public ParameterObject getParameters() { return Parameters; }
	public MqttObject getMqtt() { return Mqtt; }
	
	public Boolean getActive() { return Active; }
	public void setActive(Boolean active) { Active = active; }
	public void setParameters(ParameterObject parameters) { Parameters = parameters; }
	public void setMqtt(MqttObject mqtt) { Mqtt = mqtt; }

	public class ParameterObject
	{
		private Boolean Power;
		private Boolean Voltage;
		private Boolean Charge;
		private Boolean Temp;
		private Boolean Humidity;
		private int Sample;
		private Boolean Misc01;
		private Boolean Misc02;
		private Boolean Misc03;
		private Boolean Misc04;
		private Boolean Misc05;
		private Boolean Misc06;
		private Boolean Misc07;
		private Boolean Misc08;
		private Boolean Misc09;
		private Boolean Misc10;
		private Boolean Misc11;
		private Boolean Misc12;
		private Boolean Misc13;
		private Boolean Misc14;
		private Boolean Misc15;
		private Boolean Misc16;
		private Boolean Misc17;
		private Boolean Misc18;
		private Boolean Misc19;
		private Boolean Misc20;
		
		public Boolean getPower() { return Power; }
		public Boolean getVoltage() { return Voltage; }
		public Boolean getCharge() { return Charge; }
		public Boolean getTemp() { return Temp; }
		public Boolean getHumidity() { return Humidity; }
		public int getSample() { return Sample; }
		public Boolean getMisc01() { return Misc01; }
		public Boolean getMisc02() { return Misc02; }
		public Boolean getMisc03() { return Misc03; }
		public Boolean getMisc04() { return Misc04; }
		public Boolean getMisc05() { return Misc05; }
		public Boolean getMisc06() { return Misc06; }
		public Boolean getMisc07() { return Misc07; }
		public Boolean getMisc08() { return Misc08; }
		public Boolean getMisc09() { return Misc09; }
		public Boolean getMisc10() { return Misc10; }
		public Boolean getMisc11() { return Misc11; }
		public Boolean getMisc12() { return Misc12; }
		public Boolean getMisc13() { return Misc13; }
		public Boolean getMisc14() { return Misc14; }
		public Boolean getMisc15() { return Misc15; }
		public Boolean getMisc16() { return Misc16; }
		public Boolean getMisc17() { return Misc17; }
		public Boolean getMisc18() { return Misc18; }
		public Boolean getMisc19() { return Misc19; }
		public Boolean getMisc20() { return Misc20; }
		
		public void setPower(Boolean power) { Power = power; }
		public void setVoltage(Boolean voltage) { Voltage = voltage; }
		public void setCharge(Boolean charge) { Charge = charge; }
		public void setTemp(Boolean temp) { Temp = temp; }
		public void setHumidity(Boolean humidity) { Humidity = humidity; }
		public void setSample(int sample) { Sample = sample; }
		public void setMisc01(Boolean misc01) { Misc01 = misc01; }
		public void setMisc02(Boolean misc02) { Misc02 = misc02; }
		public void setMisc03(Boolean misc03) { Misc03 = misc03; }
		public void setMisc04(Boolean misc04) { Misc04 = misc04; }
		public void setMisc05(Boolean misc05) { Misc05 = misc05; }
		public void setMisc06(Boolean misc06) { Misc06 = misc06; }
		public void setMisc07(Boolean misc07) { Misc07 = misc07; }
		public void setMisc08(Boolean misc08) { Misc08 = misc08; }
		public void setMisc09(Boolean misc09) { Misc09 = misc09; }
		public void setMisc10(Boolean misc10) { Misc10 = misc10; }
		public void setMisc11(Boolean misc11) { Misc11 = misc11; }
		public void setMisc12(Boolean misc12) { Misc12 = misc12; }
		public void setMisc13(Boolean misc13) { Misc13 = misc13; }
		public void setMisc14(Boolean misc14) { Misc14 = misc14; }
		public void setMisc15(Boolean misc15) { Misc15 = misc15; }
		public void setMisc16(Boolean misc16) { Misc16 = misc16; }
		public void setMisc17(Boolean misc17) { Misc17 = misc17; }
		public void setMisc18(Boolean misc18) { Misc18 = misc18; }
		public void setMisc19(Boolean misc19) { Misc19 = misc19; }
		public void setMisc20(Boolean misc20) { Misc20 = misc20; }
	}
	
	public class MqttObject
	{
		private int QoS;
		private Boolean CleanSession;
		private Boolean Persistence;
		
		public int getQoS() { return QoS; }
		public Boolean getCleanSession() { return CleanSession; }
		public Boolean getPersistence() { return Persistence; }
		
		public void setQoS(int qoS) { QoS = qoS; }
		public void setCleanSession(Boolean cleanSession) { CleanSession = cleanSession; }
		public void setPersistence(Boolean persistence) { Persistence = persistence; }
	}
}
