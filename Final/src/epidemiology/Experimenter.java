package epidemiology;


import observer.Observer;
import sim.engine.SimState;
import sim.util.Bag;
import sweep.ParameterSweeper;
import sweep.SimStateSweep;

public class Experimenter extends Observer {

	public int numAgents = 0;
	public int infectedAgents=0;
	

	/**
	 * 
	 * @param state
	 */
	
	public Experimenter(String fileName, String folderName, SimStateSweep state, ParameterSweeper sweeper,
			String precision, String[] headers) {
		super(fileName, folderName, state, sweeper, precision, headers);

	}
	
	public void step(SimState state) {
		super.step(state);
		
	}
	public void stop(Environment state) {
		Bag agents = state.sparseSpace.getAllObjects();
		if(agents == null || agents.numObjs == 0) {
			event.stop();
		}
	}
	
	/**
	 * 
	 * @param state
	 */
	public void countInfected(Environment state) {
		Bag agents = state.sparseSpace.getAllObjects();
		for(int i=0;i<agents.numObjs;i++) {
			Agent a =(Agent)agents.objs[i];
			switch(a.status) {
			case INFECTED:
				infectedAgents ++;
				break;
			
			}
		}
	}

	/**
	 * 
	 * @param state
	 * @return
	 */
	public boolean reset(SimState state) {
		super.reset();
		infectedAgents =1;
		return true;
	}
}
	/**
	 * 
	 * @return
	 */
	
	/*public boolean nextInterval() {
		double total = cooperators+defectors+walkaways+walkawaysD;
		data.add(total);
		data.add(cooperators/total);
		data.add(defectors/total);
		data.add(walkaways/total);
		data.add(walkawaysD/total);
		return false;
	}

	/**
	 * 
	 * @param fileName
	 * @param folderName
	 * @param state
	 * @param sweeper
	 * @param precision
	 * @param headers
	 */
	
	
	/**
	 * 
	 * @param state
	 */
/*	public void upDatePopulation(Environment state) {
		Bag agents = state.sparseSpace.getAllObjects();
		for(int i=0;i<agents.numObjs;i++) {
			Agent a = (Agent)agents.objs[i];
			a.inQuarantine=false;
		}
	}
	
	public void strategyDistribution(Environment state) {
		Bag agents = state.sparseSpace.allObjects;
		if(agents.numObjs > 0) {
			double [] data = new double[agents.numObjs];
			for(int i = 0;i<data.length;i++) {
				Agent a = (Agent)agents.objs[i];
				data[i]=a.strategy.id();
			}
			if(agents.numObjs > 0)
				this.upDateHistogramChart(0, (int)state.schedule.getSteps(), data, 100);
		}
	}

	
}

/*upDatePopulation((Environment) state);
		*if(((Environment)state).charts && getdata) {
			strategyDistribution((Environment) state);
		}
		if(((Environment)state).paramSweeps && getdata) {
			reset(state);
			countStrategies((Environment) state);
			nextInterval();
		}
		*/
