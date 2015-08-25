package com.pixelator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

public class Main {
	
	private static float scale = 1;
	private static Color color = Color.RED;

	public static void main(String[] args) throws IOException {
		final PixelImage image = new PixelImage(new File("sprite.png"));
		
		final JFrame frame = new JFrame("Pixelator");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JComponent panel = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getImage(), 0, 0, null);
			}
		};
		panel.setPreferredSize(new Dimension(800, 600));
		frame.add(panel);
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				switch(event.getKeyCode()) {
				case KeyEvent.VK_O :
					scale = scale > 1 ? scale-1 : 1;
					print(scale);
					image.setScale(scale);
					frame.repaint();
					break;
				case KeyEvent.VK_P :
					image.setScale(scale++);
					frame.repaint();
					break;
				}
			}
		});
		
		panel.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent event) {
				scale += event.getWheelRotation()*0.25f;
				scale = scale < 1 ? 1 : scale;
				image.setScale(scale);
				frame.repaint();
			}
			
		});
		
		panel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) { }
			public void mouseEntered(MouseEvent arg0) { }
			public void mouseExited(MouseEvent arg0) { }

			public void mousePressed(MouseEvent event) {
				switch(event.getButton()) {
				case MouseEvent.BUTTON1 :
					image.setRGB(event.getX(), event.getY(), color.getRGB());
					frame.repaint();
					break;
				case MouseEvent.BUTTON3 :
					color = new Color(image.getRGB(event.getX(), event.getY()));
					break;
				}
				
				
			}

			public void mouseReleased(MouseEvent event) { }
			
		});
		
		panel.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent event) {
				image.setRGB(event.getX(), event.getY(), color.getRGB());
				frame.repaint();
			}

			public void mouseMoved(MouseEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void print(Object obj) {
		System.out.println(obj);
	}
	
}
