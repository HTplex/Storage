package playground.balac.freefloating.qsim;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.util.Log;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.core.controler.Controler;
import org.matsim.core.utils.collections.QuadTree;
import org.matsim.core.utils.io.IOUtils;

public class FreeFloatingVehiclesLocation {
	
	private QuadTree<FreeFloatingStation> vehicleLocationQuadTree;	
	
	public FreeFloatingVehiclesLocation(Scenario scenario, ArrayList<FreeFloatingStation> stations) throws IOException {
	    double minx = (1.0D / 0.0D);
	    double miny = (1.0D / 0.0D);
	    double maxx = (-1.0D / 0.0D);
	    double maxy = (-1.0D / 0.0D);

        for (Link l : scenario.getNetwork().getLinks().values()) {
	      if (l.getCoord().getX() < minx) minx = l.getCoord().getX();
	      if (l.getCoord().getY() < miny) miny = l.getCoord().getY();
	      if (l.getCoord().getX() > maxx) maxx = l.getCoord().getX();
	      if (l.getCoord().getY() <= maxy) continue; maxy = l.getCoord().getY();
	    }
	    minx -= 1.0D; miny -= 1.0D; maxx += 1.0D; maxy += 1.0D;

	    vehicleLocationQuadTree = new QuadTree<FreeFloatingStation>(minx, miny, maxx, maxy);
	    
	    
	    for(FreeFloatingStation f: stations) {  
	    	
	    	vehicleLocationQuadTree.put(f.getLink().getCoord().getX(), f.getLink().getCoord().getY(), f);
	    }
	    
	  
	    
	   
	  }
	
	public FreeFloatingVehiclesLocation(String inputFilePath, Controler controler) throws IOException {
	    double minx = (1.0D / 0.0D);
	    double miny = (1.0D / 0.0D);
	    double maxx = (-1.0D / 0.0D);
	    double maxy = (-1.0D / 0.0D);

        for (Link l : controler.getScenario().getNetwork().getLinks().values()) {
	      if (l.getCoord().getX() < minx) minx = l.getCoord().getX();
	      if (l.getCoord().getY() < miny) miny = l.getCoord().getY();
	      if (l.getCoord().getX() > maxx) maxx = l.getCoord().getX();
	      if (l.getCoord().getY() <= maxy) continue; maxy = l.getCoord().getY();
	    }
	    minx -= 1.0D; miny -= 1.0D; maxx += 1.0D; maxy += 1.0D;

	    vehicleLocationQuadTree = new QuadTree<FreeFloatingStation>(minx, miny, maxx, maxy);
	    
	    BufferedReader reader = IOUtils.getBufferedReader(inputFilePath);
	    String s = reader.readLine();
	    int i = 1;
	    while(s != null) {
	    	
	    	String[] arr = s.split("\t", -1);

            Link l = controler.getScenario().getNetwork().getLinks().get(Id.create(arr[0], Link.class));
	    	ArrayList<String> vehIDs = new ArrayList<String>();
	    	
	    	for (int k = 0; k < Integer.parseInt(arr[1]); k++) {
	    		vehIDs.add(Integer.toString(i));
	    		i++;
	    	}
	    	FreeFloatingStation f = new FreeFloatingStation(l, Integer.parseInt(arr[1]), vehIDs);
	    	
	    	vehicleLocationQuadTree.put(l.getCoord().getX(), l.getCoord().getY(), f);
	    	s = reader.readLine();
	    	
	    	
	    }	    
	    
	   
	  }
	
	public QuadTree<FreeFloatingStation> getQuadTree() {
		
		return vehicleLocationQuadTree;
	}
	
	public void addVehicle(Link link, String id) {
		
		FreeFloatingStation f = vehicleLocationQuadTree.get(link.getCoord().getX(), link.getCoord().getY());
		
		if (f == null || !f.getLink().getId().toString().equals(link.getId().toString())) {
			
			ArrayList<String> vehIDs = new ArrayList<String>();
			
			vehIDs.add(id);
			
			FreeFloatingStation fNew = new FreeFloatingStation(link, 1, vehIDs);		
			
			vehicleLocationQuadTree.put(link.getCoord().getX(), link.getCoord().getY(), fNew);
			
			
		}
		else {
			ArrayList<String> vehIDs = f.getIDs();
			ArrayList<String> newvehIDs = new ArrayList<String>();
			for (String s : vehIDs) {
				newvehIDs.add(s);
			}
			newvehIDs.add(id);
			FreeFloatingStation fNew = new FreeFloatingStation(link, f.getNumberOfVehicles() + 1, newvehIDs);		
			vehicleLocationQuadTree.remove(link.getCoord().getX(), link.getCoord().getY(), f);
			vehicleLocationQuadTree.put(link.getCoord().getX(), link.getCoord().getY(), fNew);
			
		}
		
		
	}
	
	public void removeVehicle(Link link, String id) {
		
		FreeFloatingStation f = vehicleLocationQuadTree.get(link.getCoord().getX(), link.getCoord().getY());
		
		if ( f.getLink().getId().toString().equals(link.getId().toString())) {
			
			if (f.getNumberOfVehicles() == 1)
				vehicleLocationQuadTree.remove(link.getCoord().getX(), link.getCoord().getY(), f);
			
			
			else {
				ArrayList<String> vehIDs = f.getIDs();
				ArrayList<String> newvehIDs = new ArrayList<String>();
				for (String s : vehIDs) {
					newvehIDs.add(s);
				}
				newvehIDs.remove(id);
				FreeFloatingStation fNew = new FreeFloatingStation(link, f.getNumberOfVehicles() - 1, newvehIDs);	
				
				vehicleLocationQuadTree.remove(link.getCoord().getX(), link.getCoord().getY(), f);
				vehicleLocationQuadTree.put(link.getCoord().getX(), link.getCoord().getY(), fNew);
			}
			
		}
		else {
			
			Log.error("trying to take a car from the link with no cars, this should never happen");
			
		}
		
		
	}
	
	
	
}
