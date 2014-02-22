package com.github.bjoern2.photo.organizer.filename;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.github.bjoern2.photo.organizer.metadata.MetaData;

public class GenericTargetFilenameResolver implements TargetFilenameResolver {

	private final String regexParams = "\\{[A-Za-z0-9]*\\}";
	
	private String path;
	
	public GenericTargetFilenameResolver() {
		super();
	}
	
	public GenericTargetFilenameResolver(String path) {
		super();
		this.path = path;
	}
	
	public String getTargetname(MetaData metaData) {
		String filename = path;
		
		final String[] dates = new String[] {"{yyyy}", "{MM}", "{dd}", "{HH}", "{mm}", "{ss}"};
		if (containsAny(filename, dates) ) {
			if (metaData.getRecordingDate() != null) {
				for (String d : dates) {
					SimpleDateFormat sdf = new SimpleDateFormat(d.substring(1, d.length() -1));
					filename = filename.replace(d, sdf.format(metaData.getRecordingDate()));
				}
			} else {
				return null;
			}
		}
		
		if (filename.contains("{eventname}")) {
			if (StringUtils.isBlank(metaData.getEventName())) {
				return null;
			} else {
				filename = filename.replace("{eventname}", metaData.getEventName());
			}
		}
		
		if (filename.contains("{basename}")) {
			if (StringUtils.isBlank(metaData.getBasename())) {
				return null;
			} else {
				filename = filename.replace("{basename}", metaData.getBasename());
			}
		}
		
		if (filename.contains("{ext}")) {
			if (StringUtils.isBlank(metaData.getExtension())) {
				return null;
			} else {
				filename = filename.replace("{ext}", metaData.getExtension());
			}
		}
		
		Pattern pattern = Pattern.compile(regexParams);
		Matcher matcher = pattern.matcher(filename);
		List<String> requiredParams = new ArrayList<String>();
		while (matcher.find()) {
			String grp = matcher.group();
			
			System.out.println(grp.substring(1, grp.length() - 1));
			requiredParams.add(grp.substring(1, grp.length() - 1));
		}
		
		if (requiredParams.isEmpty()) {
			return filename;
		}
			
		
		return null;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	protected boolean containsAny(String str, String... searchStrings) {
		for (String searchString : searchStrings) {
			if (str.contains(searchString)) {
				return true;
			}
		}
		return false;
	}

}
