/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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

package playground.sergioo.gtfs2PTSchedule2011.pathEditor.kernel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.Node;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.network.LinkImpl;
import org.matsim.core.router.AStarEuclidean;
import org.matsim.core.router.util.LeastCostPathCalculator;
import org.matsim.core.router.util.LeastCostPathCalculator.Path;
import org.matsim.core.router.util.PreProcessEuclidean;
import org.matsim.core.router.util.TravelDisutility;
import org.matsim.core.router.util.TravelTime;
import org.matsim.core.utils.collections.Tuple;
import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.core.utils.geometry.CoordUtils;
import org.matsim.vehicles.Vehicle;

import others.sergioo.util.geometry.Line2D;
import others.sergioo.util.geometry.Point2D;
import others.sergioo.util.geometry.Vector2D;
import playground.sergioo.gtfs2PTSchedule2011.Shape;
import playground.sergioo.gtfs2PTSchedule2011.Stop;
import playground.sergioo.gtfs2PTSchedule2011.StopTime;
import playground.sergioo.gtfs2PTSchedule2011.Trip;

public class RoutePath {
	
	//Constants
	private static final double MIN_DISTANCE_DELTA = 20*180/(6371000*Math.PI);
	
	//Attributes
	private Map<String, Stop> stops;
	public List<Link> links;
	private final Network network;
	private final Trip trip;
	private String mode;
	
	//Parameters
	private double minDistance = 40*180/(6371000*Math.PI);
	private int numCandidates = 3;
	private boolean withAngleShape = false;
	private boolean withShapeCost = false;
	private boolean withInsideStops = true;
	private boolean us = true;
	private boolean reps = true;
	private boolean inStops = true;
	private LeastCostPathCalculator leastCostPathCalculator;
	
