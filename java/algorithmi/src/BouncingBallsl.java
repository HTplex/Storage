 /*************************************************************************
 *  Compilation:  javac CollisionSystem.java
 *  Execution:    java CollisionSystem N               (N random particles)
 *                java CollisionSystem < input.txt     (from a file) 
 *  
 *  Creates N random particles and simulates their motion according
 *  to the laws of elastic collisions.
 *
 *************************************************************************/

import java.awt.Color;
import edu.princeton.cs.algs4.*;


public class BouncingBallsl {
    private MinPQ<Event> pq;        // the priority queue
    private double t  = 0.0;        // simulation clock time
    private double hz = 0.5;        // number of redraw events per clock tick
    private Particle[] particles;   // the array of particles

    // create a new collision system with the given set of particles
    public BouncingBallsl(Particle[] particles) {
        this.particles = particles.clone();   // defensive copy
    }

    // updates priority queue with all new events for particle a
    private void predict(Particle a, double limit) {
        if (a == null) return;

        // particle-particle collisions
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }

        // particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
        if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
    }

    // redraw all particles
    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / hz, null, null));
        }
    }

      
    public void simulate(double limit) {
        
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));        // redraw event


        while (!pq.isEmpty()) { 

            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            if      (a != null && b != null) a.bounceOff(b);              
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw(limit);               // redraw event

            predict(a, limit);
            predict(b, limit);
        }
    }


    private static class Event implements Comparable<Event> {
        private final double time;         // time that event is scheduled to occur
        private final Particle a, b;       // particles involved in event, possibly null
        private final int countA, countB;  // collision counts at event creation
                
        
        public Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a    = a;
            this.b    = b;
            if (a != null) countA = a.count();
            else           countA = -1;
            if (b != null) countB = b.count();
            else           countB = -1;
        }
 
        public int compareTo(Event that) {
            if      (this.time < that.time) return -1;
            else if (this.time > that.time) return +1;
            else                            return  0;
        }
        
          public boolean isValid() {
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;
        }
   
    }



  
    public static void main(String[] args) {

        // remove the border
        StdDraw.setXscale(1.0/22.0, 21.0/22.0);
        StdDraw.setYscale(1.0/22.0, 21.0/22.0);
        //StdDraw.set


        StdDraw.show(0);


        Particle[] particles;


        int N=30;
            particles = new Particle[N];
            for (int i = 0; i < N; i++) {
                double rx     = Math.random();
                double ry     = Math.random();
                double vx     = (Math.random()*2-1)/200;
                double vy     = (Math.random()*2-1)/200;
                double radius = 0.05*Math.random()+0.01;
                double mass   = radius*radius;
                int r         = (int)(255*Math.random());
                int g         = (int)(255*Math.random());
                int b         = (int)(255*Math.random());
                Color color   = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(Integer.MAX_VALUE);
    }
}


class Particle {
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    private double rx, ry;    // position
    private double vx, vy;    // velocity
    private double radius;    // radius
    private double mass;      // mass
    private Color color;      // color
    private int count;        // number of collisions so far


    // create a new particle with given parameters        
    public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
        this.vx = vx;
        this.vy = vy;
        this.rx = rx;
        this.ry = ry;
        this.radius = radius;
        this.mass   = mass;
        this.color  = color;
    }
         
    // create a random particle in the unit box (overlaps not checked)
    public Particle() {
        rx     = Math.random();
        ry     = Math.random();
        vx     = 0.01 * (Math.random() - 0.5);
        vy     = 0.01 * (Math.random() - 0.5);
        radius = 0.01;
        mass   = 0.5;
        color  = Color.BLACK;
    }

    // updates position
    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    // draw the particle
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    // return the number of collisions involving this particle
    public int count() { return count; }
        
  
    // how long into future until collision between this particle a and b?
    public double timeToHit(Particle b) {
        Particle a = this;
        if (a == b) return INFINITY;
        double dx  = b.rx - a.rx;
        double dy  = b.ry - a.ry;
        double dvx = b.vx - a.vx;
        double dvy = b.vy - a.vy;
        double dvdr = dx*dvx + dy*dvy;
        if (dvdr > 0) return INFINITY;
        double dvdv = dvx*dvx + dvy*dvy;
        double drdr = dx*dx + dy*dy;
        double sigma = a.radius + b.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        // if (drdr < sigma*sigma) StdOut.println("overlapping particles");
        if (d < 0) return INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    // how long into future until this particle collides with a vertical wall?
    public double timeToHitVerticalWall() {
        if      (vx > 0) return (1.0 - rx - radius) / vx;
        else if (vx < 0) return (radius - rx) / vx;  
        else             return INFINITY;
    }

    // how long into future until this particle collides with a horizontal wall?
    public double timeToHitHorizontalWall() {
        if      (vy > 0) return (1.0 - ry - radius) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        else             return INFINITY;
    }

    public void changeColor(Particle a, Particle b){
    	int r=a.color.getRed()+b.color.getRed();
    	int g=a.color.getGreen()+b.color.getGreen();
    	int bb=a.color.getBlue()+b.color.getBlue();
    	a.color=new Color(r/2, g/2, bb/2);
    	b.color=new Color(r/2, g/2, bb/2);
    }
    public void changeColorr(Particle a, Particle b){
    	a.color=new Color((int)(256*Math.random()), (int)(256*Math.random()), (int)(256*Math.random()));
    	b.color=new Color((int)(256*Math.random()), (int)(256*Math.random()), (int)(256*Math.random()));
    }
    // update velocities upon collision between this particle and that particle
    public void bounceOff(Particle that) {
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;             // dv dot dr
        double dist = this.radius + that.radius;   // distance between particle centers at collison
        changeColorr(this, that);
        // normal force F, and in x and y directions
        double F = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double fx = F * dx / dist;
        double fy = F * dy / dist;

        // update velocities according to normal force
        this.vx += fx / this.mass;
        this.vy += fy / this.mass;
        that.vx -= fx / that.mass;
        that.vy -= fy / that.mass;
        
        

        // update collision counts
        this.count++;
        that.count++;
    }

    // update velocity of this particle upon collision with a vertical wall
    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
    }

    // update velocity of this particle upon collision with a horizontal wall
    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
    }

    // return kinetic energy associated with this particle
    public double kineticEnergy() { return 0.5 * mass * (vx*vx + vy*vy); }
}
class CollisionSystem {
    private MinPQ<Event> pq;        // the priority queue
    private double t  = 0.0;        // simulation clock time
    private double hz = 0.5;        // number of redraw events per clock tick
    private Particle[] particles;   // the array of particles

