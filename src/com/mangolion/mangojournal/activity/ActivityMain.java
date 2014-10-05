package com.mangolion.mangojournal.activity;

import java.util.LinkedList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.fragment.FragmentEmotionList;
import com.mangolion.mangojournal.fragment.FragmentEventEdit;
import com.mangolion.mangojournal.fragment.FragmentFeelingEdit;
import com.mangolion.mangojournal.fragment.FragmentFeelingView;
import com.mangolion.mangojournal.fragment.FragmentJournalEntry;
import com.mangolion.mangojournal.fragment.FragmentNoteEdit;
import com.mangolion.mangojournallib.emotion.JournalEmotion;
import com.mangolion.mangojournallib.emotion.JournalEmotions;
import com.mangolion.mangojournallib.event.JournalEvent;
import com.mangolion.mangojournallib.feeling.JournalFeeling;
import com.mangolion.mangojournallib.jouralobj.JournalObj;
import com.mangolion.mangojournallib.main.JournalIcons;
import com.mangolion.mangojournallib.main.JournalMain;
import com.mangolion.mangojournallib.note.JournalNote;

public class ActivityMain extends Activity implements OnMenuItemClickListener {
	FragmentManager manager;
	FragmentTransaction transaction;

	public FragmentJournalEntry journalEntry;
	public FragmentEventEdit eventEdit;
	public FragmentFeelingEdit feelingEdit;
	public FragmentNoteEdit noteEdit;
	public FragmentEmotionList emotionList;
	public FragmentFeelingView feelingView;

	public LinkedList<String> status = new LinkedList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = getFragmentManager();
		setContentView(R.layout.journal_main);
		journaInit();
		journalEntry = FragmentJournalEntry.newInstance(this,
				JournalMain.entries);
		getFragmentManager().beginTransaction()
				.replace(R.id.frMain, journalEntry).commit();
	}

	private void journaInit() {
		// set icons for components
		JournalIcons.icEvent = R.drawable.eventicon;
		JournalIcons.icNote = R.drawable.noteicon;
		JournalIcons.icTask = R.drawable.taskicon;
		JournalIcons.icFeeling = R.drawable.feelingicon;

		JournalMain.entries.add(new JournalNote("This is a note"));
		JournalMain.entries.add(new JournalEvent("Event Name", "Event Desc", 3,
				false, new JournalEmotion("happy", ":D", 0, 0, R.drawable.emotionhappy)));
		JournalEmotions.emotions.add(new JournalEmotion("sad", "Q.Q", 0, 0, R.drawable.emotionsad));
		JournalEmotions.emotions.add(new JournalEmotion("happy", ":D", 0, 0, R.drawable.emotionhappy));
		JournalEmotions.emotions.add(new JournalEmotion("frustrated", "Q.Q", 0, 0, R.drawable.emotionfrustrated));
		JournalEmotions.emotions.add(new JournalEmotion("sick", "Q.Q", 0, 0, R.drawable.emotionsick));
		// set icons for emotions
		for (JournalEmotion emotion: JournalEmotions.emotions) {
			JournalMain.entries.add(new JournalFeeling(emotion.name, false, emotion));
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String currentStatus = status.getLast();

		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:

			break;
		case R.id.mAdd:
			if (currentStatus.equals(FragmentJournalEntry.id)) {
				PopupMenu popup = new PopupMenu(this, findViewById(id));
				MenuInflater inflater = popup.getMenuInflater();
				inflater.inflate(R.menu.components, popup.getMenu());
				popup.setOnMenuItemClickListener(this);
				popup.show();
			}
			break;
		case R.id.mDone:

			if (currentStatus.equals(FragmentEventEdit.id)) {
				eventEdit.save();
			}else if (currentStatus.equals(FragmentFeelingEdit.id)){
				feelingEdit.save();
			}else if (currentStatus.equals(FragmentNoteEdit.id)){
				noteEdit.save();
			}
			break;
		case R.id.mEdit:

			if (currentStatus.equals(FragmentFeelingView.id)) {
				openJournalFeeling(feelingView.feeling, "open");
			}
			break;
		default:
			break;
		}
		return  super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		Toast.makeText(this, "called", Toast.LENGTH_SHORT).show();
		switch (item.getItemId()) {
		case R.id.mEvent:
			openJournalEvent(null, "new");
			return true;
		case R.id.mFeeling:
			openJournalFeeling(null, "new");
			return true;
		case R.id.mNote:
			openJournalNote(null, "new");
			return true;
		default:
			return false;
		}
	}
	
	private boolean mAdd = false;
	private boolean mDone = false;
	private boolean mEdit = false;
	private boolean mSetting = true;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.journal_main, menu);
		menu.findItem(R.id.mAdd).setVisible(mAdd);
		menu.findItem(R.id.mDone).setVisible(mDone);
		menu.findItem(R.id.mEdit).setVisible(mEdit);
		menu.findItem(R.id.action_settings).setVisible(mSetting);

		return true;
	}

	public void setSetting(String... strs) {
		mAdd = false;
		mDone = false;
		mSetting = false;
		mEdit = false;

		for (String str : strs) {
			if (str.equals("add")) {
				mAdd = true;
			} else if (str.equals("done")) {
				mDone = true;
			} else if (str.equals("edit")) {
				mEdit = true;
			} else if (str.equals("setting")) {
				mSetting = true;
			}

		}

		invalidateOptionsMenu();
	}

	public void openJournalComponent(JournalObj obj) {
		if (obj.type.equals("event")) {
			openJournalEvent((JournalEvent) obj, "open");
		} else if (obj.type.equals("feeling")) {
			viewJournalFeeling((JournalFeeling) obj);
		} else if (obj.type.equals("note")) {
			openJournalNote((JournalNote) obj, "open");
		} else if (obj.type.equals("task")) {

		}
	}
	
	public void viewJournalFeeling(JournalFeeling feeling){
		feelingView = FragmentFeelingView.newInstance(this, feeling);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frMain, feelingView).addToBackStack(null)
				.commit();
	}

	public void openJournalFeeling(JournalFeeling feeling, String mode) {
		feelingEdit = FragmentFeelingEdit.newInstance(this, feeling, mode);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frMain, feelingEdit).addToBackStack(null)
				.commit();
	}

	public void openJournalEvent(JournalEvent event, String mode) {
		eventEdit = FragmentEventEdit.newInstance(this, event, mode);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frMain, eventEdit).addToBackStack(null)
				.commit();
	}
	
	public void openJournalNote(JournalNote  note, String mode) {
		noteEdit = FragmentNoteEdit.newInstance(this, note, mode);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frMain, noteEdit).addToBackStack(null)
				.commit();
	}

	public void getEmotionfromList(String receiver) {
		emotionList = FragmentEmotionList.newInstance(this, "get", receiver);
		transaction = manager.beginTransaction();
		transaction.replace(R.id.frMain, emotionList).addToBackStack(null)
				.commit();
	}

	public void returnEmotionfromUser(String receiver, JournalEmotion emotion) {
		if (receiver.equals(FragmentFeelingEdit.id)){
			feelingEdit.addEmotion(emotion);
		}
	}
}
