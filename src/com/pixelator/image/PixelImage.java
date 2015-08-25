package com.pixelator.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class PixelImage implements Cloneable {
	
	private int width;
	private int height;
	
	//package private
	BufferedImage image;
	int[] data;
	
	public PixelImage(int width, int height) {
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.width = width;
		this.height = height;
		
		data = ((DataBufferInt)(image.getRaster().getDataBuffer())).getData();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getRGB(int x, int y) {
		return data[y*width + x];
	}
	
	public void setRGB(int x, int y, int rgb) {
		data[y*width + x] = rgb;
	}
	
	public PixelImage getScaledInstance(int scale) {
		assert(scale > 0);
		
		PixelImage in = this;
		PixelImage out = new PixelImage(in.width*scale, in.height*scale);
		
		for(int y = 0; y < out.height; y++) {
			for(int x = 0; x < out.width; x++) {
				out.setRGB(x, y, in.getRGB(x/scale, y/scale));
			}
		}
		
		return out;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, null, 0, 0);
	}
	
	public PixelImage clone() {
		PixelImage out = new PixelImage(width, height);
		for(int i = 0; i < data.length; i++) {
			out.data[i] = data[i];
		}
		return out;
	}
	
}
