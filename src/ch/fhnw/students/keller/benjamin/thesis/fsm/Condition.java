package ch.fhnw.students.keller.benjamin.thesis.fsm;

public interface Condition {		
	public boolean check();
	public boolean isContainer();
	public boolean isInverted();
	public boolean isSingle();
	public void setInverted(boolean inv);
	public void setName(String aName);
	public String getName();
}
