package com.github.bjoern2.photo.organizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.bjoern2.photo.organizer.metadata.MetaData;
import com.github.bjoern2.photo.organizer.service.DuplicateDetectorService;
import com.github.bjoern2.photo.organizer.service.MetaDataResolverService;
import com.github.bjoern2.photo.organizer.service.TargetFilenameResolverService;

@Component
public class PhotoMover {

//	@Autowired
//	private PhotoRepository photoRepository;
	
	@Autowired
	private MetaDataResolverService metaDataResolverService;
	
	@Autowired
	private TargetFilenameResolverService targetFilenameResolverService;
	
	@Autowired
	private DuplicateDetectorService duplicateDetectorService;

	private String errorPath = null;
	
	private final NumberFormat nfCounter = new DecimalFormat("000");
	
	public PhotoMover() {
		super();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void move(Path source, BasicFileAttributes attributes) throws IOException {
		// BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);

		if (!Files.isWritable(source)) {
			return;
		}
		
		final String sourceMd5 = DigestUtils.md5Hex(Files.newInputStream(source));
		// System.out.println(md5);
//		List<Photo> photos = photoRepository.findByHash(sourceMd5);
//		if (!photos.isEmpty()) {
//			System.out.println("Photo already added:");
//			for (Photo p : photos) {
//				// TODO: Hier ein Check, ob die Bilder wirklich noch da sind.
//				System.out.println(p.getPath());
//			}
//			return;
//		}
//		
		
		MetaData metaData = metaDataResolverService.resolveMetaDate(source);

		String targetFilename = targetFilenameResolverService.resolveName(metaData);
		
		
		Path target = Paths.get(targetFilename);
		if (!Files.exists(target.getParent())) {
			Files.createDirectories(target.getParent());
		}
		
		if (Files.exists(target)) {
			final String targetMd5 = DigestUtils.md5Hex(Files.newInputStream(target));
			if (sourceMd5.equals(targetMd5)) {
//				System.out.println("Photo already exists. (" + targetFilename + ")");
//				Photo entity = new Photo();
//				entity.setPath(targetFilename);
//				entity.setHash1(targetMd5);
//				photoRepository.save(entity);
				if (targetFilename == null) {
					Files.delete(source);
				} else {
					Path errorPath = Paths.get(this.errorPath);
					Files.move(source, errorPath.resolve(target.getFileName()));
				}
				return;
			} else {
				target = getAlternateFile(targetFilename);
				target = Files.move(source, target);
//				Photo entity = new Photo();
//				entity.setPath(target.toString());
//				entity.setHash1(sourceMd5);
//				photoRepository.save(entity);
				return;
			}
		} else {
			target = Files.move(source, target);
//			Photo entity = new Photo();
//			entity.setPath(targetFilename);
//			entity.setHash1(sourceMd5);
//			photoRepository.save(entity);
			return;
		}
		
	}
	
	private Path getAlternateFile(String targetFilename) {
		Path target = null;
		int i = 0;
		String newTargetFilename = "";
		do {
			newTargetFilename = FilenameUtils.getFullPath(targetFilename) + FilenameUtils.getBaseName(targetFilename) + "." + nfCounter.format(i) + "." + FilenameUtils.getExtension(targetFilename);
			target = Paths.get(newTargetFilename);
			i++;
		} while (Files.exists(target));
		return target;
	}

	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}
	
}
