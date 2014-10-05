package com.mangolion.mangojournal.fragment;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.emotion.JournalEmotion;
import com.mangolion.mangojournallib.event.JournalEvent;
import com.mangolion.mangojournallib.main.JournalMain;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentEventEdit extends Fragment {
	ActivityMain activity;
	public static String id = "Edit Event";
	public JournalEvent event;
	public String mode = "";
	public JournalEmotion emotion;
	
	public static FragmentEventEdit newInstance(ActivityMain activity, JournalEvent event, String mode){
		FragmentEventEdit edit = new FragmentEventEdit();
		edit.activity = activity;
		edit.event = event;
		edit.mode = mode;
		return edit;
	}
	
	TextView tvEmotion;
	EditText etName, etDesc, etTime, etLength;
	SeekBar sbImportance;
	CheckBox cbSetTime, cbSetLength, cbShowEmotion, cbIsOngoing;
	ImageView ivEmotion;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity.status.add(id);
		activity.setSetting("done");
		activity.setTitle(id);
		
		View view = inflater.inflate(R.layout.eventedit, container, false);
		tvEmotion = (TextView) view.findViewById(R.id.tvEmotion);
		etName = (EditText) view.findViewById(R.id.etName);
		etDesc = (EditText)  view.findViewById(R.id.etDesc);
		etLength = (EditText) view.findViewById(R.id.etLength);
		etTime = (EditText) view.findViewById(R.id.etTime);
		sbImportance = (SeekBar) view.findViewById(R.id.sbImportance);
		cbSetTime = (CheckBox) view.findViewById(R.id.cbSetTime);
		cbSetLength = (CheckBox) view.findViewById(R.id.cbSetLength);
		cbShowEmotion = (CheckBox) view.findViewById(R.id.cbShowEmote);
		cbIsOngoing = (CheckBox) view.findViewById(R.id.cbIsOngoing);
		ivEmotion =(ImageView) view.findViewById(R.id.ivEmotion);
		
		if (event != null){
			etName.setText(event.name);
			etDesc.setText(event.desc);
			sbImportance.setProgress(event.importance);
			cbIsOngoing.setChecked(event.isOngoing);
		}
		
		return view;
	}

	public void save() {
		String name = etName.getText().toString()
				,desc = etDesc.getText().toString();
		int importance = sbImportance.getProgress();
		boolean isOngoing = cbIsOngoing.isChecked();
		
		if (mode.equals("new")){
			event = new JournalEvent(name, desc, importance, isOngoing, emotion);
			JournalMain.entries.add(event);
			//refresh entry's adapter
			activity.journalEntry.adapter.notifyDataSetChanged();
		}else if (mode.equals("open")){
			event.edit(name, desc, importance, isOngoing);
		}
		
		getFragmentManager().popBackStack();
	}
}
