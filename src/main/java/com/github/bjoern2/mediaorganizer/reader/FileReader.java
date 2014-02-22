package com.github.bjoern2.photo.organizer.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import com.github.bjoern2.photo.organizer.processor.MediaData;

public class FileReader extends AbstractItemCountingItemStreamItemReader<MediaData> {

    private String baseDir;
    
    private List<String> fileExtensions;
    
    private final List<Path> paths = new ArrayList<Path>();

    public FileReader() {
        super();
        setName("fileReader");
    }

    @Override
    protected MediaData doRead() throws Exception {
        MediaData d = new MediaData();
        d.setInputFile(paths.get(getCurrentItemCount() - 1).toFile());
        return d;
    }

    @Override
    protected void doOpen() throws Exception {
        File baseDir = new File(this.baseDir);
        Files.walkFileTree(baseDir.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                for (String ext : fileExtensions) {
                    if (FilenameUtils.getExtension(file.toString()).equalsIgnoreCase(ext)) {
                        paths.add(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
            
        });
        
        setMaxItemCount(paths.size());
        setCurrentItemCount(0);
    }

    @Override
    protected void doClose() throws Exception {
        // do nothing.
        
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public List<String> getFileExtensions() {
        return fileExtensions;
    }

    public void setFileExtensions(List<String> fileExtensions) {
        this.fileExtensions = fileExtensions;
    }

}
