package com.github.bjoern2.photo.organizer.metadata;

import java.nio.file.Path;
import java.util.List;

import com.github.bjoern2.photo.organizer.service.ICalEventService;

public class ICalEventNameResolver implements MetaDataResolver {

	private ICalEventService iCalService;
	
	private String path;
	
	public ICalEventNameResolver() {
		this.iCalService = new ICalEventService();
	}
	
	public void fillMetaData(Path file, MetaData md) {
		this.iCalService.setPath(path);
		List<String> eventNames = iCalService.getEventNames(md.getRecordingDate());
		if (eventNames.size() == 1) {
			md.setEventNameIfNotSet(eventNames.get(0));
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

}
