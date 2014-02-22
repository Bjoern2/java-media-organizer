package com.github.bjoern2.photo.organizer.conf;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BeanXml {

	@XmlAttribute(name = "class", required = true)
	private String clazz;
	
	@XmlElement(name = "property")
	private List<PropertyXml> properties;

	public List<PropertyXml> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyXml> properties) {
		this.properties = properties;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
}
