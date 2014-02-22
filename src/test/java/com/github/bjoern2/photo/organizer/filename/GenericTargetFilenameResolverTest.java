package com.github.bjoern2.photo.organizer.filename;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.github.bjoern2.photo.organizer.metadata.MetaData;

public class GenericTargetFilenameResolverTest {

	@Test
	public void testGetFilename() throws Throwable {
		GenericTargetFilenameResolver r = new GenericTargetFilenameResolver();
		r.setPath("c:/test/{yyyy}/{MM}/{yyyy}{MM}{dd}{HH}{mm}{ss}.{ext}");
		
		
		MetaData md = new MetaData();
		md.setExtension("jpg");
		md.setRecordingDate(DateUtils.parseDate("13.08.1983", new String[] {"dd.MM.yyyy"}));
		String filename = r.getTargetname(md);
		
		Assert.assertEquals("c:/test/1983/08/19830813000000.jpg", filename);
		
		
	}
	
}
