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
	private bool decidedQuarantine = false;
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

public bool checkQuarantine(Environment state) { //
	if (!this.decidedQuarantine){
		this.decidedQuarantine = true;
		if(state.random.double(1) < this.compliance){ { //e.g if we want 80% chance of quarantining we would set this.compliance to .80
			this.inQuarantine == true;
		}
	}
	return this.inQuarantine;
}

public bool checkRecover(Environment state) {
	int recoveryTime = state.recoveryTime + nextInt(2 * state.recoveryError) - state.recoveryError;
	if (this.sickTime == recoveryTime) {
			this.status == "RECOVERED";
			this.inQuarantine == false;
			return true;
	return false;
	}
}

public void interact (Environment state, Bag neighbors) {
	if(neighbors.isEmpty()) {
		return;
	}
	for (agents i in neighbors) {
		boolean infected = Math.random().baseInfectionRate;
		if infected == true {
			this.status = "EXPOSED";
			return;
		}
	}
	return;
}
