package com.github.bjoern2.photo.organizer.duplicate;

import java.nio.file.Path;

import com.github.bjoern2.photo.organizer.utils.ImageUtils;

public class RotateDuplicateDetector implements DuplicateDetector {

	@Override
	public boolean isDuplicate(Path source, Path target) throws Exception {
		return ImageUtils.isRotatedOrEquals(source, target);
	}

}
