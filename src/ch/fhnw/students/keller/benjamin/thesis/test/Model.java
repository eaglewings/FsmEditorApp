package ch.fhnw.students.keller.benjamin.thesis.test;

import ch.fhnw.students.keller.benjamin.thesis.fsm.*;

public class Model {
	private StateMachine fsm;

	public Model() {
		fsm = new StateMachine();
		fsm.add(new State(fsm, "State1"));
		fsm.add(new State(fsm, "State2"));
		for (State state : fsm) {
			state.add(new Transition(state, "t1"));
			state.add(new Transition(state, "t2"));
		}
		fsm.add(new State(fsm, "State3"));
		fsm.add(new State(fsm, "State4"));
		fsm.add(new State(fsm, "State5"));
	}

	public StateMachine getMachine() {

		return fsm;
	}
}
