package com.pixelator.image;

public class Pixel {
	
	public int x;
	public int y;

	public Pixel(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pixel left() {
		return new Pixel(x-1, y);
	}
	
	public Pixel right() {
		return new Pixel(x+1, y);
	}
	
	public Pixel up() {
		return new Pixel(x, y-1);
	}
	
	public Pixel down() {
		return new Pixel(x, y+1);
	}
	
	public boolean inBounds(PixelImage image) {
		return x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pixel other = (Pixel) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
