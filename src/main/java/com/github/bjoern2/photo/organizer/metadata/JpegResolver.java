package com.github.bjoern2.photo.organizer.metadata;

import java.nio.file.Path;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class JpegResolver implements MetaDataResolver {

	@Override
	public void fillMetaData(Path file, MetaData md) {
		try {
			fillMetaData2(file, md);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	
	private void fillMetaData2(Path file, MetaData md) throws Exception {
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(file.toFile());
			ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
			
			Date recordingDate = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
			md.setRecordingDateIfNotSet(recordingDate);
			
//			for (Directory directory2 : metadata.getDirectories()) {
//			    for (Tag tag : directory2.getTags()) {
//			        System.out.println(tag);
//			    }
//			}
			
			
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		}
	}

}
