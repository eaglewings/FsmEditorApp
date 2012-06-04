package ch.fhnw.students.keller.benjamin.thesis.fsm;

public abstract class ConditionSingle implements Condition {
	private boolean inverted;
	private String name="";
	abstract boolean checkStraight();
	
	@Override
	public boolean check() {
		if(inverted){
			return !checkStraight();
		}
		else {
			return checkStraight();
		}
		
	}

	@Override
	public boolean isContainer() {
		return false;
	}

	@Override
	public boolean isInverted() {
		return inverted;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public void setInverted(boolean inv) {
		inverted = inv;

	}
	@Override
	public void setName(String aName){
		name=aName;
	}
	@Override
	public String getName(){
		return name;
	}

}
