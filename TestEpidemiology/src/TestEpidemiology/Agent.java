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
		state.clock++;
		Environment eState = (Environment)state;
		if(this.status == "EXPOSED"){
			this.status = "INFECTED";
		}
		if(this.status = "INFECTED"){ //if agent is infected
			this.sickTime++;
			if(state.clock >= state.burnInTime){
				if(this.inQuarantine()){ //if the agent is infected and in quarantine
					checkRecover(state); //check if they can recover
					if(this.sickTime > state.quarantineTime){ //if more than designated quarantine time take them out of quarantine
						this.inQuarantine = false;
					}
					return;
				}
				checkQuarantine(state); //check if an infected agent not in quarantine currently should quarantine
				checkRecover(state); //check if an infected agent not in quarantine should recover 
				if(this.inQuarantine()){ //if we decided the agent should recover, return
					return;
				}
			}
		}
		move(state); //all agents not in quarantine move
		if(this.status == "SUSCEPTIBLE"){ //if susceptible interact with all nearest agents
			Bag neighbors = findSickNeighbors(state);
			interact(state, neighbors);
		}
		return;
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
