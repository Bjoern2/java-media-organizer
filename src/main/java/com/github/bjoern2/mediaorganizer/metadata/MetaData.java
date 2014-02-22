package com.github.bjoern2.photo.organizer.metadata;

import java.util.Date;

public class MetaData {

	private String basename;
	private String extension;
	private int width;
	private int height;
	private Date recordingDate;
	private Date creationDate;
	private Date modifiedDate;
	private String eventName;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setWidthIfNotSet(int width) {
		if (this.width <= 0) {
			this.width = width;
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setHeightIfNotSet(int height) {
		if (this.height <= 0) {
			this.height = height;
		}
	}

	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}
	
	public void setRecordingDateIfNotSet(Date recordingDate) {
		if (this.recordingDate == null) {
			this.recordingDate = recordingDate;
		}
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public void setCreationDateIfNotSet(Date creationDate) {
		if (this.creationDate == null) {
			this.creationDate = creationDate;
		}
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public void setModifiedDateIfNotSet(Date modifiedDate) {
		if (this.modifiedDate == null) {
			this.modifiedDate = modifiedDate;
		}
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public void setEventNameIfNotSet(String eventName) {
		if (this.eventName == null || this.eventName.isEmpty()) {
			this.eventName = eventName;
		}
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
