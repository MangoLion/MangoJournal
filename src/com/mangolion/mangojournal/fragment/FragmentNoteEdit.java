package com.mangolion.mangojournal.fragment;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.event.JournalEvent;
import com.mangolion.mangojournallib.note.JournalNote;
import com.mangolion.mangojournallib.main.JournalMain;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentNoteEdit extends Fragment {
	ActivityMain activity;
	public static String id = "Edit Note";
	public String mode = "";
	public JournalNote note, backUpNote;
	
	public static FragmentNoteEdit newInstance(ActivityMain activity, JournalNote note, String mode){
		FragmentNoteEdit edit = new FragmentNoteEdit();
		edit.activity = activity;
		edit.note = note;
		if (note != null){
		edit.backUpNote = new JournalNote(note.desc);
		}else {
			edit.backUpNote = new JournalNote("");
		}
		edit.mode = mode;
		return edit;
	}
	
	EditText etDesc;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activity.status.add(id);
		activity.setTitle(id);
		activity.setSetting("add", "done");
		
		View view = inflater.inflate(R.layout.noteedit, container, false);
		etDesc = (EditText)  view.findViewById(R.id.etDesc);
		
		if (backUpNote != null){
			etDesc.setText(backUpNote.desc);
		}
		
		return view;
	}

	public void save() {
		String desc = etDesc.getText().toString();
		if (mode.equals("new")){
			note = new JournalNote(desc);
			JournalMain.entries.add(note);
			//refresh entry's adapter
			activity.journalEntry.adapter.notifyDataSetChanged();
		}else if (mode.equals("open")){
			note.desc = desc;
		}
		
		getFragmentManager().popBackStack();
	}
}
