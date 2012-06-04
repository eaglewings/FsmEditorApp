package ch.fhnw.students.keller.benjamin.thesis.fsm;


public class StateMachineController {
	private StateMachine stateMachine;
	private State activeState;
	
	StateMachineController(StateMachine stateMachine){
		this.stateMachine=stateMachine;
		activeState=stateMachine.getInitialState();
		
	}
	
	private void changeState(State state){
		activeState.deactivate();
		state.activate();
		activeState = state;
	}
	
	private void checkTransitions(){
		for (int i = 0; i < activeState.size(); i++) {
			if(activeState.get(i).checkCondition()){
				changeState(activeState.get(i).getToState());
			}
			
		}
	}
	
	
	public int main(String[] args){
		while(true){
			return 0;
		}
		
		
	}
	
	
}
