package com.pixelator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.pixelator.image.ImageUtil;
import com.pixelator.image.PixelImage;

public class Pixelator extends JFrame {
	private static final long serialVersionUID = 6039984290859271589L;

	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	
	private int color;
	
	public Pixelator() {
		super("Pixelator");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		PixelImage chara = null;
		try {
			chara = ImageUtil.load(new File("mami_run.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		final PixelImage sheet = new PixelImage(chara.getWidth(), chara.getHeight());;// = new PixelImage(256, 128);
		ImageUtil.blit(chara, sheet, 0, 0);
		
//		new Selection(sheet).all().fill(-1);
		
		final SpriteComponent sc = new SpriteComponent(sheet, 70, 55);
		sc.scale(0);
		
		sc.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		this.add(sc);
		
		this.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {
				sc.scale(-e.getWheelRotation());
				repaint();
			}	
			
		});
		
		sc.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) { }
			public void mouseEntered(MouseEvent arg0) { }
			public void mouseExited(MouseEvent arg0) { }

			public void mousePressed(MouseEvent event) {
				Point p = new Point((int)(event.getX()/sc.scale), (int)(event.getY()/sc.scale));
				switch(event.getButton()) {
				case MouseEvent.BUTTON1 :
					sheet.setRGB(p.x, p.y, color);
					repaint();
					break;
				case MouseEvent.BUTTON2 :
					ImageUtil.fill(sheet, p.x, p.y, color);
					repaint();
					break;
				case MouseEvent.BUTTON3 :
					color = sheet.getRGB(p.x, p.y);
					break;
				}
				
				
			}

			public void mouseReleased(MouseEvent event) { }
			
		});
		
		sc.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent event) {
				Point p = new Point((int)(event.getX()/sc.scale), (int)(event.getY()/sc.scale));
				sheet.setRGB(p.x, p.y, color);
				repaint();
			}

			public void mouseMoved(MouseEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		this.pack();
		this.setVisible(true);
	}
	
	private class SpriteComponent extends JComponent {
		private static final long serialVersionUID = 5458052785788603609L;

		private int scale = 1;
		
		private int tileX;
		private int tileY;
		
		private PixelImage sheet;
		
		public SpriteComponent(PixelImage image, int tileX, int tileY) {
			super();
			this.sheet = image;
			this.tileX = tileX;
			this.tileY = tileY;
		}
		
		public void setSheet(PixelImage image) {
			this.sheet = image;
		}
		
		public void scale(int dx) {
			scale += dx;
			if(scale < 1) { 
				scale = 1; 
			}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D)g;
			
			if(sheet != null) {
				sheet.getScaledInstance(scale).render(g2d);
			}
			
			g2d.setColor(Color.black);
			
			for (int x = 0; x <= sheet.getWidth(); x+=tileX) {
				g2d.drawLine(x*scale, 0, x*scale, sheet.getHeight()*scale);
			}
			
			for (int y = 0; y <= sheet.getHeight(); y+=tileY) {
				g2d.drawLine(0, y*scale, sheet.getWidth()*scale, y*scale);
			}
		}
		
	}
	
}
