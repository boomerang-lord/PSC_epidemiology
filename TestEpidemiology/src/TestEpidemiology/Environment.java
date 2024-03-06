package TestEpidemiology;

public class Environment {

	public Environment() {
		// TODO Auto-generated constructor stub
	}

	int gridWidth = 100;
	int gridHeight = 100;
	in searchRadius;
	int numAgents;
	int recoveryRate;
	int recoveryError = 5;
	double complianceAvg;
	double complianceSD;
	int burninTime;
	double baseInfectionRate;
	double randMove = 0.3;
	boolean shareSpace = true;

	ENUM status = (susceptible, exposed, infected, recovered);
	clock = 0;

}
