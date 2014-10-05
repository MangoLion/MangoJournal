package com.mangolion.mangojournal.adapter.emotion;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.emotion.JournalEmotion;

public class EmotionViewAdapter  extends ArrayAdapter<JournalEmotion>{

	public List<JournalEmotion> emotions;
	public static int resId = R.layout.emotionviewitem;
	ActivityMain activity;
	public boolean usePercentage;

	public EmotionViewAdapter(ActivityMain activity,
			List<JournalEmotion> emotions, boolean usePercentage) {
		super(activity, resId, emotions);
		this.emotions = emotions;
		this.activity = activity;
		this.usePercentage = usePercentage;
	}

	TextView tvEmotion, tvIntensity, tvPercentage, tvDesc;
	ProgressBar pbIntensity, pbPercentage;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final JournalEmotion emotion = emotions.get(position);

		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(activity).inflate(resId, null);
		}
		tvEmotion = (TextView) view.findViewById(R.id.tvEmotion);
		tvDesc = (TextView) view.findViewById(R.id.tvDesc);
		tvIntensity = (TextView) view.findViewById(R.id.tvIntensity);
		tvPercentage = (TextView) view.findViewById(R.id.tvPercentage);
		pbIntensity = (ProgressBar) view.findViewById(R.id.pbIntensity);
		pbPercentage = (ProgressBar) view.findViewById(R.id.pbPercentage);

		tvIntensity.setText("Intensity: " + String.valueOf(emotion.intensity));
		tvPercentage.setText("Percentage: "
				+ String.valueOf(emotion.percentage));

		if (!usePercentage) {
			tvPercentage.setVisibility(View.GONE);
			pbPercentage.setVisibility(View.GONE);
		}

		tvDesc.setText(emotion.desc);
		tvEmotion.setText(emotion.name);
		Drawable icon = activity.getResources().getDrawable(
				(Integer) emotion.icon);
		icon.setBounds(0, 0, icon.getIntrinsicWidth(),
				icon.getIntrinsicHeight());
		tvEmotion.setCompoundDrawables(null, null, icon, null);
		
		pbIntensity.setProgress(emotion.intensity);
		pbPercentage.setProgress(emotion.percentage);
		return view;
	}

}