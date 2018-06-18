package library.bean;

import java.io.Serializable;

public class PublisherBean implements Serializable{

	private String publisherId;
	private String publisherName;

	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
}