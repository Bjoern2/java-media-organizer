package com.github.bjoern2.photo.organizer.conf;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class XmlUtilsTest {

	@Test
	public void testUnmarshal() throws Throwable {
		InputStream in = getClass().getResourceAsStream("/config.xml");
		String xml = IOUtils.toString(in);
		
		OrganizerXml o = XMLUtils.unmarshal(xml);
		
		Assert.assertNotNull(o);
		
		
	}
	
}
