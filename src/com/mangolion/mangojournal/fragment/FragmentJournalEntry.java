package com.mangolion.mangojournal.fragment;

import java.util.LinkedList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournal.adapter.JournalObjectAdapter;
import com.mangolion.mangojournallib.jouralobj.JournalObj;

public class FragmentJournalEntry extends Fragment {
	ActivityMain activity;
	public static String id = "Entry";
	public LinkedList<JournalObj> components;
	JournalObjectAdapter adapter;
	
	public static FragmentJournalEntry newInstance(ActivityMain activity, LinkedList<JournalObj> components){
		FragmentJournalEntry edit = new FragmentJournalEntry();
		edit.activity = activity;
		edit.components = components;
		return edit;
	}
	
	ListView lvComponents;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity.status.add(id);
		activity.setSetting("add");
		activity.setTitle(id);
		
		View view = inflater.inflate(R.layout.entry, container, false);
		lvComponents = (ListView) view.findViewById(R.id.list);
		
		if (components != null){
			adapter = new JournalObjectAdapter(activity, components);
			lvComponents.setAdapter(adapter);
		}
		
		lvComponents.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				activity.openJournalComponent(components.get(position));
			}
		});
		
		return view;
	}
}