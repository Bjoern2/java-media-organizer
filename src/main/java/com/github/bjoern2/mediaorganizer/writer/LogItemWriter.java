package com.github.bjoern2.photo.organizer.writer;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.batch.item.ItemWriter;

import com.github.bjoern2.photo.organizer.processor.MediaData;

public class LogItemWriter implements ItemWriter<MediaData> {

//    private final Logger logger = LoggerFactory.getLogger(LogItemWriter.class);
    
    @Override
    public void write(List<? extends MediaData> items) throws Exception {
        for (MediaData item : items) {
//            logger.info(ToStringBuilder.reflectionToString(item, ToStringStyle.SHORT_PREFIX_STYLE));
            System.out.println(ToStringBuilder.reflectionToString(item, ToStringStyle.SHORT_PREFIX_STYLE));
        }
    }
    
}
