package com.github.bjoern2.photo.organizer.conf;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLUtils {

	public static String marshal(OrganizerXml job) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(OrganizerXml.class); 
		Marshaller m = context.createMarshaller(); 
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); 
		StringWriter out = new StringWriter();
		m.marshal(job, out);
		return out.toString();
	}
	
	public static OrganizerXml unmarshal(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(OrganizerXml.class); 
		Unmarshaller m = context.createUnmarshaller();
		OrganizerXml o = (OrganizerXml)m.unmarshal(new StringReader(xml));
		return o;
	}
	
	public static String marshalEventsXml(EventsXml o) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(EventsXml.class); 
		Marshaller m = context.createMarshaller(); 
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); 
		StringWriter out = new StringWriter();
		m.marshal(o, out);
		return out.toString();
	}
	
	public static EventsXml unmarshalEventsXml(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(EventsXml.class); 
		Unmarshaller m = context.createUnmarshaller();
		EventsXml o = (EventsXml)m.unmarshal(new StringReader(xml));
		return o;
	}
	
}
