package com.github.bjoern2.photo.organizer.filename;

import com.github.bjoern2.photo.organizer.metadata.MetaData;

public interface TargetFilenameResolver {

	String getTargetname(MetaData metaData);
	
}
