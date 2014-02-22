package com.github.bjoern2.photo.organizer.conf;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlDateTimeAdapter extends XmlAdapter<String, Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Date unmarshal(String v) throws Exception {
		return dateFormat.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return dateFormat.format(v);
	}

}
