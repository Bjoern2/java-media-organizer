package com.github.bjoern2.photo.organizer.service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.bjoern2.photo.organizer.duplicate.DuplicateDetector;

@Component
public class DuplicateDetectorService {

	private List<DuplicateDetector> duplicateDetectors;
	
	public DuplicateDetectorService() {
		super();
		duplicateDetectors = new ArrayList<DuplicateDetector>();
	}
	
	public void addDuplicateDetector(DuplicateDetector d) {
		duplicateDetectors.add(d);
	}
	
	public boolean isDuplicate(Path source, Path target) throws Exception {
		for (DuplicateDetector d : duplicateDetectors) {
			if (!d.isDuplicate(source, target)) {
				return false;
			}
		}
		return true;
	}
	
}
