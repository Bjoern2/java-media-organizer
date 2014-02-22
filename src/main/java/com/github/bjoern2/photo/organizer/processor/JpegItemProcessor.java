package com.github.bjoern2.photo.organizer.processor;

import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class JpegItemProcessor extends AbstractImageProcessor {

    public JpegItemProcessor() {
        super();
        fields.add("recordingDate");
    }

    @Override
    public MediaData process(MediaData item) throws Exception {
        if ((fields == null) || (fields.isEmpty())) {
            return item;
        }
        
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(item.getInputFile());
            ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
            
            if (fields.contains("recordingDate")) {
                Date recordingDate = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                item.setRecordingDate(recordingDate);
                
            }
            
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
        return item;
    }

}
