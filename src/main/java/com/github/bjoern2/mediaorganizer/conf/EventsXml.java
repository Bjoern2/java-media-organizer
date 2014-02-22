package com.github.bjoern2.photo.organizer.conf;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "events")
public class EventsXml {
	
	@XmlElement(name = "event")
	private List<EventXml> events;

	public List<EventXml> getEvents() {
		return events;
	}

	public void setEvents(List<EventXml> events) {
		this.events = events;
	}
	
}
