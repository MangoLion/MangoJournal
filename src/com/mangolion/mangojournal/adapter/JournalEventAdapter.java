package com.mangolion.mangojournal.adapter;

import java.util.List;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.event.JournalEvent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JournalEventAdapter{
	public static int resId = R.layout.eventitem;
	ImageView ivEmotion, ivIcon;
	TextView tvName;
	LinearLayout llEvent;

	public View getView(JournalEvent event, View convertView, ActivityMain activity) {
		
		View view = convertView;
		if (view == null){
			view = LayoutInflater.from(activity).inflate(resId, null);
		}
		
		ivEmotion = (ImageView) view.findViewById(R.id.ivEmotion);
		ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
		tvName = (TextView) view.findViewById(R.id.tvName);
		llEvent = (LinearLayout) view.findViewById(R.id.llEvent);
		
		tvName.setText(event.name);
		ivIcon.setImageResource((Integer) event.icon);
		
		
		return view;
	}

}
