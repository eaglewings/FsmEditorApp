package ch.fhnw.students.keller.benjamin.thesis.test;

import ch.fhnw.students.keller.benjamin.thesis.fsm.StateMachine;
import android.content.Context;
import android.widget.ArrayAdapter;

public class MachineAdapter extends ArrayAdapter<String> {
	private StateMachine fsm;
	public MachineAdapter(Context context, int resource, int textViewResourceId, StateMachine fsm){
		super(context, resource, textViewResourceId);
		this.fsm = fsm;
		//this.set
	}
	
}
