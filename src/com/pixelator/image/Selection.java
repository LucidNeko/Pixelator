package com.pixelator.image;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Selection {
	
	private PixelImage image;
	
	private Set<Pixel> pixels = new HashSet<Pixel>();
	
	
	public Selection(PixelImage image) {
		this.image = image;
	}
	
	public Selection(PixelImage image, Set<Pixel> pixels) {
		this.image = image;
		this.pixels.addAll(pixels);
	}
	
	public Selection all() {
		Selection selection = new Selection(image);
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				selection.pixels.add(new Pixel(x, y));
			}
		}
		return selection;
	}
	
	public Selection select(int rgb) {
		Selection selection = new Selection(image);
		
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				Pixel pixel = new Pixel(x, y);
				if(image.getRGB(x, y) == rgb && this.pixels.contains(pixel)) {
					selection.pixels.add(pixel);
				}
			}
		}
		
		return selection;
	}
	
	public Selection select(int x, int y, boolean contiguous) {
		int sourceRGB = image.getRGB(x, y);
		
		if(!contiguous) {
			return select(sourceRGB);
		}
		
		Selection selection = new Selection(image);
		
		Queue<Pixel> queue = new LinkedList<Pixel>();
		queue.offer(new Pixel(x, y));
		
		while(!queue.isEmpty()) {
			Pixel p = queue.poll();
			
			if(!selection.pixels.contains(p) && p.inBounds(image) && image.getRGB(p.x, p.y) == sourceRGB) {
				selection.pixels.add(p);
				queue.offer(p.left());
				queue.offer(p.right());
				queue.offer(p.up());
				queue.offer(p.down());
			}
		}
		
		return selection;
	}
	
	public Selection select(int minX, int minY, int maxX, int maxY) {
		Selection selection = new Selection(image);
		
		for(int y = minY; y < maxY; y++) {
			for(int x = minX; x < maxX; x++) {
				Pixel p = new Pixel(x, y);
				if(p.inBounds(image) && this.pixels.contains(p)) {
					selection.pixels.add(p);
				}
			}
		}
		
		return selection;
	}
	
	public Selection fill(int rgb) {
		
		for(Pixel p : pixels) {
			image.setRGB(p.x, p.y, rgb);
		}
		
		return this;
	}

}
