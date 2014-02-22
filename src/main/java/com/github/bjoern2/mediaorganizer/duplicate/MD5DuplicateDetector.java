package com.github.bjoern2.photo.organizer.duplicate;

import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5DuplicateDetector implements DuplicateDetector {

	@Override
	public boolean isDuplicate(Path source, Path target) throws Exception{
		final String sourceMd5 = DigestUtils.md5Hex(Files.newInputStream(source));
		final String targetMd5 = DigestUtils.md5Hex(Files.newInputStream(target));
		return sourceMd5.equals(targetMd5);
	}

	
	
}
