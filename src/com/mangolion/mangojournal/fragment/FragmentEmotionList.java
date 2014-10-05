package com.mangolion.mangojournal.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournal.adapter.emotion.EmotionItemAdapter;
import com.mangolion.mangojournallib.emotion.JournalEmotions;

public class FragmentEmotionList extends Fragment {
	ActivityMain activity;
	public static String id = "Emotion List";
	public String receiver = "";
	public String mode = "";
	public EmotionItemAdapter adapter;
	
	public static FragmentEmotionList newInstance(ActivityMain activity, String mode, String receiver){
		FragmentEmotionList list = new FragmentEmotionList();
		list.activity = activity;
		list.mode = mode;
		list.receiver = receiver;
		return list;
	}
	
	ListView lvMain;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity.status.add(id);
		activity.setTitle(id);
		activity.setSetting("add");
		lvMain = new ListView(activity);
		adapter = new EmotionItemAdapter(activity, JournalEmotions.emotions);
		lvMain.setAdapter(adapter);
		
		lvMain.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getFragmentManager().popBackStack();
				activity.returnEmotionfromUser(receiver, JournalEmotions.emotions.get(position));
			}
		});
		
		View view = lvMain;//inflater.inflate(R.layout.noteedit, container, false);
		return view;
	}

}
