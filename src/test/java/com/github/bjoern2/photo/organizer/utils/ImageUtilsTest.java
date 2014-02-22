package com.github.bjoern2.photo.organizer.utils;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ImageUtilsTest {

	@Ignore
	@Test
	public void testRotate() throws IOException {
		Path file000 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		String hash000 = DigestUtils.md5Hex(Files.newInputStream(file000));
		
		Path file090 = Paths.get("C:/temp/photo/todo/SL372205_090.JPG");
		ImageUtils.rotateClockwise(file000, file090);
		String hash090 = DigestUtils.md5Hex(Files.newInputStream(file090));
		
		Path file180 = Paths.get("C:/temp/photo/todo/SL372205_180.JPG");
		ImageUtils.rotateClockwise(file090, file180);
		String hash180 = DigestUtils.md5Hex(Files.newInputStream(file180));
		
		Path file270 = Paths.get("C:/temp/photo/todo/SL372205_270.JPG");
		ImageUtils.rotateClockwise(file180, file270);
		String hash270 = DigestUtils.md5Hex(Files.newInputStream(file270));
		
		Path file360 = Paths.get("C:/temp/photo/todo/SL372205_360.JPG");
		ImageUtils.rotateClockwise(file270, file360);
		String hash360 = DigestUtils.md5Hex(Files.newInputStream(file360));
		
		Assert.assertEquals(hash000, hash360);
		
	}
	
	@Test
	public void testIsEqualsOrRotated000() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		boolean result = ImageUtils.isRotatedOrEquals(file1, file2);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEquals000() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		BufferedImage img1 = ImageIO.read(file1.toFile());
		BufferedImage img2 = ImageIO.read(file2.toFile());
		boolean result = ImageUtils.isEquals(img1, img2, Rotation.NO);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEqualsOrRotated090() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205_90.JPG");
		boolean result = ImageUtils.isRotatedOrEquals(file1, file2);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEquals090() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205_90.JPG");
		BufferedImage img1 = ImageIO.read(file1.toFile());
		BufferedImage img2 = ImageIO.read(file2.toFile());
		boolean result = ImageUtils.isEquals(img1, img2, Rotation.CLOCKWISE);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEqualsOrRotated180() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205_180.JPG");
		boolean result = ImageUtils.isRotatedOrEquals(file1, file2);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEquals180() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205_180.JPG");
		BufferedImage img1 = ImageIO.read(file1.toFile());
		BufferedImage img2 = ImageIO.read(file2.toFile());
		boolean result = ImageUtils.isEquals(img1, img2, Rotation.UPSIDE_DOWN);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEqualsOrRotated270() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205_270.JPG");
		boolean result = ImageUtils.isRotatedOrEquals(file1, file2);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEquals270() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372205_270.JPG");
		BufferedImage img1 = ImageIO.read(file1.toFile());
		BufferedImage img2 = ImageIO.read(file2.toFile());
		boolean result = ImageUtils.isEquals(img1, img2, Rotation.ANTICLOCKWISE);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsEqualsNegative() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372206.JPG");
		BufferedImage img1 = ImageIO.read(file1.toFile());
		BufferedImage img2 = ImageIO.read(file2.toFile());
		boolean result = ImageUtils.isEquals(img1, img2, Rotation.NO);
		Assert.assertFalse(result);
	}
	
	@Test
	public void testIsEqualsOrRotatedNegative() throws IOException {
		Path file1 = Paths.get("C:/temp/photo/todo/SL372205.JPG");
		Path file2 = Paths.get("C:/temp/photo/todo/SL372206.JPG");
		boolean result = ImageUtils.isRotatedOrEquals(file1, file2);
		Assert.assertFalse(result);
	}
	
	@Test
	public void testGetSize() throws Throwable {
		Path file1 = Paths.get("C:/Users/bjoern/Pictures/DCIM/100SSCAM/SL372205.JPG");
		Dimension size = ImageUtils.getImageSize(file1);
		
		Assert.assertEquals(new Dimension(3072, 2304), size);
	}
	
	
}
