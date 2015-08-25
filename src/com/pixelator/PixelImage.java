package com.pixelator;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PixelImage {
	
	private BufferedImage underlyingImage;
	private BufferedImage scaledImage;
	
	private float scale = 1;
	
	public PixelImage(File file) throws IOException {
		this(ImageIO.read(file));
	}
	
	public PixelImage(BufferedImage image) {
		underlyingImage = deepCopy(image);
		scaledImage = deepCopy(image);
	}
	
	private Point scalePointToImage(int x, int y) {
		return new Point((int)(x/scale), (int)(y/scale));
	}
	
	public int getRGB(int x, int y) {
		Point point = scalePointToImage(x, y);
		return underlyingImage.getRGB(point.x, point.y);
	}
	
	public void setRGB(int x, int y, int rgb) {
		Point point = scalePointToImage(x, y);
		underlyingImage.setRGB(point.x, point.y, rgb);
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	//TODO: Is slow.
	public Image getImage() {
		scaledImage = new BufferedImage((int)(underlyingImage.getWidth()*scale), (int)(underlyingImage.getHeight()*scale), underlyingImage.getType());
		
		for(int y = 0; y < scaledImage.getHeight(); y++) {
			for(int x = 0; x < scaledImage.getWidth(); x++) {
				scaledImage.setRGB(x, y, underlyingImage.getRGB((int)(x/scale), (int)(y/scale)));
			}
		}
		
		return scaledImage;
	}
	
	private static BufferedImage deepCopy(BufferedImage bi) {
	    ColorModel cm = bi.getColorModel();
	    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
	    WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
	    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

}
