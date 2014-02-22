package com.github.bjoern2.photo.organizer.writer;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ItemWriter;

import com.github.bjoern2.photo.organizer.processor.MediaData;

public class PhotoWriter implements ItemWriter<MediaData> {

    private boolean overwrite = false;
    private String option = "move";

    @Override
    public void write(List<? extends MediaData> items) throws Exception {
        for (MediaData item : items) {
            if (item.getOutputFile().exists()) {
                if (overwrite) {
                    if (StringUtils.equalsIgnoreCase("move", option)) {
                        Files.move(item.getInputFile().toPath(), item.getOutputFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        Files.copy(item.getInputFile().toPath(), item.getOutputFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            } else {
                if (StringUtils.equalsIgnoreCase("move", option)) {
                    Files.move(item.getInputFile().toPath(), item.getOutputFile().toPath());
                } else {
                    Files.copy(item.getInputFile().toPath(), item.getOutputFile().toPath());
                }
            }
        }
        
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
    
}
