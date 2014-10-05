package com.mangolion.mangojournal.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournal.adapter.emotion.EmotionEditAdapter;
import com.mangolion.mangojournallib.emotion.JournalEmotion;
import com.mangolion.mangojournallib.feeling.JournalFeeling;
import com.mangolion.mangojournallib.main.JournalMain;

public class FragmentFeelingEdit extends Fragment {
	ActivityMain activity;
	public static String id = "Edit Feeling";
	public String mode = "";
	public JournalFeeling feeling, backUpFeeling;
	
	public static FragmentFeelingEdit newInstance(ActivityMain activity, JournalFeeling feeling, String mode){
		FragmentFeelingEdit edit = new FragmentFeelingEdit();
		edit.activity = activity;
		edit.feeling = feeling;
		if (feeling != null){
		edit.backUpFeeling = new JournalFeeling(feeling.desc, feeling.usePercentage, feeling.emotions);
		}else {
			edit.backUpFeeling = new JournalFeeling("", false);
		}
		edit.mode = mode;
		return edit;
	}
	
	EditText etDesc;
	CheckBox cbUsePercentage;
	Button btAdd;
	ListView lvEmotions;
	public EmotionEditAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity.status.add(id);
		activity.setTitle(id);
		activity.setSetting("add", "done");
		
		View view = inflater.inflate(R.layout.feelingedit, container, false);
		etDesc = (EditText)  view.findViewById(R.id.etDesc);
		cbUsePercentage = (CheckBox) view.findViewById(R.id.cbUsePrecentage);
		btAdd = (Button) view.findViewById(R.id.btAdd);
		lvEmotions = (ListView) view.findViewById(R.id.lvEmotions);
		
		adapter = new EmotionEditAdapter(activity, backUpFeeling.emotions, backUpFeeling.usePercentage);
		lvEmotions.setAdapter(adapter);
		
		if (backUpFeeling != null){
			etDesc.setText(backUpFeeling.desc);
			cbUsePercentage.setChecked(backUpFeeling.usePercentage);
		}
		
		btAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.getEmotionfromList(id);
			}
		});
		
		cbUsePercentage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				backUpFeeling.usePercentage = isChecked;
				adapter.usePercentage = isChecked;
				adapter.notifyDataSetChanged();
			}
		});
		
		return view;
	}

	public void save() {
		String desc = etDesc.getText().toString();
		//JournalFeeling showFeeling = 
		boolean usePercentage = cbUsePercentage.isChecked();
		
		if (mode.equals("new")){
			feeling = new JournalFeeling(desc, usePercentage, backUpFeeling.emotions);
			JournalMain.entries.add(feeling);
			//refresh entry's adapter
			activity.journalEntry.adapter.notifyDataSetChanged();
		}else if (mode.equals("open")){
			feeling.edit(desc, usePercentage, backUpFeeling.emotions);
		}
		
		getFragmentManager().popBackStack();
	}

	public void addEmotion(JournalEmotion emotion) {
		backUpFeeling.emotions.add(emotion);
		adapter.notifyDataSetChanged();
	}
}
