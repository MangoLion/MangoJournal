package com.mangolion.mangojournal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.note.JournalNote;

public class JournalNoteAdapter{
	public static int resId = R.layout.noteitem;

	TextView tvDesc;
	ImageView ivIcon;
	
	public View getView(JournalNote note, View convertView, ActivityMain activity) {
		
		View view = convertView;
		if (view == null){
			view = LayoutInflater.from(activity).inflate(resId, null);
		}

		tvDesc = (TextView) view.findViewById(R.id.tvDesc);
		ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
		
		tvDesc.setText(note.desc);
		ivIcon.setImageResource((Integer) note.icon);
		
		return view;
	}

}

