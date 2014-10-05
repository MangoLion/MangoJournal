package com.mangolion.mangojournal.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mangolion.mangojournal.R;
import com.mangolion.mangojournal.activity.ActivityMain;
import com.mangolion.mangojournallib.feeling.JournalFeeling;

public class JournalFeelingAdapter {
	public static int resId = R.layout.feelingitemmode1;

	TextView tvName, tvEmotion;
	ImageView ivIcon;

	public View getView(JournalFeeling feeling, View convertView,
			ActivityMain activity) {

		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(activity).inflate(resId, null);
		}

		tvName = (TextView) view.findViewById(R.id.tvName);
		ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
		tvEmotion = (TextView) view.findViewById(R.id.tvEmotion);

		if (feeling.emotionShow != null)
			tvName.setText(feeling.emotionShow.name);

		ivIcon.setImageResource((Integer) feeling.icon);
		if (feeling.emotionShow.icon != null) {
			Drawable icon = activity.getResources().getDrawable(
					(Integer) feeling.emotionShow.icon);
			icon.setBounds(0, 0, icon.getIntrinsicWidth(),
					icon.getIntrinsicHeight());
			tvEmotion.setCompoundDrawables(null, null, icon, null);
		}
		return view;
	}
}
