package com.github.bjoern2.photo.organizer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoOrganizer {

	@Autowired
	private PhotoMover photoMover;
	
	private String inputPath;
	
	public static void main(String[] args) {
		PhotoOrganizer organizer = new PhotoOrganizer();
		try {
			organizer.watch();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void watch() throws IOException {
//		WatchService watcher = FileSystems.getDefault().newWatchService();
		Path todo = Paths.get(inputPath);
//		todo.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
		while (true) {
			Files.walkFileTree(todo, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					super.visitFile(file, attrs);
					try {
						photoMover.move(file, attrs);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					return FileVisitResult.CONTINUE;
				}
				
			});
		}
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
}
