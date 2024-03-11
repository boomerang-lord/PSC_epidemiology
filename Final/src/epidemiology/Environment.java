package epidemiology;
import sim.util.Bag;
import sim.util.distribution.Normal;
import sim.field.grid.SparseGrid2D;
import sweep.SimStateSweep;

public class Environment extends SimStateSweep {
	int gridWidth = 100;
	int gridHeight = 100;
	int searchRadius;
	public int getSearchRadius() {
		return searchRadius;
	}

	public void setSearchRadius(int searchRadius) {
		this.searchRadius = searchRadius;
	}

	int numAgents;
	int recoveryTime;
	int recoveryError = 5;
	double complianceAvg;
	double complianceSD;
	int burninTime;
	double baseInfectionRate;
	double randMove = .3;
	boolean shareSpace = true;
	

	    // Global Variables
	   // public enum Status { SUSCEPTIBLE, EXPOSED, INFECTED, RECOVERED }
	    int clock = 0;
	    SparseGrid2D space;
		public int quarantineTime;

	    
		public Environment(long seed, Class observer) {
			super(seed, observer);
			// TODO Auto-generated constructor stub
		}
		
	   // public Environment(long seed) {
	     //   super(seed);
	    //}

	   /* public void start() {
	        super.start();
	        makeSpace();
	        makeAgents();
	        initializeObserver();
	    }*/
	    public void start() {
			super.start();
			makeSpace(gridWidth,gridHeight);
			makeAgents();
			if(observer != null)
				observer.initialize(space, spaces);
			
		}

	    public void makeSpace() {
	        space = new SparseGrid2D(gridWidth, gridHeight);
	    }


	    public void makeAgents() {
	        // Create 1 agent that is infected (patient zero)
	        int x = random.nextInt(gridWidth);
	        int y = random.nextInt(gridHeight);
	        int xdir = random.nextInt(3) - 1;
	        int ydir = random.nextInt(3) - 1;
	        Normal normal = new Normal(complianceAvg, complianceSD, random);
	        double compliance = normal.nextDouble();
	       // double compliance = random.nextDouble() * complianceSD + complianceAvg;
	        Agent patientZero = new Agent(x, y, xdir, ydir, compliance, Status.INFECTED, false);
	        space.setObjectLocation(patientZero, x, y);

	        // Create remaining agents
	        for (int i = 1; i < numAgents; i++) {
	            int tempx = random.nextInt(gridWidth);
	            int tempty = random.nextInt(gridHeight);

	            if (!shareSpace) {
	                Bag b = space.getObjectsAtLocation(tempx, tempty);
	                while (!b.isEmpty()) {
	                    tempx = random.nextInt(gridWidth);
	                    tempty = random.nextInt(gridHeight);
	                    b = space.getObjectsAtLocation(tempx, tempty);
	                }
	            }

	            x = tempx;
	            y = tempty;
	            xdir = random.nextInt(3) - 1;
	            ydir = random.nextInt(3) - 1;
	           // compliance = random.nextDouble() * complianceSD + complianceAvg;
	            Agent a = new Agent(x, y, xdir, ydir, compliance, Status.SUSCEPTIBLE, false);
	            space.setObjectLocation(a, x, y);
	        }
	    }

	  

	   /* public static void main(String[] args) {
	        doLoop(Environment.class, args);
	        System.exit(0);
	    }*/
	}