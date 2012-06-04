package ch.fhnw.students.keller.benjamin.thesis.test;

import java.util.Vector;


import ch.fhnw.students.keller.benjamin.thesis.fsm.Action;
import ch.fhnw.students.keller.benjamin.thesis.fsm.DigitalOutAction;
import ch.fhnw.students.keller.benjamin.thesis.fsm.IO.Digital;
import ch.fhnw.students.keller.benjamin.thesis.fsm.State;
import ch.fhnw.students.keller.benjamin.thesis.fsm.Transition;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.*;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActionAdapter extends ArrayAdapter<Action> {
	private Context context;
	private DigitalOutAction da;
	private int position;
	private View view;
	private View visibleDeleteButton;


	public ActionAdapter(Context context, Vector<Action> actions) {
		
		super(context, R.layout.doactionrow, actions);
		Log.d("actionadapter", "actionadapter");
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		this.position=position;
		Action a = this.getItem(position);

		if (a instanceof DigitalOutAction) {
			da = (DigitalOutAction) a;
			if (v == null) {
				Log.d("actionadapter", "V==0"+this.position);

				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				v = inflater.inflate(R.layout.doactionrow, parent, false);
				

			}
			
			Log.d("actionadapter", "position"+this.position);

			if (a != null) {

				TextView txtView1 = (TextView) v.findViewById(R.id.textView1);
				TextView txtView2 = (TextView) v.findViewById(R.id.textView2);
				ToggleButton toggleButton = (ToggleButton) v
						.findViewById(R.id.toggleButton1);
				Button deleteButton = (Button) v.findViewById(R.id.buttondelete);
				deleteButton.setFocusable(true);
				deleteButton.setOnFocusChangeListener(new OnFocusChangeListener() {
					
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						Log.d("actionadapter", "focuschange"+v);
						if(!hasFocus){
							v.setVisibility(View.INVISIBLE);
						}
						
					}
				});
				deleteButton.setOnClickListener(new OnClickListener() {
					private int pos=ActionAdapter.this.position;
					@Override
					public void onClick(View v) {
						v.setVisibility(View.INVISIBLE);
						remove(getItem(pos));
						
					}
				});
				
				toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					private DigitalOutAction da = ActionAdapter.this.da;
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							da.switching=Digital.ON;
						}
						else{
							da.switching=Digital.OFF;
						}
						
					}
				});

				txtView1.setText("Ausgangname");
				if (da.switching == Digital.OFF) {
					toggleButton.setChecked(false);
				} else {
					toggleButton.setChecked(true);
				}
				

				

				
				view=deleteButton;
				
				v.setOnTouchListener(new OnTouchListener() {
					private int pos=ActionAdapter.this.position;
					private View view = ActionAdapter.this.view;
					
					GestureDetector gd=new GestureDetector(new OnGestureListener() {
						
						@Override
						public boolean onSingleTapUp(MotionEvent e) {
							// TODO Auto-generated method stub
							return false;
						}
						
						@Override
						public void onShowPress(MotionEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
								float distanceY) {
							// TODO Auto-generated method stub
							return false;
						}
						
						@Override
						public void onLongPress(MotionEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
								float velocityY) {
							
							Animation animation = new AlphaAnimation(0.0f, 1.0f);
							animation.setDuration(300);
							//animation = AnimationUtils.loadAnimation(context, R.anim.delteanimation);
							
							view.startAnimation(animation);
							view.setVisibility(view.VISIBLE);
							visibleDeleteButton=view;
							view.requestFocus();
							//remove(getItem(pos));
							return false;
						}
						
						@Override
						public boolean onDown(MotionEvent e) {
							if(visibleDeleteButton!=null){
								visibleDeleteButton.setVisibility(View.INVISIBLE);
								visibleDeleteButton=null;
							}
							return false;
						}
					});
					public boolean onTouch(View v, MotionEvent event) {
						gd.onTouchEvent(event);
						return true;
					}
				});

			}
		}
		return v;
	}
}
