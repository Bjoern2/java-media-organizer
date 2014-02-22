package com.github.bjoern2.photo.organizer.duplicate;

import java.nio.file.Path;

public interface DuplicateDetector {

	boolean isDuplicate(Path source, Path target) throws Exception;
	
}
