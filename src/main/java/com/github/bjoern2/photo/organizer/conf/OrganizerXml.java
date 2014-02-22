package com.github.bjoern2.photo.organizer.conf;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organizer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizerXml {

	@XmlElement(required = true)
	private ConfigXml config;
	
	@XmlElementWrapper(name = "metaDataResolvers")
	@XmlElement(name = "bean")
	private List<BeanXml> metaDataResolvers;
	
	@XmlElementWrapper(name = "targetFilenameResolvers")
	@XmlElement(name = "bean")
	private List<BeanXml> targetFilenameResolvers;

	public ConfigXml getConfig() {
		return config;
	}

	public void setConfig(ConfigXml config) {
		this.config = config;
	}

	public List<BeanXml> getMetaDataResolvers() {
		return metaDataResolvers;
	}

	public void setMetaDataResolvers(List<BeanXml> metaDataResolvers) {
		this.metaDataResolvers = metaDataResolvers;
	}

	public List<BeanXml> getTargetFilenameResolvers() {
		return targetFilenameResolvers;
	}

	public void setTargetFilenameResolvers(List<BeanXml> targetFilenameResolvers) {
		this.targetFilenameResolvers = targetFilenameResolvers;
	}
	
}
