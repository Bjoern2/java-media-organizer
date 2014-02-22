package com.github.bjoern2.photo.organizer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.github.bjoern2.photo.organizer.filename.TargetFilenameResolver;
import com.github.bjoern2.photo.organizer.metadata.MetaData;

@Component
public class TargetFilenameResolverService {

	private List<TargetFilenameResolver> resolvers;
	
	public TargetFilenameResolverService() {
		super();
		resolvers = new ArrayList<TargetFilenameResolver>();
		//resolvers.add(new GenericTargetFilenameResolver("c:/temp/photo/{yyyy}/{MM}/{basename}.{ext}"));
	}
	
	public void addResolver(TargetFilenameResolver resolver) {
		if (resolver != null) {
			resolvers.add(resolver);
		}
	}
	
	public String resolveName(MetaData metaData) {
		for (TargetFilenameResolver r : resolvers) {
			final String filename = r.getTargetname(metaData);
			if (StringUtils.isNotBlank(filename)) {
				return filename;
			}
			
		}
		return null;
	}
	
}
