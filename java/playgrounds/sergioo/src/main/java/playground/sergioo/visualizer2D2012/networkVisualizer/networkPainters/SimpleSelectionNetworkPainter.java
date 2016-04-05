package playground.sergioo.visualizer2D2012.networkVisualizer.networkPainters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;

import playground.sergioo.visualizer2D2012.LayersPanel;

public class SimpleSelectionNetworkPainter extends NetworkPainter {
	
	//Attributes
	private Color selectedLinkColor = Color.GREEN;
	private Color selectedNodeColor = Color.MAGENTA;
	private Stroke selectedStroke = new BasicStroke(2);
	private boolean withSelected = true;
	
	//Methods
	public SimpleSelectionNetworkPainter(Network network) {
		super(network);
	}
	public SimpleSelectionNetworkPainter(Network network, Color networkColor) {
		super(network, networkColor);
	}
	public SimpleSelectionNetworkPainter(Network network, Color networkColor, Stroke networkStroke) {
		super(network, networkColor, networkStroke);
	}
	public SimpleSelectionNetworkPainter(Network network, Color networkColor, Stroke networkStroke, Color selectedLinkColor, Color selectedNodeColor, Stroke selectedStroke) {
		super(network, networkColor, networkStroke);
		this.selectedLinkColor = selectedLinkColor;
		this.selectedNodeColor = selectedNodeColor;
		this.selectedStroke = selectedStroke;
	}
	@Override
	public void paint(Graphics2D g2, LayersPanel layersPanel) {
		super.paint(g2, layersPanel);
		if(withSelected) {
			paintSelected(g2, layersPanel);
			for(Link link:networkPainterManager.getSelectedLinks())
				paintSimpleLink(g2, layersPanel, link, selectedStroke, selectedLinkColor);
			for(Node node:networkPainterManager.getSelectedNodes())
				paintCircle(g2, layersPanel, node.getCoord(), 5, selectedNodeColor);
		}
	}
	private void paintSelected(Graphics2D g2, LayersPanel layersPanel) {
		Link link=networkPainterManager.getSelectedLink();
		if(link!=null)
			paintLink(g2, layersPanel, link, selectedStroke, 3, selectedLinkColor);
		Node node = networkPainterManager.getSelectedNode();
		if(node!=null)
			paintCircle(g2, layersPanel, node.getCoord(), 5, selectedNodeColor);
	}
	public void changeVisibleSelectedElements() {
		withSelected = !withSelected;
	}
	
}
