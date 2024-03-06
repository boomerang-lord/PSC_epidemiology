package TestEpidemiology;

import Epidemiology.Environment.Status;
import ec.util.MersenneTwisterFast;
import sim.engine.Schedule;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Bag;


public class Agent implements Steppable {
    private int x;
    private int y;
    private int xdir;
    private int ydir;
    private double compliance;
    private Environment.Status status;
    private boolean inQuarantine;
    private int sickTime = 0;
	//private MersenneTwisterFast random;

    public Agent(int x, int y, int xdir, int ydir, double compliance, Environment.Status status, boolean inQuarantine) {
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
		// TODO Auto-generated method stub
		
	}
}

public bool checkQuarantine(environment_state) {
	if (random() this.compliance) {
		this.inQuarantine == true;
	return true;
	}
}

public bool checkRecover(environment_state) {
	int recoveryTime = state.recoveryTime + nextInt(2 * state.recoveryError) - state.recoveryError;
	if (this.sickime == recoveryTime) {
			this.status == "RECOVERED";
			this.inQuarantine == False;
			return true;
	return false;
	}
}

public interact (Environment state, Bag neighbors) {
	if(neighbors.isEmpty()) {
		return;
	}
	for (agents i in neighbors) {
		boolean infected = random().baseInfectionRate;
		if infected == true {
			this.status = "EXPOSED";
			break;
			return step();
		}
	}
	return;
}
