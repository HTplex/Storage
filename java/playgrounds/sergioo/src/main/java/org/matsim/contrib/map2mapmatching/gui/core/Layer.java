package org.matsim.contrib.map2mapmatching.gui.core;

import java.awt.Graphics2D;


public class Layer {
	
	//Attributes
	private boolean visible;
	private final boolean active;
	private Painter painter;
	
	//Method
	public Layer(Painter painter) {
		super();
		this.painter = painter;
		visible = true;
		active = true;
	}
	public Layer(Painter painter, boolean active) {
		super();
		this.painter = painter;
		visible = true;
		this.active = active;
	}
	public Painter getPainter() {
		return painter;
	}
	public void setPainter(Painter painter) {
		this.painter = painter;
	}
	public void paint(Graphics2D g2, LayersPanel layersPanel) throws Exception {
		if(visible && painter!=null)
			painter.paint(g2, layersPanel);
	}
	public void changeVisible() {
		visible = !visible;
	}
	public boolean isActive() {
		return active;
	}
	
}
