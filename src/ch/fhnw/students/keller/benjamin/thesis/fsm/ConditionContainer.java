package ch.fhnw.students.keller.benjamin.thesis.fsm;

import java.util.ArrayList;

public class ConditionContainer extends ArrayList<Condition> implements Condition {
	private boolean inverted= false;
	private boolean and = false;
	private String name="";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean checkAnd(){
		for (int i = 0; i < size(); i++) {
			if(!get(i).check()){
				return false;
			}
		}
		return true;
	}
	private boolean checkOr(){
		for (int i = 0; i < size(); i++) {
			if(get(i).check()){
				return true;
			}
		}
		return false;
	}
	private boolean checkStraight(){
		if(and){
			return checkAnd();
		}
		else{
			return checkOr();
		}
	}
	@Override
	public boolean check() {
		if(inverted){
			return !checkStraight();
		}
		else {
			return checkStraight();
		}
		
	}
	
	public void setAnd(){
		and=true;
	}
	public void setOr(){
		and=false;
	}
	public boolean isAnd(){
		return and;
	}
	public boolean isOr(){
		return !and;
	}
	
	@Override
	public boolean isContainer() {
		return true;
	}

	@Override
	public boolean isInverted() {
		return inverted;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public void setInverted(boolean inv) {
		inverted=inv;
		
	}
	@Override
	public void setName(String aName) {
		name=aName;
		
	}
	@Override
	public String getName() {
		
		return name;
	}
	
	

}
