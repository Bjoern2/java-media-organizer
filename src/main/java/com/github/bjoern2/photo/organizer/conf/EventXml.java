package com.github.bjoern2.photo.organizer.conf;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class EventXml {

	@XmlElement(required = true)
	private String name;
	
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
	private Date start;
	
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
	private Date end;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
