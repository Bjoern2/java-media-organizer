package com.github.bjoern2.photo.organizer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.github.bjoern2.photo.organizer.conf.BeanXml;
import com.github.bjoern2.photo.organizer.conf.OrganizerXml;
import com.github.bjoern2.photo.organizer.conf.XMLUtils;
import com.github.bjoern2.photo.organizer.filename.TargetFilenameResolver;
import com.github.bjoern2.photo.organizer.metadata.MetaDataResolver;
import com.github.bjoern2.photo.organizer.service.MetaDataResolverService;
import com.github.bjoern2.photo.organizer.service.TargetFilenameResolverService;
import com.github.bjoern2.photo.organizer.utils.BeanUtils;

public class Application {

	public static void main(String[] args) throws Throwable {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		context.registerShutdownHook();
		
		String configFilePath = System.getProperty("user.dir") + "/conf/organizer.xml";
		Path configFile = Paths.get(configFilePath);
		if (!Files.exists(configFile)) {
			context.close();
			System.exit(1);
			return;
		}
		
		
		InputStream in = Files.newInputStream(configFile);
		String xml = IOUtils.toString(in);
		OrganizerXml config = XMLUtils.unmarshal(xml);
		
		
		TargetFilenameResolverService targetFilenameResolverService = context.getBean(TargetFilenameResolverService.class);
		for (BeanXml bean : config.getTargetFilenameResolvers()) {
			TargetFilenameResolver resolver = BeanUtils.toBean(bean);
			targetFilenameResolverService.addResolver(resolver);
		}
		
		MetaDataResolverService metaDataResolverService = context.getBean(MetaDataResolverService.class);
		for (BeanXml bean : config.getMetaDataResolvers()) {
			MetaDataResolver resolver = BeanUtils.toBean(bean);
			metaDataResolverService.addResolver(resolver);
		}
		
		PhotoMover mover = context.getBean(PhotoMover.class);
		mover.setErrorPath(config.getConfig().getErrorPath());
		
		PhotoOrganizer organizer = context.getBean(PhotoOrganizer.class);
		organizer.setInputPath(config.getConfig().getInputPath());
		try {
			organizer.watch();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		context.close();
		
	}
	
}