	//Methods
	public RoutePath(Network network, String mode, Trip trip, Map<String, Stop> stops) {
		super();
		this.network = network;
		this.mode = mode;
		this.trip = trip;
		this.stops = stops;
		links = new ArrayList<Link>();
		setWithShapeCost();
		calculatePath();
	}
	public RoutePath(Network network, String mode, Trip trip, Map<String, Stop> stops, List<Link> links) {
		super();
		this.network = network;
		this.mode = mode;
		this.trip = trip;
		this.stops = stops;
		this.links = links;
		setWithShapeCost();
	}
	public boolean isWithAngleShape() {
		return withAngleShape;
	}
	public void setWithAngleShape() {
		this.withAngleShape = !withAngleShape;
	}
	public boolean isWithShapeCost() {
		return withShapeCost;
	}
	public boolean isWithInsideStops() {
		return withInsideStops;
	}
	public void setWithInsideStops() {
		this.withInsideStops = !withInsideStops;
	}
	public boolean isUs() {
		return us;
	}
	public void setUs() {
		us = !us;
	}
	public boolean isReps() {
		return reps;
	}
	public void setReps() {
		reps = !reps;
	}
	public boolean isInStops() {
		return inStops;
	}
	public void setInStops() {
		inStops = !inStops;
	}
	public String getUsText() {
		return "Us "+us;
	}
	public String getRepsText() {
		return "Reps "+reps;
	}
	public String getInsideStopsText() {
		return "InStops "+inStops;
	}
	public String getLinkText() {
		return "";
	}
	public String getStopText() {
		return "";
	}
	public String getMinDistanceText() {
		return Math.round(minDistance*6371000*Math.PI/180)+"";
	}
	public double getMinDistance() {
		return minDistance;
	}
	public int getNumCandidates() {
		return numCandidates;
	}
	public String getNumCandidatesText() {
		return numCandidates+"";
	}
	public List<Link> getLinks() {
		return links;
	}
	public Collection<Link> getStopLinks() {
		Collection<Link> links = new ArrayList<Link>();
		for(StopTime stopTime:trip.getStopTimes().values()) {
			String linkId = stops.get(stopTime.getStopId()).getLinkId();
			if(linkId!=null)
				links.add(network.getLinks().get(Id.createLinkId(linkId)));
		}
		return links;
	}
	public Collection<Link> getNetworkLinks(double xMin, double yMin, double xMax, double yMax) {
		Collection<Link> links =  new HashSet<Link>();
		for(Link link:network.getLinks().values()) {
			Coord linkCenter = link.getCoord();
			if(xMin-10*minDistance<linkCenter.getX()&&yMin-10*minDistance<linkCenter.getY()&&xMax+10*minDistance>linkCenter.getX()&&yMax+10*minDistance>linkCenter.getY())
				links.add(link);
		}
		return links;
	}
	public SortedMap<Integer,Coord> getShapePoints() {
		if(trip.getShape()!=null)
			return trip.getShape().getPoints();
		else
			return new TreeMap<Integer,Coord>();
	}
	public Collection<Coord> getStopPoints() {
		Collection<Coord> points = new ArrayList<Coord>();
		for(StopTime stopTime:trip.getStopTimes().values())
			points.add(stops.get(stopTime.getStopId()).getPoint());
		return points;
	}
	public String getStopId(int pos) {
		int i = 0;
		for(StopTime stopTime:trip.getStopTimes().values()) {
			if(i==pos)
				return stopTime.getStopId();
			i++;
		}
		return "";
	}
	public Link getLink(int index) {
		return links.get(index);
	}
	public Coord getStop(String selectedStopId) {
		return stops.get(selectedStopId).getPoint();
	}
	public int getIndexStop(String selectedStopId) {
		int i=0;
		for(StopTime stopTime:trip.getStopTimes().values()) {
			if(stopTime.getStopId().equals(selectedStopId))
				return i;
			i++;
		}
		return -1;
	}
	public int getLinkIndexStop(String selectedStopId) {
		for(int i=0; i<links.size(); i++)
			if(links.get(i).getId().toString().equals(stops.get(selectedStopId).getLinkId()))
				return i;
		return -1;
	}
	private int getLinkPosition(String link) {
		for(int i=0; i<links.size(); i++)
			if(link.equals(links.get(i).getId().toString()))
				return i;
		return -1;
	}
	public int getIndexNearestLink(double x, double y) {
		Coord coord = new CoordImpl(x, y);
		int nearest = -1;
		double nearestDistance = Double.POSITIVE_INFINITY;
		for(int i=0; i<links.size(); i++) {
			double distance = ((LinkImpl) links.get(i)).calcDistance(coord); 
			if(distance<nearestDistance) {
				nearest = i;
				nearestDistance = distance;
			}
		}
		return nearest;
	}
	public String getIdNearestStop(double x, double y) {
		Coord coord = new CoordImpl(x, y);
		String nearest = "";
		double nearestDistance = Double.POSITIVE_INFINITY;
		for(StopTime stopTime:trip.getStopTimes().values()) {
			double distance = CoordUtils.calcDistance(stops.get(stopTime.getStopId()).getPoint(),coord);
			if(distance<nearestDistance) {
				nearest = stopTime.getStopId();
				nearestDistance = distance;
			}
		}
		return nearest;
	}
	public Node getNearestNode(double x, double y) {
		Coord point = new CoordImpl(x, y);
		Node nearest = links.get(0).getFromNode();
		double nearestDistance = CoordUtils.calcDistance(point, nearest.getCoord());
		for(Link link:links) {
			double distance = CoordUtils.calcDistance(point,link.getToNode().getCoord());
			if(distance<nearestDistance) {
				nearestDistance = distance;
				nearest = link.getToNode();
			}
		}
		return nearest;
	}
	public void addLinkFirst(Coord point) {
		Link nearest = null;
		double nearestDistance = Double.POSITIVE_INFINITY;
		for(Link link:network.getLinks().values()) {
			double distance = ((LinkImpl)link).calcDistance(point);
			if(distance<nearestDistance) {
				nearestDistance = distance;
				nearest = link;
			}
		}
		links.add(0,nearest);
	}
	public void addLinkNext(int index, Coord second) {
		Link link = links.get(index);
		if(index==links.size()-1||!link.getToNode().equals(links.get(index+1).getFromNode())) {
			Point2D toPoint = new Point2D(link.getToNode().getCoord().getX(), link.getToNode().getCoord().getY());
			Point2D secondPoint = new Point2D(second.getX(), second.getY());
			Vector2D dirSegment = new Vector2D(toPoint, secondPoint);
			Link bestLink = null;
			double smallestAngle = Double.POSITIVE_INFINITY;
			for(Link linkN:network.getLinks().values())
				if(link.getToNode().equals(linkN.getFromNode())) {
					Point2D toPoint2 = new Point2D(linkN.getToNode().getCoord().getX(), linkN.getToNode().getCoord().getY());
					Vector2D linkNSegment = new Vector2D(toPoint, toPoint2);
					double angle = dirSegment.getAngleTo(linkNSegment);
					if(angle<smallestAngle) {
						smallestAngle = angle;
						bestLink = linkN;
					}
				}
			links.add(index+1, bestLink);
		}	
	}
	public void addLinkNetwork(Node fromNode, Node toNode) {
		Id<Link> linkId = Id.createLinkId(network.getLinks().size()*2);
		network.addLink(network.getFactory().createLink(linkId, fromNode, toNode));
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(RoutesPathsGenerator.NEW_NETWORK_LINKS_FILE,true));
			writer.println(linkId);
			writer.println(fromNode.getId());
			writer.println(toNode.getId());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Node createNode(double x, double y) {
		Node node = network.getFactory().createNode(Id.createNodeId("n"+network.getNodes().size()), new CoordImpl(x, y));
		network.addNode(node);
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(RoutesPathsGenerator.NEW_NETWORK_NODES_FILE,true));
			writer.println(node.getId());
			writer.println(node.getCoord().getX());
			writer.println(node.getCoord().getY());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}
	public void removeLink(int index) {
		links.remove(index);
	}
	public void removeLinksFrom(int index) {
		int size=links.size();
		for(int i=index; i<size; i++)
			links.remove(index);
	}
	public void removeLinksTo(int index) {
		for(int i=0; i<=index; i++)
			links.remove(0);
	}
	public void addShortestPath(int indexI) {
		if(indexI<links.size()-1) {
			Path path = leastCostPathCalculator.calcLeastCostPath(links.get(indexI).getToNode(), links.get(indexI+1).getFromNode(), 0, null, null);
			if(path!=null) {
				int i=1;
				for(Link link:path.links) {
					links.add(indexI+i,link);
					i++;
				}
			}
		}
	}
	public void calculatePath() {
		links.clear();
		Iterator<StopTime> prev=trip.getStopTimes().values().iterator(), next=trip.getStopTimes().values().iterator();
		List<Link> prevLL, nextLL;
		Link prevL = null, nextL = null;
		next.next();
		Stop prevStop = null,nextStop = null;
		if(next.hasNext()) {
			Path bestPath=null;
			prevStop = stops.get(prev.next().getStopId());
			nextStop = stops.get(next.next().getStopId());
			if(prevStop.getLinkId()!=null) {
				prevLL=new ArrayList<Link>();
				prevLL.add(network.getLinks().get(Id.createLinkId(prevStop.getLinkId())));
			}
			else
				prevLL=getBestLinksMode(network, prevStop.getPoint(),trip.getShape());
			if(nextStop.getLinkId()!=null) {
				nextLL=new ArrayList<Link>();
				nextLL.add(network.getLinks().get(Id.createLinkId(nextStop.getLinkId())));
			}
			else
				nextLL=getBestLinksMode(network, nextStop.getPoint(),trip.getShape());
			List<Tuple<Path,Link[]>> paths = new ArrayList<Tuple<Path,Link[]>>();
			for(int i=0; i<prevLL.size(); i++)
				for(int j=0; j<nextLL.size(); j++) {
					prevL = prevLL.get(i);
					nextL = nextLL.get(j);
					Path path;
					if(prevL==nextL)
						path = new Path(new ArrayList<Node>(), new ArrayList<Link>(), 0.0, 0.0);
					else {
						path = leastCostPathCalculator.calcLeastCostPath(prevL.getToNode(), nextL.getFromNode(), 0, null, null);
						if(path == null)
							path = new Path(new ArrayList<Node>(), new ArrayList<Link>(), 0.0, 0.0);
						path.links.add(0,prevL);
					}
					path.links.add(nextL);
					paths.add(new Tuple<Path,Link[]>(path,new Link[]{prevL,nextLL.get(j)}));
				}
			double shortestDistance = Double.POSITIVE_INFINITY;
			for(Tuple<Path,Link[]> tuple:paths) {
				if(tuple.getFirst().links.size()>0) {
					double distance = calculateDistance(tuple.getFirst(),prevStop.getPoint(),nextStop.getPoint());
					if(bestPath==null||distance<=shortestDistance) {
				 		shortestDistance = distance;
				 		bestPath = tuple.getFirst();
				 		prevStop.setLinkId(tuple.getSecond()[0].getId().toString());
				 		nextStop.setLinkId(tuple.getSecond()[1].getId().toString());
				 	}
				}
			}
			prevL = bestPath.links.get(bestPath.links.size()-1);
			links.addAll(bestPath.links);
			prevStop = nextStop;
		}
		for(;next.hasNext();) {
			Path bestPath=null;
			nextStop = stops.get(next.next().getStopId());
			if(nextStop.getLinkId()!=null) {
				nextL = network.getLinks().get(Id.createLinkId(nextStop.getLinkId()));
				if(prevL.equals(nextL))
					bestPath = new Path(new ArrayList<Node>(), new ArrayList<Link>(), 0.0, 0.0);
				else {
					bestPath = leastCostPathCalculator.calcLeastCostPath(prevL.getToNode(), nextL.getFromNode(), 0, null, null);
					if(bestPath == null)
						bestPath = new Path(new ArrayList<Node>(), new ArrayList<Link>(), 0.0, 0.0);
					else
						bestPath.links.add(0,prevL);
				}
				bestPath.links.add(nextL);
			}
			else {
				nextLL=getBestLinksMode(network, nextStop.getPoint(),trip.getShape());
				List<Tuple<Path,Link>> paths = new ArrayList<Tuple<Path,Link>>();
				for(int i=0; i<nextLL.size(); i++) {
					nextL = nextLL.get(i);
					Path path;
					if(prevL.equals(nextL))
						path = new Path(new ArrayList<Node>(), new ArrayList<Link>(), 0.0, 0.0);
					else {
						path = leastCostPathCalculator.calcLeastCostPath(prevL.getToNode(), nextL.getFromNode(), 0, null, null);
						if(path == null)
							path = new Path(new ArrayList<Node>(), new ArrayList<Link>(), 0.0, 0.0);
						else
							path.links.add(0,prevL);
					}
					path.links.add(nextL);
					paths.add(new Tuple<Path,Link>(path,nextL));
				}	
				double shortestDistance = Double.POSITIVE_INFINITY;
				for(Tuple<Path,Link> tuple:paths) {
					double distance = calculateDistance(tuple.getFirst(),prevStop.getPoint(),nextStop.getPoint());
					if(bestPath==null||distance<=shortestDistance) {
						shortestDistance = distance;
					 	bestPath = tuple.getFirst();
					 	nextStop.setLinkId(tuple.getSecond().getId().toString());
					}
				}
			}
			prevL = bestPath.links.get(bestPath.links.size()-1);
			bestPath.links.remove(0);
			links.addAll(bestPath.links);
			prevStop = nextStop;
		}
	}
	private List<Link> getBestLinksMode(Network network, Coord coord, Shape shape) {
		List<Double> nearestDistances = new ArrayList<Double>();
		List<Link> nearestLinks = new ArrayList<Link>();
		for(double minDistance=this.minDistance;nearestLinks.size()<numCandidates;minDistance+=MIN_DISTANCE_DELTA)
			for(Link link:network.getLinks().values())
				if(link.getAllowedModes().contains(mode)) {
					Point2D fromPoint = new Point2D(link.getFromNode().getCoord().getX(), link.getFromNode().getCoord().getY());
					Point2D toPoint = new Point2D(link.getToNode().getCoord().getX(), link.getToNode().getCoord().getY());
					Vector2D linkVector = new Vector2D(fromPoint, toPoint);
					Line2D linkSegment = new Line2D(fromPoint, toPoint);
					if(!withInsideStops || linkSegment.isNearestInside(new Point2D(coord.getX(),coord.getY())))
						if(!withAngleShape || shape==null || linkVector.getAngleTo(shape.getVector(coord))<Math.PI/16) {
							double distance = ((LinkImpl)link).calcDistance(coord);
							if(distance<minDistance) {
								int i=0;
								for(; i<nearestDistances.size() && distance<nearestDistances.get(i); i++);
								if(i>0 || nearestLinks.size()<numCandidates) {
									nearestDistances.add(i, distance);
									nearestLinks.add(i, link);
									if(nearestLinks.size()>numCandidates) {
										nearestDistances.remove(0);
										nearestLinks.remove(0);
									}
								}
							}
						}
				}
		return nearestLinks;	
	}
	private double calculateDistance(Path path, Coord prevStop, Coord nextStop) {
		LinkImpl firstLink = (LinkImpl)path.links.get(0);
		Point2D fromPoint = new Point2D(firstLink.getFromNode().getCoord().getX(), firstLink.getFromNode().getCoord().getY());
		Point2D toPoint = new Point2D(firstLink.getToNode().getCoord().getX(), firstLink.getToNode().getCoord().getY());
		Line2D firstLinkLine = new Line2D(fromPoint, toPoint);
		double distance = firstLink.calcDistance(prevStop);
		if(path.links.size()==1) {
			distance += firstLinkLine.getNearestPoint(new Point2D(prevStop.getX(), prevStop.getY())).getDistance(firstLinkLine.getNearestPoint(new Point2D(nextStop.getX(), nextStop.getY())));
			distance += firstLink.calcDistance(nextStop);
		}
		else {
			Coord firstToNodeCoord = firstLink.getToNode().getCoord();
			distance += firstLinkLine.getNearestPoint(new Point2D(prevStop.getX(), prevStop.getY())).getDistance(new Point2D(firstToNodeCoord.getX(),firstToNodeCoord.getY()));
			LinkImpl lastLink = (LinkImpl)path.links.get(path.links.size()-1);
			Point2D fromPoint2 = new Point2D(lastLink.getFromNode().getCoord().getX(), lastLink.getFromNode().getCoord().getY());
			Point2D toPoint2 = new Point2D(lastLink.getToNode().getCoord().getX(), lastLink.getToNode().getCoord().getY());
			Line2D lastLinkLine = new Line2D(fromPoint2, toPoint2);
			for(int i=1; i<path.links.size()-1; i++)
				distance += path.links.get(i).getLength();
			Coord lastFromNodeCoord = lastLink.getFromNode().getCoord();
			distance += lastLinkLine.getNearestPoint(new Point2D(nextStop.getX(), nextStop.getY())).getDistance(new Point2D(lastFromNodeCoord.getX(),lastFromNodeCoord.getY()));
			distance += lastLink.calcDistance(nextStop);
		}
		return distance;
	}
	public int isPathJoined() {
		for(int i=0; i<links.size()-1; i++)
			if(!links.get(i).getToNode().equals(links.get(i+1).getFromNode()))
				return i;
		return -1;
	}
	public int isPathWithoutUs() {
		for(int i=1; i<links.size()-2; i++)
			if(links.get(i).getFromNode().equals(links.get(i+1).getToNode()))
				return i;
		return -1;
	}
	public int isPathWithoutRepeatedLink() {
		for(int i=0; i<links.size()-1; i++)
			for(int j=i+1; j<links.size(); j++)
				if(links.get(i).equals(links.get(j))&&!(i==0 && j==links.size()-1))
					return i;
		return -1;
	}
	public String allStopsWithLink() {
		for(StopTime stopTime: trip.getStopTimes().values())
			if(stops.get(stopTime.getStopId()).getLinkId()==null)
				return stopTime.getStopId();
		return "";
	}
	public int isFirstLinkWithStop() {
		String firstStopLink = stops.get(trip.getStopTimes().values().iterator().next().getStopId()).getLinkId();
		if(!firstStopLink.equals(links.get(0).getId().toString()))
			return getLinkPosition(firstStopLink)-1;
		return -1;
	}
	public String allStopsWithCorrectLink() {
		for(StopTime stopTime: trip.getStopTimes().values()) {
			Stop stop = stops.get(stopTime.getStopId());
			Link link = network.getLinks().get(Id.createLinkId(stop.getLinkId()));
			Point2D fromPoint = new Point2D(link.getFromNode().getCoord().getX(), link.getFromNode().getCoord().getY());
			Point2D toPoint = new Point2D(link.getToNode().getCoord().getX(), link.getToNode().getCoord().getY());
			Line2D linkLine = new Line2D(fromPoint, toPoint);
			Point2D point = new Point2D(stop.getPoint().getX(),stop.getPoint().getY());
			if(!linkLine.isNearestInside(point)) {
				int pos=getLinkPosition(link.getId().toString());
				if(pos==-1)
					return stopTime.getStopId();
				if(pos==links.size()-1||pos==0)
					return "";
				Link link2 = links.get(pos+1);
				fromPoint = new Point2D(link2.getFromNode().getCoord().getX(), link2.getFromNode().getCoord().getY());
				toPoint = new Point2D(link2.getToNode().getCoord().getX(), link2.getToNode().getCoord().getY());
				Line2D linkLine2 = new Line2D(fromPoint, toPoint);
				if(!(linkLine.getPointPosition(point).equals(Line2D.PointPosition.AFTER)&&linkLine2.getPointPosition(point).equals(Line2D.PointPosition.BEFORE)))
					return stopTime.getStopId();
			}
		}
		return "";
	}
	public String allStopsWithInRouteLink() {
		for(StopTime stopTime: trip.getStopTimes().values()) {
			Stop stop = stops.get(stopTime.getStopId());
			Link link = network.getLinks().get(Id.createLinkId(stop.getLinkId()));
			if(!links.contains(link))
				return stopTime.getStopId();
		}
		return "";
	}
	public void increaseMinDistance() {
		minDistance += MIN_DISTANCE_DELTA;
	}
	public void decreaseMinDistance() {
		if(minDistance-MIN_DISTANCE_DELTA>0)
			minDistance -= MIN_DISTANCE_DELTA;
	}
	public void increaseNumCandidates() {
		numCandidates ++;
	}
	public void decreaseNumCandidates() {
		if(numCandidates-1>0)
			numCandidates --;
	}
	public void initStops() {
		for(StopTime stopTime: trip.getStopTimes().values())
			stops.get(stopTime.getStopId()).setLinkId(null);
	}
	public boolean addLinkStop(int selectedLinkIndex, String selectedStopId) {
		return stops.get(selectedStopId).setLinkId(getLink(selectedLinkIndex).getId().toString());
	}
	public void forceAddLinkStop(int selectedLinkIndex, String selectedStopId) {
		stops.get(selectedStopId).forceSetLinkId(getLink(selectedLinkIndex).getId().toString());
	}
	public void removeLinkStop(String selectedStopId) {
		stops.get(selectedStopId).setLinkId(null);
	}
	public void setWithShapeCost() {
		withShapeCost = !withShapeCost;
		TravelDisutility travelMinCost = null;
		PreProcessEuclidean preProcessData = null;
		if(!withShapeCost || trip.getShape()==null) {
			travelMinCost = new TravelDisutility() {
				@Override
				public double getLinkTravelDisutility(final Link link, final double time, final Person person, final Vehicle vehicle) {
					return getLinkMinimumTravelDisutility(link);
				}
				@Override
				public double getLinkMinimumTravelDisutility(Link link) {
					return link.getLength()/link.getFreespeed();
				}
			};
			
		}
		else {
			travelMinCost = new TravelDisutility() {
				@Override
				public double getLinkTravelDisutility(final Link link, final double time, final Person person, final Vehicle vehicle) {
					return getLinkMinimumTravelDisutility(link);
				}
				@Override
				public double getLinkMinimumTravelDisutility(Link link) {
					return (link.getLength()/link.getFreespeed())*Math.pow(trip.getShape().getDistance(link),1);
				}
			};
		}
		preProcessData = new PreProcessEuclidean(travelMinCost);
		preProcessData.run(network);
		TravelTime timeFunction = new TravelTime() {	

			@Override
			public double getLinkTravelTime(Link link, double time, Person person, Vehicle vehicle) {
				return link.getLength()/link.getFreespeed();
			}
		};
		leastCostPathCalculator = new AStarEuclidean(network, preProcessData, timeFunction);
	}
	
}
