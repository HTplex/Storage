package playground.mzilske.ant2014;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import playground.mzilske.cdr.PowerPlans;

import java.io.IOException;
import java.io.PrintWriter;

class RegimeResource {

	private String WD;

	private String regime;

	public RegimeResource(String wd, String regime) {
		this.WD = wd;
		this.regime = regime;
	}

	public MultiRateRunResource getMultiRateRun(String alternative) {
		return new MultiRateRunResource(WD + "/alternatives/" + alternative, regime, alternative);
	}

    public void durationsSimulated() {
        String filename = WD + "/durations-simulated.txt";
        final Scenario baseScenario = getBaseRun().getLastIteration().getExperiencedPlansAndNetwork();
        FileIO.writeToFile(filename, new StreamingOutput() {
            @Override
            public void write(PrintWriter pw) throws IOException {
                pw.printf("%s\n", "duration");
                for (Person person : baseScenario.getPopulation().getPersons().values()) {
                    Plan plan = person.getSelectedPlan();
                    for (int i=0; i < plan.getPlanElements().size(); i++) {
                        PlanElement pe = plan.getPlanElements().get(i);
                        if (pe instanceof Activity) {
                            Activity act = (Activity) pe;
                            double duration = PowerPlans.duration(act);
                            pw.printf("%f\n", duration);
                        }
                    }
                }
            }
        });
    }

	public RunResource getBaseRun() {
		return new RunResource(WD + "/output-berlin", "2kW.15");
	}
	

}
