package dataLayer;

import java.util.Date;

public class SubMessage_Dto
{
	private String IdDev;
	private String IdMessage;
	private Date ReceivedTime;
	private Date ProcessedTime;
	private String Topic;
	private String Message;
	private int qos;
	
	public String getIdDev() {
		return IdDev;
	}
	public void setIdDev(String idDev) {
		IdDev = idDev;
	}
	public String getIdMessage() {
		return IdMessage;
	}
	public void setIdMessage(String idMessage) {
		IdMessage = idMessage;
	}
	public Date getReceivedTime() {
		return ReceivedTime;
	}
	public void setReceivedTime(Date receivedTime) {
		ReceivedTime = receivedTime;
	}
	public Date getProcessedTime() {
		return ProcessedTime;
	}
	public void setProcessedTime(Date processedTime) {
		ProcessedTime = processedTime;
	}
	public String getTopic() {
		return Topic;
	}
	public void setTopic(String topic) {
		Topic = topic;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getQos() {
		return qos;
	}
	public void setQos(int qos) {
		this.qos = qos;
	}
}
