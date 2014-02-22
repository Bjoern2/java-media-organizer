package com.github.bjoern2.photo.organizer.conf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigXml {
	
	@XmlElement
	private boolean deleteDuplicates = true;
	
	@XmlElement(required = true)
	private String inputPath;
	
	@XmlElement(required = true)
	private String errorPath;

	public boolean isDeleteDuplicates() {
		return deleteDuplicates;
	}

	public void setDeleteDuplicates(boolean deleteDuplicates) {
		this.deleteDuplicates = deleteDuplicates;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getErrorPath() {
		return errorPath;
	}

	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}
	
	

	
}
