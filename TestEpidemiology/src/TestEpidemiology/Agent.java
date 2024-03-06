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
