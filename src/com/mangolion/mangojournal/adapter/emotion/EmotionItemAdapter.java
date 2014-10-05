package com.mangolion.mangojournal.adapter.emotion;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.emotion.JournalEmotion;

public class EmotionItemAdapter extends ArrayAdapter<JournalEmotion>{

	public List<JournalEmotion> emotions;
	public static int resId = R.layout.emotionitem;
	ActivityMain activity;
	
	public EmotionItemAdapter(ActivityMain activity, 
			List<JournalEmotion> Emotions) {
		super(activity, resId, Emotions);
		this.emotions = Emotions;
		this.activity = activity;  
	}
	
	TextView tvEmotion;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JournalEmotion emotion = emotions.get(position);
		
		View view = convertView;
		if (view == null){
			view = LayoutInflater.from(activity).inflate(resId, null);
		}
		tvEmotion = (TextView) view.findViewById(R.id.tvEmotion);	
		tvEmotion.setText(emotion.name);
		if (emotion.icon != null) {
			Drawable icon = activity.getResources().getDrawable(
					(Integer) emotion.icon);
			icon.setBounds(0, 0, icon.getIntrinsicWidth(),
					icon.getIntrinsicHeight());
			tvEmotion.setCompoundDrawables(null, null, icon, null);
		}
		
		return view;
	}

}
