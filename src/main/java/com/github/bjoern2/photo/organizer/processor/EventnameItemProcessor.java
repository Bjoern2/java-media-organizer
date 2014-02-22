package com.github.bjoern2.photo.organizer.processor;

import java.util.Date;
import java.util.List;

public class EventnameItemProcessor extends AbstractImageProcessor {

    protected List<Event> events; 
    
    @Override
    public MediaData process(MediaData item) throws Exception {
        if (events == null) {
            return item;
        }
        
        final Date recordingDate = item.getRecordingDate();
        for (Event event : events) {
            if ((event.getStart().compareTo(recordingDate) <= 0) && (event.getEnd().compareTo(recordingDate) > 0)) {
                item.setEventName(event.getName());
                return item;
            }
        }
        return item;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
