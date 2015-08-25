package com.pixelator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import com.pixelator.image.ImageUtil;
import com.pixelator.image.PixelImage;

public class Main {
	
//	private static int scale = 1;
//	private static Color color = Color.RED;

	public static void main(String[] args) throws IOException {
		new Pixelator();
		
//		final PixelImage image = ImageUtil.load(new File("sprite.png"));
//		
//		
//		final JFrame frame = new JFrame("Pixelator");
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		
//		JComponent panel = new JComponent() {
//			@Override
//			protected void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				image.getScaledInstance(scale).render((Graphics2D)g);
//			}
//		};
//		panel.setPreferredSize(new Dimension(800, 600));
//		frame.add(panel);
//		
//		panel.addMouseWheelListener(new MouseWheelListener() {
//
//			public void mouseWheelMoved(MouseWheelEvent event) {
//				scale += -event.getWheelRotation();
//				scale = scale < 1 ? 1 : scale;
//				frame.repaint();
//			}
//			
//		});
//		
//		panel.addMouseListener(new MouseListener() {
//
//			public void mouseClicked(MouseEvent arg0) { }
//			public void mouseEntered(MouseEvent arg0) { }
//			public void mouseExited(MouseEvent arg0) { }
//
//			public void mousePressed(MouseEvent event) {
//				Point p = new Point((int)(event.getX()/scale), (int)(event.getY()/scale));
//				switch(event.getButton()) {
//				case MouseEvent.BUTTON1 :
//					image.setRGB(p.x, p.y, color.getRGB());
//					frame.repaint();
//					break;
//				case MouseEvent.BUTTON2 :
//					ImageUtil.fill(image, p.x, p.y, color.getRGB());
//					frame.repaint();
//					break;
//				case MouseEvent.BUTTON3 :
//					color = new Color(image.getRGB(p.x, p.y));
//					break;
//				}
//				
//				
//			}
//
//			public void mouseReleased(MouseEvent event) { }
//			
//		});
//		
//		panel.addMouseMotionListener(new MouseMotionListener() {
//
//			public void mouseDragged(MouseEvent event) {
//				Point p = new Point((int)(event.getX()/scale), (int)(event.getY()/scale));
//				image.setRGB(p.x, p.y, color.getRGB());
//				frame.repaint();
//			}
//
//			public void mouseMoved(MouseEvent event) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
//		
//		frame.pack();
//		frame.setVisible(true);
	}
	
	public static void print(Object obj) {
		System.out.println(obj);
	}
	
}
