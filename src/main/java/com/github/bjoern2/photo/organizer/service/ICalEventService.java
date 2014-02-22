package com.github.bjoern2.photo.organizer.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.PeriodRule;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Period;

import org.apache.commons.lang.time.DateUtils;

public class ICalEventService {

	private String path;
	
	public ICalEventService() {
		super();
	}
	
	public ICalEventService(String path) {
		super();
		this.path = path;
	}

	public List<String> getEventNames(Date date) {
		List<String> events = new ArrayList<String>();
		try {
			FileInputStream fin = new FileInputStream(path);
			CalendarBuilder builder = new CalendarBuilder();
			Calendar calendar = builder.build(fin);
			
			Date day = DateUtils.truncate(date, java.util.Calendar.DATE);
			
			Period period = new Period(new DateTime(day), new Dur(1, 0, 0, 0));
			Filter filter = new Filter(new PeriodRule[] {new PeriodRule(period)}, Filter.MATCH_ANY);
	
			
			@SuppressWarnings("unchecked")
			Collection<Component> components = filter.filter(calendar.getComponents(Component.VEVENT));
			for (Component component : components) {
				events.add(component.getProperty("SUMMARY").getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return events;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
