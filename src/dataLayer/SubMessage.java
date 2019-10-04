package dataLayer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_SubMessage")
public class SubMessage
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "IdDev", unique = true)
	private String idDev;
	
	@Column(name = "IdMessage", nullable = false)
	private String idMessage;
	
	@Column(name = "ReceivedTime", nullable = false)
	private Date receivedTime;
	
	@Column(name = "ProcessedTime", nullable = false)
	private Date processedTime;
	
	@Column(name = "Topic", nullable = true)
	private String topic;
	
	@Column(name = "Message", nullable = true)
	private String message;

	public String getIdDev() {
		return idDev;
	}

	public void setIdDev(String idDev) {
		this.idDev = idDev;
	}

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public Date getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(Date processedTime) {
		this.processedTime = processedTime;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
