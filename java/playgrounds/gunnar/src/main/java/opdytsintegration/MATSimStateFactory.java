package opdytsintegration;

import optdyts.DecisionVariable;
import optdyts.SimulatorState;

import org.matsim.api.core.v01.population.Population;

import floetteroed.utilities.math.Vector;

/**
 * A factory for MATSim simulation states.
 * 
 * @author Gunnar Flötteröd
 *
 * @param <X>
 *            the simulator state
 * @param <U>
 *            the decision variable type
 * 
 * @see SimulatorState
 * @see DecisionVariable
 */
public interface MATSimStateFactory<X extends SimulatorState, U extends DecisionVariable> {

	/**
	 * Creates a new object representation of the current MATSim simulation
	 * state.
	 * 
	 * @see MATSimState
	 * 
	 * @param population
	 *            the current MATSim population
	 * @param stateVector
	 *            a vector representation of the state to be created
	 * @param decisionVariable
	 *            the decision variable that has led to the state to be created
	 * @return the current MATSim simulation state
	 */
	public X newState(Population population, Vector stateVector,
			U decisionVariable);

}
