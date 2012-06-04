package ch.fhnw.students.keller.benjamin.thesis.fsm;


public class Transition {
	private State toState;
	private State fromState;
	private Condition condition;
	private String name;

	public Transition(State state, String name) {
		this.name = name;
		fromState = state;
		condition = new ConditionContainer();
	}

	public State getToState() {
		return toState;
	}

	public void setToState(State toState) {
		this.toState = toState;
	}

	public boolean checkCondition() {
		return condition.check();
	}

	public void setName(String name) {
		this.name = name;
	}

	public Condition getCondition() {
		return condition;
	}

	public String toString() {
		return name;
	}

	public State getFromState() {
		return fromState;
	}

}
