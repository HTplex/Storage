/* *********************************************************************** *
 * project: org.matsim.*
 * Controler.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2011 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package playground.sergioo.busRoutesVisualizer2011.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Node;

import others.sergioo.util.geometry.Point2D;

import playground.sergioo.busRoutesVisualizer2011.gui.Window.Option;

public class PanelPathEditor extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Attributes
	private Camera camera;
	private Window window;
	private Color backgroundColor = Color.WHITE;
	private Color selectedColor = Color.RED;
	private Color nodeSelectedColor = Color.MAGENTA;
	private Color networkColor = Color.LIGHT_GRAY;
	private int pointsSize = 4;
	private Stroke selectedStroke = new BasicStroke(2.5f);
	private Stroke networkStroke = new BasicStroke(1.5f);
	private int iniX;
	private int iniY;
	private boolean withStops = true;
	private boolean withLinks = true;
	private boolean withNetwork = true;
	private double xMax;
	private double yMax;
	private double xMin;
	private double yMin;
	
	//Methods
	public PanelPathEditor(Window window) {
		this.window = window;
		this.setBackground(backgroundColor);
		camera = new Camera();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}
	public void calculateBoundaries() {
		xMin=Double.POSITIVE_INFINITY; yMin=Double.POSITIVE_INFINITY; xMax=Double.NEGATIVE_INFINITY; yMax=Double.NEGATIVE_INFINITY;
		for(Set<Link> links:window.getLinks()) {
			for(Link link:links)
				if(link!=null) {
					if(link.getFromNode().getCoord().getX()<xMin)
						xMin = link.getFromNode().getCoord().getX();
					if(link.getFromNode().getCoord().getX()>xMax)
						xMax = link.getFromNode().getCoord().getX();
					if(link.getFromNode().getCoord().getY()<yMin)
						yMin = link.getFromNode().getCoord().getY();
					if(link.getFromNode().getCoord().getY()>yMax)
						yMax = link.getFromNode().getCoord().getY();
					if(link.getToNode().getCoord().getX()<xMin)
						xMin = link.getToNode().getCoord().getX();
					if(link.getToNode().getCoord().getX()>xMax)
						xMax = link.getToNode().getCoord().getX();
					if(link.getToNode().getCoord().getY()<yMin)
						yMin = link.getToNode().getCoord().getY();
					if(link.getToNode().getCoord().getY()>yMax)
						yMax = link.getToNode().getCoord().getY();
				}
		}
		setBoundaries();
	}
	private void setBoundaries() {
		double aspectRatioPanel = camera.setAspectRatio(getWidth(), getHeight());
		double aspectRatioWorld = (xMax-xMin)/(yMax-yMin);
		double cXMin = xMin, cYMin = yMin, cXMax = xMax, cYMax = yMax;
		if(aspectRatioWorld>aspectRatioPanel) {
			cXMin=xMin;
			cXMax=xMax;
			cYMin=yMin+(yMax-yMin)/2-((xMax-xMin)/aspectRatioPanel)/2;
			cYMax=yMax-(yMax-yMin)/2+((xMax-xMin)/aspectRatioPanel)/2;
		}
		else if(aspectRatioWorld<aspectRatioPanel){
			cYMin=yMin;
			cYMax=yMax;
			cXMin=xMin+(xMax-xMin)/2-((yMax-yMin)*aspectRatioPanel)/2;
			cXMax=xMax-(xMax-xMin)/2+((yMax-yMin)*aspectRatioPanel)/2;
		}
		camera.setBoundaries(cXMin, cYMin, cXMax, cYMax);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		if(withNetwork)
			paintNetwork(g2);
		if(withLinks)
			paintLines(g2);
		if(withStops)
			paintPoints(g2);
		paintSelected(g2);
	}
	private void paintPoints(Graphics2D g2) {
		Color[] colors = new Color[]{new Color(255,255,0),new Color(255,0,0),new Color(0,0,255)};
		Collection<Coord>[] allStops = window.getStopPoints();
		for(int k=allStops.length-1; k>=0; k--) {
			g2.setStroke(new BasicStroke(allStops.length-k+2));
			Collection<Coord> points=allStops[k];
			g2.setColor(colors[k]);
			for(Coord point:points) {
				g2.drawLine(camera.getIntX(point.getX())-2*pointsSize, camera.getIntY(point.getY()), camera.getIntX(point.getX())+2*pointsSize, camera.getIntY(point.getY()));
				g2.drawLine(camera.getIntX(point.getX()), camera.getIntY(point.getY())-2*pointsSize, camera.getIntX(point.getX()), camera.getIntY(point.getY())+2*pointsSize);
			}
		}
	}
	private void paintNetwork(Graphics2D g2) {
		g2.setColor(networkColor);
		g2.setStroke(networkStroke);
		for(Link link:window.getNetworkLinks(camera.getUpLeftCorner().getX(),camera.getUpLeftCorner().getY()+camera.getSize().getY(),camera.getUpLeftCorner().getX()+camera.getSize().getX(),camera.getUpLeftCorner().getY()))
			paintLink(link,g2);
	}
	private void paintLines(Graphics2D g2) {
		Set<Link>[] allLinks = window.getLinks();
		Color[] colors = new Color[]{new Color(127,127,0),new Color(127,0,0),new Color(0,0,127)};
		for(int k=allLinks.length-1; k>=0; k--) {
			Set<Link> links = allLinks[k];
			g2.setColor(colors[k]);
			g2.setStroke(new BasicStroke(allLinks.length-k+2));
			for(Link link:links)
				if(link!=null)
					paintLink(link, g2);
		}
	}
	private void paintSelected(Graphics2D g2) {
		g2.setColor(selectedColor);
		g2.setStroke(selectedStroke);
		if(withStops) {		
			Coord stop=window.getSelectedStop();
			if(stop!=null) {
				g2.drawLine(camera.getIntX(stop.getX())-2*pointsSize, camera.getIntY(stop.getY()), camera.getIntX(stop.getX())+2*pointsSize, camera.getIntY(stop.getY()));
				g2.drawLine(camera.getIntX(stop.getX()), camera.getIntY(stop.getY())-2*pointsSize, camera.getIntX(stop.getX()), camera.getIntY(stop.getY())+2*pointsSize);
			}
		}
		Node node = window.getSelectedNode();
		if(node!=null) {
			g2.setColor(nodeSelectedColor);
			Shape circle = new Ellipse2D.Double(camera.getIntX(node.getCoord().getX())-pointsSize*4,camera.getIntY(node.getCoord().getY())-pointsSize*4,pointsSize*8,pointsSize*8);
			g2.fill(circle);
		}
	}
	private void paintLink(Link link, Graphics2D g2) {
		g2.drawLine(camera.getIntX(link.getFromNode().getCoord().getX()),
				camera.getIntY(link.getFromNode().getCoord().getY()),
				camera.getIntX(link.getToNode().getCoord().getX()),
				camera.getIntY(link.getToNode().getCoord().getY()));
	}
	public void centerCamera(double x, double y) {
		camera.centerCamera(x, y);
	}
	public Point2D getCenter() {
		return camera.getCenter();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		this.requestFocus();
		if(e.getClickCount()==2 && e.getButton()==MouseEvent.BUTTON3)
			camera.centerCamera(camera.getDoubleX(e.getX()), camera.getDoubleY(e.getY()));
		else {
			if(window.getOption().equals(Option.SELECT_STOP) && e.getButton()==MouseEvent.BUTTON1)
				window.selectStop(camera.getDoubleX(e.getX()),camera.getDoubleY(e.getY()));
			else if(window.getOption().equals(Option.SELECT_STOP) && e.getButton()==MouseEvent.BUTTON3)
				window.unselectStop();
			else if(window.getOption().equals(Option.SELECT_NODE) && e.getButton()==MouseEvent.BUTTON1)
				window.selectNode(camera.getDoubleX(e.getX()),camera.getDoubleY(e.getY()));
			else if(window.getOption().equals(Option.SELECT_NODE) && e.getButton()==MouseEvent.BUTTON3)
				window.unselectNode();
			else if(window.getOption().equals(Option.ZOOM) && e.getButton()==MouseEvent.BUTTON1)
				camera.zoomIn(e.getX(), e.getY());
			else if(window.getOption().equals(Option.ZOOM) && e.getButton()==MouseEvent.BUTTON3)
				camera.zoomOut(e.getX(), e.getY());
		}
		repaint();
	}
	public void withStops() {
		withStops = true;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		iniX = e.getX();
		iniY = e.getY();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		camera.move(e.getX(),iniX,e.getY(),iniY);
		iniX = e.getX();
		iniY = e.getY();
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		window.setCoords(camera.getDoubleX(e.getX()),camera.getDoubleY(e.getY()));
	}
	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 's':
			withStops = !withStops;
			break;
		case 'l':
			withLinks = !withLinks;
			break;
		case 'n':
			withNetwork  = !withNetwork;
			break;
		case 'v':
			setBoundaries();
			break;
		case 'i':
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.showSaveDialog(this);
			File file = jFileChooser.getSelectedFile();
			saveImage(file.getName().split("\\.")[file.getName().split("\\.").length-1], file, Integer.parseInt(JOptionPane.showInputDialog("Width", "5760")),  Integer.parseInt(JOptionPane.showInputDialog("Height", "3024")));
			break;
		}
		repaint();
	}
	protected void saveImage(String type, File file, int width, int height) {
		Image windowImage = this.createImage(width, height);
		this.setSize(new Dimension(width, height));
		this.setBoundaries();
		this.paintComponent(windowImage.getGraphics());
		try {
			ImageIO.write((RenderedImage) windowImage, type, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Image saved");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
