package com.github.bjoern2.photo.organizer.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import com.github.bjoern2.photo.organizer.conf.EventXml;
import com.github.bjoern2.photo.organizer.conf.EventsXml;
import com.github.bjoern2.photo.organizer.conf.XMLUtils;

public class EventXmlResolver implements MetaDataResolver {

	private String path;
	
	private EventsXml events;
	
	public void fillMetaData(Path file, MetaData md) {
		if (events == null) {
			readXml();
		}
		
		if (events == null) {
			return;
		}
		
		final List<EventXml> properEvents = new ArrayList<EventXml>();
		for (EventXml e : events.getEvents()) {
			if ((e.getStart().compareTo(md.getRecordingDate()) <= 0) && (e.getEnd().compareTo(md.getRecordingDate()) > 0)) {
				properEvents.add(e);
			}
		}
		
		if (properEvents.size() == 1) {
			md.setEventNameIfNotSet(properEvents.get(0).getName());
		}
		
	}
	
	protected void readXml() {
		InputStream in = null;
		try {
			Path p = Paths.get(this.path);
			in = Files.newInputStream(p);				
			String xml = IOUtils.toString(in);
			events = XMLUtils.unmarshalEventsXml(xml);
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
			return;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

}
