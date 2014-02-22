package com.github.bjoern2.photo.organizer.metadata;

import java.awt.Dimension;
import java.nio.file.Path;

import com.github.bjoern2.photo.organizer.utils.ImageUtils;

public class ImageSizeResolver implements MetaDataResolver {

	public void fillMetaData(Path file, MetaData md) {
		Dimension size = ImageUtils.getImageSize(file);
		if (size != null) {
			md.setWidthIfNotSet((int)size.getWidth());
			md.setHeightIfNotSet((int)size.getHeight());
		}
		
	}


}
