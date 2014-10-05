package com.mangolion.mangojournal.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournal.adapter.emotion.EmotionViewAdapter;
import com.mangolion.mangojournallib.feeling.JournalFeeling;

public class FragmentFeelingView extends Fragment {
	ActivityMain activity;
	public static String id = "Feeling View";
	public EmotionViewAdapter adapter;
	public JournalFeeling feeling;
	
	public static FragmentFeelingView newInstance(ActivityMain activity, JournalFeeling feeling){
		FragmentFeelingView view = new FragmentFeelingView();
		view.activity = activity;
		view.feeling = feeling;
		return view;
	}
	
	TextView tvDesc;
	ListView lvMain;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity.status.add(id);
		activity.setTitle(id);
		activity.setSetting("edit");
		View view = inflater.inflate(R.layout.feelingview, container, false);
		lvMain = (ListView) view.findViewById(R.id.list);
		tvDesc = (TextView) view.findViewById(R.id.tvDesc);

		adapter = new EmotionViewAdapter(activity, feeling.emotions, feeling.usePercentage);
		lvMain.setAdapter(adapter);
		tvDesc.setText(feeling.desc);
		return view;
	}

}
