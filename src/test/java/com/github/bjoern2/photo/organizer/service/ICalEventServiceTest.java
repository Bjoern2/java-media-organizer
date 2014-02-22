package com.github.bjoern2.photo.organizer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ICalEventServiceTest {

	@Test
	public void testLondon() throws Throwable {
		ICalEventService s = new ICalEventService();
		s.setPath("c:/temp/profbulb@googlemail.com.ics");
		String eventName = s.getEventNames(toDate("04.10.2013 13:00:00")).get(0);
		Assert.assertEquals("London", eventName);
	}
	
	@Test
	public void testBirthday() throws Throwable {
		ICalEventService s = new ICalEventService();
		s.setPath("c:/temp/profbulb@googlemail.com.ics");
		String eventName = s.getEventNames(toDate("13.08.2012 13:00:00")).get(0);
		Assert.assertEquals("Geburtstag Björn", eventName);
	}
	
	private Date toDate(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		try {
			return sdf.parse(d);
		} catch (ParseException e) {
			return null;
		}
	}
	
}
