package playground.ciarif.retailers.data;

import java.util.ArrayList;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.utils.collections.QuadTree;
import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.facilities.ActivityFacility;

public class RetailZone
{
  private Id<RetailZone> id;
  private QuadTree<Person> personsQuadTree;
  private QuadTree<ActivityFacility> shopsQuadTree;
  private ArrayList<Person> persons = new ArrayList<Person>();
  private ArrayList<ActivityFacility> shops = new ArrayList<ActivityFacility>();
  private CoordImpl minCoord;
  private CoordImpl maxCoord;

  public RetailZone(Id<RetailZone> id, Double minx, Double miny, Double maxx, Double maxy)
  {
    this.id = id;
    this.personsQuadTree = new QuadTree<Person>(minx.doubleValue(), miny.doubleValue(), maxx.doubleValue(), maxy.doubleValue());
    this.shopsQuadTree = new QuadTree<ActivityFacility>(minx.doubleValue(), miny.doubleValue(), maxx.doubleValue(), maxy.doubleValue());
    this.minCoord = new CoordImpl(minx.toString(), miny.toString());
    this.maxCoord = new CoordImpl(maxx.toString(), maxy.toString());
  }

  public Id<RetailZone> getId() {
    return this.id;
  }

  public void addPersonToQuadTree(Coord coord, Person person) {
    this.personsQuadTree.put(coord.getX(), coord.getY(), person);
    this.persons.add(person);
  }

  public void addShopToQuadTree(Coord coord, ActivityFacility shop) {
    this.shopsQuadTree.put(coord.getX(), coord.getY(), shop);
    this.shops.add(shop);
  }

  public QuadTree<Person> getPersonsQuadTree() {
    return this.personsQuadTree;
  }

  public ArrayList<Person> getPersons() {
    return this.persons;
  }

  public ArrayList<ActivityFacility> getShops() {
    return this.shops;
  }

  public QuadTree<ActivityFacility> getShopsQuadTree() {
    return this.shopsQuadTree; }

  public CoordImpl getMaxCoord() {
    return this.maxCoord; }

  public CoordImpl getMinCoord() {
    return this.minCoord;
  }
}

