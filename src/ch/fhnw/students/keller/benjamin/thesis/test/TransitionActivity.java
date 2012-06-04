package ch.fhnw.students.keller.benjamin.thesis.test;

import ch.fhnw.students.keller.benjamin.thesis.fsm.State;
import ch.fhnw.students.keller.benjamin.thesis.fsm.StateMachine;
import ch.fhnw.students.keller.benjamin.thesis.fsm.Transition;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TransitionActivity extends Activity {

	private StateMachine fsm;
	private ArrayAdapter<State> adapter;
	private Transition transition;
	private State state;
	private State[] values;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transition);

		fsm = ((FsmEditorApp) getApplicationContext()).model.getMachine();

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		int position = extras.getInt("StateIndex");
		state = fsm.get(position);
		position = extras.getInt("TransitionIndex");
		transition = state.get(position);

		ListView listView = (ListView) findViewById(R.id.listView1);
		TextView txtViewFromState = (TextView) findViewById(R.id.textViewFromState);
		TextView txtViewTransition = (TextView) findViewById(R.id.textViewTransition);
		Spinner spinner = (Spinner) findViewById(R.id.spinnerToState);
		txtViewFromState.setText(state.getStateName());
		txtViewTransition.setText(transition.toString());
		
		values= new State[fsm.size()-1];
		int j=0;
		for (int i = 0; i < fsm.size(); i++) {
			Log.d("transact",""+i);
			if(fsm.get(i)!=state){
				
				values[j]=fsm.get(i);
				j++;
			}
				
		}
		
		adapter=new ArrayAdapter<State>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, values);
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				transition.setToState(values[pos]);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// nothing
				
			}
		});
		
		
	}
	

}
