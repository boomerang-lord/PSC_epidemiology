package epidemiology;
import sim.util.Bag;
import sim.engine.SimState;
import sim.engine.Steppable;




public class Agent implements Steppable {
    public int x;
    public int y;
    public int xdir;
    public int ydir;
    public double compliance;
    public Status status;
    public boolean inQuarantine;
    public int sickTime = 0;
	//private MersenneTwisterFast random;

    public Agent(int x, int y, int xdir, int ydir, double compliance, epidemiology.Status status, boolean inQuarantine) {
        this.x = x;
        this.y = y;
        this.xdir = xdir;
        this.ydir = ydir;
        this.compliance = compliance;
        this.status = status;
        this.inQuarantine = inQuarantine;
    }

    @Override
    public void step(SimState state) {
        Environment environment = (Environment) state;
        environment.clock++;

        if (this.status == Status.EXPOSED) {
            this.status = Status.INFECTED;
           return; // [return I guess?]
        }

        if (this.status == Status.INFECTED) {
            this.sickTime++;
            if (environment.clock >= environment.burninTime) {
                if (this.inQuarantine) {
                    checkRecover(environment);
                    if (this.sickTime > environment.quarantineTime) {
                        this.inQuarantine = false;
                        return; // end step
                    }
                }
            }
            checkQuarantine(environment);
            checkRecover(environment);
            if (this.inQuarantine) {
                return;
            }
        }

        if (this.status == Status.SUSCEPTIBLE) {
            Bag neighbors = findSickNeighbors(environment);
            interact(environment, neighbors);
        }
    }

    public void checkQuarantine(Environment state) {
        if (state.random.nextBoolean(compliance)) {
            this.inQuarantine = true;
        }
    }

    public void checkRecover(Environment state) {
        int recoveryTime = state.recoveryTime + state.random.nextInt(2 * state.recoveryError) - state.recoveryError;
        if (this.sickTime == recoveryTime) {
            this.status = Status.RECOVERED;
            this.inQuarantine = false;
        }
    }

    public Bag findSickNeighbors(Environment state) {
        Bag neighbors = state.space.getMooreNeighbors(x, y, state.getSearchRadius(), state.sparseSpace.TOROIDAL, false);
        Bag sickNeighbors = new Bag();
        for (int i = 0; i < neighbors.size(); i++) {
            Agent a = (Agent) neighbors.get(i);
            if (!a.inQuarantine && a.status == Status.INFECTED) {
                sickNeighbors.add(a);
            }
        }
        return sickNeighbors;
    }

    public void interact(Environment state, Bag neighbors) {
        if (neighbors.isEmpty()) {
            return;
        }
        for (int i = 0; i < neighbors.size(); i++) {
            boolean infected = state.random.nextBoolean(state.baseInfectionRate);
            if (infected) {
                this.status = Status.EXPOSED;
                return; // Break out of method and return to step
            }
        }
        // If lucky, they aren't exposed
    }
}
