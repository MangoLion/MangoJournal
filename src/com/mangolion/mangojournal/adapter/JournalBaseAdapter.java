package com.mangolion.mangojournal.adapter;

import java.util.List;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.event.JournalEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class JournalBaseAdapter extends ArrayAdapter<JournalEvent>{

	public List<JournalEvent> events;
	public static int resId = R.layout.eventitem;
	ActivityMain activity;
	
	public JournalBaseAdapter(ActivityMain activity, 
			List<JournalEvent> events) {
		super(activity, resId, events);
		this.events = events;
		this.activity = activity;  
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JournalEvent event = events.get(position);
		
		View view = convertView;
		if (view == null){
			view = LayoutInflater.from(activity).inflate(resId, null);
		}
		return view;
	}

}
