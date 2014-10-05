package com.mangolion.mangojournal.adapter.emotion;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.emotion.JournalEmotion;

public class EmotionEditAdapter extends ArrayAdapter<JournalEmotion>{

	public List<JournalEmotion> emotions;
	public static int resId = R.layout.emotionedititem;
	ActivityMain activity;
	public boolean usePercentage;

	public EmotionEditAdapter(ActivityMain activity,
			List<JournalEmotion> emotions, boolean usePercentage) {
		super(activity, resId, emotions);
		this.emotions = emotions;
		this.activity = activity;
		this.usePercentage = usePercentage;
	}

	TextView tvEmotion, tvIntensity, tvPercentage;
	EditText etDesc;
	SeekBar sbIntensity, sbPercentage;
	CheckBox cbShow;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final JournalEmotion emotion = emotions.get(position);

		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(activity).inflate(resId, null);
		}
		tvEmotion = (TextView) view.findViewById(R.id.tvEmotion);
		etDesc = (EditText) view.findViewById(R.id.etDesc);
		tvIntensity = (TextView) view.findViewById(R.id.tvIntensity);
		tvPercentage = (TextView) view.findViewById(R.id.tvPercentage);
		sbIntensity = (SeekBar) view.findViewById(R.id.sbIntensity);
		sbPercentage = (SeekBar) view.findViewById(R.id.sbPercentage);
		cbShow = (CheckBox) view.findViewById(R.id.cbShow);

		tvIntensity.setText("Intensity: " + String.valueOf(emotion.intensity));
		tvPercentage.setText("Percentage: "
				+ String.valueOf(emotion.percentage));

		if (!usePercentage) {
			tvPercentage.setVisibility(View.GONE);
			sbPercentage.setVisibility(View.GONE);
		}

		etDesc.setText(emotion.desc);
		tvEmotion.setText(emotion.name);
		Drawable icon = activity.getResources().getDrawable(
				(Integer) emotion.icon);
		icon.setBounds(0, 0, icon.getIntrinsicWidth(),
				icon.getIntrinsicHeight());
		tvEmotion.setCompoundDrawables(null, null, icon, null);
		
		cbShow.setChecked(emotion.showinEntry);
		cbShow.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				emotion.showinEntry = isChecked;
			}
		});
		
		etDesc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		sbIntensity.setProgress(emotion.intensity);
		sbIntensity.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				emotion.intensity = sbIntensity.getProgress();
			}
		});
		sbPercentage.setProgress(emotion.percentage);
		sbPercentage.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				emotion.percentage = sbIntensity.getProgress();
				
			}
		});

		return view;
	}

}
