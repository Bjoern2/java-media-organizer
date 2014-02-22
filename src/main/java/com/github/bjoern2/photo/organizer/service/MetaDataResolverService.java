package com.github.bjoern2.photo.organizer.service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.bjoern2.photo.organizer.metadata.MetaData;
import com.github.bjoern2.photo.organizer.metadata.MetaDataResolver;

@Component
public class MetaDataResolverService {

	private List<MetaDataResolver> resolvers;
	
	public MetaDataResolverService() {
		super();
		this.resolvers = new ArrayList<MetaDataResolver>();
//		metaDataResolvers.add(new JpegResolver());
//		metaDataResolvers.add(new ImageSizeResolver());
//		metaDataResolvers.add(new FileAttributesResolver());
	}
	
	public void addResolver(MetaDataResolver resolver) {
		if (resolver != null) {
			resolvers.add(resolver);
		}
	}

	public MetaData resolveMetaDate(Path file) {
		MetaData md = new MetaData();
		for (MetaDataResolver resolver : resolvers) {
			resolver.fillMetaData(file, md);
		}
		return md;
	}
	
}
