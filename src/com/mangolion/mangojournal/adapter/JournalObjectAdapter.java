package com.mangolion.mangojournal.adapter;

import java.util.List;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.event.JournalEvent;
import com.mangolion.mangojournallib.feeling.JournalFeeling;
import com.mangolion.mangojournallib.jouralobj.JournalObj;
import com.mangolion.mangojournallib.note.JournalNote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class JournalObjectAdapter extends ArrayAdapter<JournalObj> {

	public List<JournalObj> objs;
	public static int noteResId = R.layout.noteitem,
			eventResId = R.layout.eventitem,
			feelingResId = R.layout.feelingedit;
	ActivityMain activity;
	JournalEventAdapter eventAdapter;
	JournalNoteAdapter noteAdapter;
	JournalFeelingAdapter feelingAdapter;

	public JournalObjectAdapter(ActivityMain activity, List<JournalObj> objs) {
		super(activity, noteResId, objs);
		this.objs = objs;
		this.activity = activity;

		eventAdapter = new JournalEventAdapter();
		noteAdapter = new JournalNoteAdapter();
		feelingAdapter = new JournalFeelingAdapter();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JournalObj obj = objs.get(position);

		View view = convertView;
		if (view == null) {
			if (obj.type.equals("note"))
				view = noteAdapter.getView((JournalNote) obj, convertView,
						activity);
			else if (obj.type.equals("event"))

				view = eventAdapter.getView((JournalEvent) obj, convertView,
						activity);// LayoutInflater.from(activity).inflate(noteResId,
									// null);
			else if (obj.type.equals("feeling")) {
				view = feelingAdapter.getView((JournalFeeling) obj, convertView, activity);
			}
			
			//view.setBackgroundResource(R.drawable.bgglow);
		}
		return view;
	}

}
