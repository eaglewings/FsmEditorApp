package ch.fhnw.students.keller.benjamin.thesis.test;

import ch.fhnw.students.keller.benjamin.thesis.fsm.Action;
import ch.fhnw.students.keller.benjamin.thesis.fsm.DigitalOutAction;
import ch.fhnw.students.keller.benjamin.thesis.fsm.State;
import ch.fhnw.students.keller.benjamin.thesis.fsm.StateMachine;
import ch.fhnw.students.keller.benjamin.thesis.fsm.Transition;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class StateActivity extends TabActivity {
	private StateMachine fsm;
	private StateAdapter stateAdapter;
	private ActionAdapter actionAdapter;
	private State state;
	private AlertDialog alertDia;
	private Handler myHandler = new Handler();
	private TabHost mTabHost;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.state2);
		

		fsm = ((FsmEditorApp) getApplicationContext()).model.getMachine();

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		int position = extras.getInt("StateIndex");
		state = fsm.get(position);

		ListView listViewTransitions = (ListView) findViewById(R.id.listViewTransitions);
		ListView listViewActions = (ListView) findViewById(R.id.listViewActions);
		TextView txtView = (TextView) findViewById(R.id.textView2);
		txtView.setText(state.getStateName());

		mTabHost = getTabHost();

		mTabHost.addTab(mTabHost.newTabSpec("tab_test1")
				.setIndicator("Actions").setContent(R.id.tab1));
		mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
				.setIndicator("Transitions").setContent(R.id.tab2));

		stateAdapter = new StateAdapter(this, state);

		listViewTransitions.setAdapter(stateAdapter);

		listViewTransitions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(view.getContext(),
						TransitionActivity.class);
				i.putExtra("TransitionIndex", position);
				i.putExtra("StateIndex", fsm.indexOf(state));
				startActivity(i);

			}

		});

		alertDia = new AlertDialog.Builder(this).create();
		alertDia.setOnDismissListener(new DialogInterface.OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				myHandler.post(new Runnable() {
					@Override
					public void run() {
						stateAdapter.notifyDataSetChanged();
					}
				});

			}
		});
		alertDia.setCancelable(true);
		// Delete Dialog bei Longclick
		listViewTransitions
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> adapter,
							View view, int pos, long id) {
						final int finalpos = pos;
						alertDia.setTitle("Delete State \"" + state.get(pos)
								+ "\"?");
						alertDia.setButton(AlertDialog.BUTTON_POSITIVE,
								"Delete",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										state.remove(finalpos);
										StateActivity.this.stateAdapter
												.notifyDataSetChanged();
									}
								});
						alertDia.show();

						return false;
					}
				});
		if(state.actions==null){
		Log.d("StateActivity", "action ist null");
		}
		actionAdapter = new ActionAdapter(this, state.actions);

		listViewActions.setAdapter(actionAdapter);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.statemenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.addTransition) {
			state.add(new Transition(state, "NewTransition"
					+ (state.size() + 1)));
			stateAdapter.notifyDataSetChanged();
			return true;
		}
		if (item.getItemId() == R.id.addDOaction) {
			Action action = new DigitalOutAction();
			state.actions.add(action);
			actionAdapter.notifyDataSetChanged();
			return true;
		}
		return true;
	}

	public void onResume() {
		super.onResume();
		
		stateAdapter.notifyDataSetChanged();
		actionAdapter.notifyDataSetChanged();
		
	}
}
