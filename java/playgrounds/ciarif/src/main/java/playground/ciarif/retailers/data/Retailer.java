package playground.ciarif.retailers.data;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.core.controler.Controler;
import org.matsim.facilities.ActivityFacility;
import org.matsim.facilities.ActivityFacilityImpl;

import playground.ciarif.retailers.stategies.CatchmentAreaRetailerStrategy;
import playground.ciarif.retailers.stategies.CustomersFeedbackStrategy;
import playground.ciarif.retailers.stategies.GravityModelRetailerStrategy;
import playground.ciarif.retailers.stategies.LogitMaxLinkRetailerStrategy;
import playground.ciarif.retailers.stategies.MaxActivitiesRetailerStrategy;
import playground.ciarif.retailers.stategies.MaxLinkRetailerStrategy;
import playground.ciarif.retailers.stategies.MinTravelingCostsRetailerStrategy;
import playground.ciarif.retailers.stategies.RandomRetailerStrategy;
import playground.ciarif.retailers.stategies.RetailerStrategy;

public class Retailer
{
  private final Id<Retailer> id;
  private final Map<Id<ActivityFacility>, ActivityFacility> facilities = new TreeMap<>();
  private static final Logger log = Logger.getLogger(Retailer.class);
  private RetailerStrategy strategy;
  private Map<Id<ActivityFacility>, ActivityFacility> movedFacilities = new TreeMap<>();

  public Retailer(Id<Retailer> id, RetailerStrategy rs) {
    this.id = id;
    this.strategy = rs;
  }

  public final Id<Retailer> getId() {
    return this.id;
  }

  public final boolean addFacility(ActivityFacilityImpl f) {
    if (f == null) return false;
    if (this.facilities.containsKey(f.getId())) return false;
    this.facilities.put(f.getId(), f);

    return true;
  }

  public final boolean addStrategy(Controler controler, String strategyName)
  {
    if (strategyName.contains("randomRetailerStrategy")) {
      this.strategy = new RandomRetailerStrategy(controler);
      return true;
    }
    if (strategyName.contains("maxLinkRetailerStrategy")) {
      this.strategy = new MaxLinkRetailerStrategy(controler);
      return true;
    }
    if (strategyName.contains("logitMaxLinkRetailerStrategy")) {
      this.strategy = new LogitMaxLinkRetailerStrategy(controler);
      return true;
    }
    if (strategyName.contains("catchmentAreaRetailerStrategy")) {
      this.strategy = new CatchmentAreaRetailerStrategy(controler);
      return true;
    }
    if (strategyName.contains("customersFeedbackStrategy")) {
      this.strategy = new CustomersFeedbackStrategy(controler);
      return true;
    }
    if (strategyName.contains("gravityModelRetailerStrategy")) {
    	
      this.strategy = new GravityModelRetailerStrategy(controler);
      return true;
    }
    if (strategyName.contains("maxActivitiesRetailerStrategy")) {
      this.strategy = new MaxActivitiesRetailerStrategy(controler);
      return true;
    }
    if (strategyName.contains("minTravelingCostsRetailerStrategy")) {
      this.strategy = new MinTravelingCostsRetailerStrategy(controler);
      return true;
    }
    throw new RuntimeException("The strategy has been not added!");
  }

  public final ActivityFacilityImpl getFacility(Id<ActivityFacility> facId) {
    return ((ActivityFacilityImpl)this.facilities.get(facId));
  }

  public final Map<Id<ActivityFacility>, ActivityFacility> getFacilities() {
    return this.facilities;
  }

  public final void runStrategy(Map<Id<Link>, LinkRetailersImpl> links) {
	
    this.movedFacilities = this.strategy.moveFacilities(this.facilities, links);
  }

  public Map<Id<ActivityFacility>, ActivityFacility> getMovedFacilities() {
    return this.movedFacilities;
  }
}
