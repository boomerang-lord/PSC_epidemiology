package epidemiology;

import java.awt.Color;

import sweep.GUIStateSweep;
import sweep.SimStateSweep;

public class GUI extends GUIStateSweep {
	
	

	public GUI(SimStateSweep state, int gridWidth, int gridHeight, Color backdrop, Color agentDefaultColor,
			boolean agentPortrayal) {
		super(state, gridWidth, gridHeight, backdrop, agentDefaultColor, agentPortrayal);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
	GUI.initialize(Environment.class, Experimenter.class, GUI.class, 100, 100, Color.WHITE, Color.BLUE, false, spaces.SPARSE);

     // GUI.initialize(theClass, theClass, theClass, numberOfHCharts, bins, null, null, arrayChartTypeH, spaces, chartTitleH);
      
	}

}
















/*//All arrays must have the same number of elements as the number of charts.
/*String[] title2 = {"Rate of Infection of Susceptible Agents"};//A string array, where every entry is the title of a chart
String[] x2 = {"String1"};//A string array, where every entry is the x-axis title
String[] y2 = {"String2"};//A string array, where every entry is the y-axis title
GUI.initializeArrayHistogramChart(1, title2, x2, y2, new int[10]);*/