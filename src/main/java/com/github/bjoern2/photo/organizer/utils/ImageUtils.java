package com.github.bjoern2.photo.organizer.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class ImageUtils {

	public static void rotateClockwise(Path source, Path target) throws IOException {
		BufferedImage srcImage = ImageIO.read(source.toFile());
//		AffineTransform affineTransform = AffineTransform.getRotateInstance(Math.toRadians(90), srcImage.getWidth() / 2, srcImage.getHeight() / 2);
//		BufferedImage targetImage = new BufferedImage(srcImage.getHeight(), srcImage.getWidth(), srcImage.getType());
//		Graphics2D g = (Graphics2D) targetImage.getGraphics();
//		g.setTransform(affineTransform);
//		g.drawImage(srcImage, 0, 0, null);
		
		
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		BufferedImage targetImage = new BufferedImage(height, width, srcImage.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++ ) {
				targetImage.setRGB(y, (width - x - 1), srcImage.getRGB(x, y));
			}
		}
		
		ImageIO.write(targetImage, "jpeg", target.toFile());
	}
	
	
	public static boolean isRotatedOrEquals(Path img1Path, Path img2Path) throws IOException {
		BufferedImage img1 = ImageIO.read(img1Path.toFile());
		BufferedImage img2 = ImageIO.read(img2Path.toFile());
		
		int w1 = img1.getWidth();
		int h1 = img1.getHeight();
		int w2 = img2.getWidth();
		int h2 = img2.getHeight();

		if (!(((w1 == w2) && (h1 == h2)) || ((w1 == h2) && (w2 == h1)))) {
			return false;
		}
		
		// TODO: Add here a performance optimization.
		//for (int i = 0; i < 4; i++) {
		for (Rotation r : Rotation.values()) {
			if (isEquals(img1, img2, r)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isEquals(BufferedImage img1, BufferedImage img2, Rotation rotation) {
		final int w1 = img1.getWidth();
		final int h1 = img1.getHeight();
		final int w2 = img2.getWidth();
		final int h2 = img2.getHeight();
		
		if ((rotation == Rotation.NO) || (rotation == Rotation.UPSIDE_DOWN)) {
			if (w1 != w2) {
				return false;
			}
			if (h1 != h2) {
				return false;
			}
		} else if ((rotation == Rotation.CLOCKWISE) || (rotation == Rotation.ANTICLOCKWISE)) {
			if (w1 != h2) {
				return false;
			}
			if (h1 != w2) {
				return false;
			}
		} else {
			return false;
		}
		
		for (int x1 = 0; x1 < w1; x1 = x1 + 1) {
			for (int y1 = 0; y1 < h1; y1= y1 + 1) {
				final Color color1 = new Color(img1.getRGB(x1, y1), true);
				int x2;
				int y2;
				if (rotation == Rotation.NO) {
					x2 = x1;
					y2 = y1;
				} else if (rotation == Rotation.CLOCKWISE) {
					x2 = h1 - y1 - 1;
					y2 = x1;
				}  else if (rotation == Rotation.UPSIDE_DOWN) {
					x2 = w1 - x1 - 1;
					y2 = h1 - y1 - 1;
				} else if (rotation == Rotation.ANTICLOCKWISE) {
					x2 = y1;
					y2 = w1 - x1 - 1;
				} else {
					return false;
				}
				
				final Color color2 = new Color(img2.getRGB(x2, y2), true);
				if (!colorsEquals(color1, color2)) {
//					System.out.println("Not equals: " + x + "|" + y + " -> " + x2 + "|" + y2);
//					System.out.println(color1);
//					System.out.println(color2);
					return false;
				}
				
			}
		}
		return true;
	}
	
	
	public static boolean colorsEquals(Color c1, Color c2) {
		if (Math.abs(c1.getRed() - c2.getRed()) > 15) {
			return false;
		}
		
		if (Math.abs(c1.getGreen() - c2.getGreen()) > 15) {
			return false;
		}
		
		if (Math.abs(c1.getBlue() - c2.getBlue()) > 15) {
			return false;
		}
		
		if (Math.abs(c1.getAlpha() - c2.getAlpha()) > 15) {
			return false;
		}
		
		return true;
//		return ((c1.getRed() == c2.getRed()) && (c1.getGreen() == c2.getGreen()) && (c1.getBlue() == c2.getBlue()));
	}
	
	public static Dimension getImageSize(File file) {
	    return getImageSize(file.toPath());
	}
	
	public static Dimension getImageSize(Path file) {
		String ext = FilenameUtils.getExtension(file.toString());
	    Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(ext);
	    if (iter.hasNext()) {
	    	ImageInputStream stream = null;
	        ImageReader reader = iter.next();
	        try {
	        	stream = new FileImageInputStream(file.toFile());
	            reader.setInput(stream);
	            int width = reader.getWidth(reader.getMinIndex());
	            int height = reader.getHeight(reader.getMinIndex());
	           return new Dimension(width, height);
	        } catch (IOException e) {
//	            log(e.getMessage());
	        	e.printStackTrace();
	        } finally {
	            reader.dispose();
	            IOUtils.closeQuietly(stream);
	        }
	    } else {
//	        log("No reader found for given format: " + ext));
	    }
	    return null;
	}
}

