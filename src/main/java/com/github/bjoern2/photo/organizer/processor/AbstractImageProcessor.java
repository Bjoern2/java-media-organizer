package com.github.bjoern2.photo.organizer.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

public abstract class AbstractImageProcessor implements ItemProcessor<MediaData, MediaData> {

    protected List<String> fields = new ArrayList<String>();

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

}
