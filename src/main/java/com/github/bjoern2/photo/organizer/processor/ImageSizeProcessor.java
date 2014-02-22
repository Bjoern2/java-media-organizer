package com.github.bjoern2.photo.organizer.processor;

import java.awt.Dimension;

import com.github.bjoern2.photo.organizer.utils.ImageUtils;


public class ImageSizeProcessor extends AbstractImageProcessor {

    public ImageSizeProcessor() {
        super();
        fields.add("width");
        fields.add("height");
    }

    @Override
    public MediaData process(MediaData item) throws Exception {
        Dimension size = ImageUtils.getImageSize(item.getInputFile());
        if (size != null) {
            if (fields.contains("width")) {
                item.setWidth((int)size.getWidth());
            }
            if (fields.contains("height")) {
                item.setHeight((int)size.getHeight());
            }
        }
        return item;
    }

}
