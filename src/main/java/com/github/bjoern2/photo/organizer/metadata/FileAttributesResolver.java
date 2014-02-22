package com.github.bjoern2.photo.organizer.metadata;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;

public class FileAttributesResolver implements MetaDataResolver {

	public void fillMetaData(Path file, MetaData md) {
		try {
			BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
			FileTime creationTime = attributes.lastModifiedTime();
			Date creationDate = new Date(creationTime.toMillis());
			md.setRecordingDateIfNotSet(creationDate);
			md.setCreationDateIfNotSet(creationDate);
			
			FileTime modifiedTime = attributes.lastModifiedTime();
			Date modifiedDate = new Date(modifiedTime.toMillis());
			md.setModifiedDateIfNotSet(modifiedDate);
			
			String basename = FilenameUtils.getBaseName(file.toString());
			md.setBasename(basename);
			String ext = FilenameUtils.getExtension(file.toString());
			md.setExtension(ext);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

