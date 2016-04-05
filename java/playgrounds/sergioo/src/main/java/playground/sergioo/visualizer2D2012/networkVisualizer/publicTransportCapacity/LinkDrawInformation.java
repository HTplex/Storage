package playground.sergioo.visualizer2D2012.networkVisualizer.publicTransportCapacity;

import java.awt.Color;

public class LinkDrawInformation {
	
	//Attributes
	private float thickness;
	private Color color;
	
	//Methods
	public LinkDrawInformation(float thickness, Color color) {
		super();
		this.thickness = thickness;
		this.color = color;
	}
	public float getThickness() {
		return thickness;
	}
	public Color getColor() {
		return color;
	}
	public void increaseThickness(float thickness) {
		this.thickness += thickness;
	}
	
}