    // create a new collision system with the given set of particles
    public CollisionSystem(Particle[] particles) {
        this.particles = particles.clone();   // defensive copy
    }

    // updates priority queue with all new events for particle a
    private void predict(Particle a, double limit) {
        if (a == null) return;

        // particle-particle collisions
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }

        // particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
        if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
    }

    // redraw all particles
    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / hz, null, null));
        }
    }

      
   /********************************************************************************
    *  Event based simulation for limit seconds
    ********************************************************************************/
    public void simulate(double limit) {
        
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));        // redraw event


        // the main event-driven simulation loop
        while (!pq.isEmpty()) { 

            // get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            // physical collision, so update positions, and then simulation clock
            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            // process event
            if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw(limit);               // redraw event

            // update the priority queue with new collisions involving a or b
            predict(a, limit);
            predict(b, limit);
        }
    }


   /*************************************************************************
    *  An event during a particle collision simulation. Each event contains
    *  the time at which it will occur (assuming no supervening actions)
    *  and the particles a and b involved.
    *
    *    -  a and b both null:      redraw event
    *    -  a null, b not null:     collision with vertical wall
    *    -  a not null, b null:     collision with horizontal wall
    *    -  a and b both not null:  binary collision between a and b
    *
    *************************************************************************/
    private static class Event implements Comparable<Event> {
        private final double time;         // time that event is scheduled to occur
        private final Particle a, b;       // particles involved in event, possibly null
        private final int countA, countB;  // collision counts at event creation
                
        
        // create a new event to occur at time t involving a and b
        public Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a    = a;
            this.b    = b;
            if (a != null) countA = a.count();
            else           countA = -1;
            if (b != null) countB = b.count();
            else           countB = -1;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            if      (this.time < that.time) return -1;
            else if (this.time > that.time) return +1;
            else                            return  0;
        }
        
        // has any collision occurred between when event was created and now?
        public boolean isValid() {
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;
        }
   
    }



   /********************************************************************************
    *  Sample client
    ********************************************************************************/
    public static void main(String[] args) {

        // remove the border
        StdDraw.setXscale(1.0/22.0, 21.0/22.0);
        StdDraw.setYscale(1.0/22.0, 21.0/22.0);


        // StdDraw.setCanvasSize(1024, 768);
        // StdDraw.setXscale(1.0/22.0 +.016, +0.013 + 27.6666667/22.0);
        // StdDraw.setYscale(1.0/22.0, 21.0/22.0);

        // turn on animation mode
        StdDraw.show(0);

        // the array of particles
        Particle[] particles;

        // create N random particles
        if (args.length == 1) {
            int N = Integer.parseInt(args[0]);
            particles = new Particle[N];
            for (int i = 0; i < N; i++) particles[i] = new Particle();
        }

        // or read from standard input
        else {
            int N = StdIn.readInt();
            particles = new Particle[N];
            for (int i = 0; i < N; i++) {
                double rx     = StdIn.readDouble();
                double ry     = StdIn.readDouble();
                double vx     = StdIn.readDouble();
                double vy     = StdIn.readDouble();
                double radius = StdIn.readDouble();
                double mass   = StdIn.readDouble();
                int r         = StdIn.readInt();
                int g         = StdIn.readInt();
                int b         = StdIn.readInt();
                Color color   = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        }

        // create collision system and simulate
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000);
    }
      
}
