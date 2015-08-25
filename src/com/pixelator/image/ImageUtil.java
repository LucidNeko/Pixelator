package com.pixelator.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	public static PixelImage load(File file) throws IOException {
		BufferedImage in = ImageIO.read(file);
		return load(in);
	}
	
	public static PixelImage load(BufferedImage in) {
		PixelImage out = new PixelImage(in.getWidth(), in. getHeight());
		Graphics2D g = out.image.createGraphics();
		g.drawImage(in, null, 0, 0);
		g.dispose();
		return out;
	}
	
	/**
	 * Blit the image onto the canvas from the canvas point (sx, sy)
	 */
	public static void blit(PixelImage image, PixelImage canvas, int sx, int sy) {
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				int dx = sx + x;
				int dy = sy + y;
				
				if(inBounds(canvas, dx, dy)) {
					canvas.setRGB(dx, dy, image.getRGB(x, y));
				}
			}
		}
	}
	
	public static void fill(PixelImage image, int x, int y, int rgb) {
		new Selection(image).all().select(x, y, false).fill(rgb);
	}
	
	public static boolean inBounds(PixelImage image, int x, int y) {
		return x >= 0 && x < image.getWidth() &&
			   y >= 0 && y < image.getHeight();
	}

}
