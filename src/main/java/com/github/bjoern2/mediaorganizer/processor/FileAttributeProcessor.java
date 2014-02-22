package com.github.bjoern2.photo.organizer.processor;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;

public class FileAttributeProcessor extends AbstractImageProcessor {

    @Override
    public MediaData process(MediaData item) throws Exception {
        try {
            BasicFileAttributes attributes = Files.readAttributes(item.getInputFile().toPath(), BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            FileTime creationTime = attributes.lastModifiedTime();
            Date creationDate = new Date(creationTime.toMillis());
            item.setRecordingDate(creationDate);
            item.setCreationDate(creationDate);
            
            FileTime modifiedTime = attributes.lastModifiedTime();
            Date modifiedDate = new Date(modifiedTime.toMillis());
            item.setModifiedDate(modifiedDate);
            
            String basename = FilenameUtils.getBaseName(item.getInputFile().toPath().toString());
            item.setBasename(basename);
            String ext = FilenameUtils.getExtension(item.getInputFile().toPath().toString());
            item.setExtension(ext);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return item;
    }

}
