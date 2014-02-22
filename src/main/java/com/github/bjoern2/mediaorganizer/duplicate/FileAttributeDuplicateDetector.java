package com.github.bjoern2.photo.organizer.duplicate;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileAttributeDuplicateDetector implements DuplicateDetector {

	@Override
	public boolean isDuplicate(Path source, Path target) throws Exception{
		BasicFileAttributes sourceAttributes = Files.readAttributes(source, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
		FileTime sourceCreationTime = sourceAttributes.lastModifiedTime();
		
		BasicFileAttributes targetAttributes = Files.readAttributes(source, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
		FileTime targetCreationTime = targetAttributes.lastModifiedTime();
		return sourceCreationTime.equals(targetCreationTime);
	}

}
