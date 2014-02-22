package com.github.bjoern2.photo.organizer.metadata;

import java.nio.file.Path;

public interface MetaDataResolver {

	void fillMetaData(Path file, MetaData md);
	
}
