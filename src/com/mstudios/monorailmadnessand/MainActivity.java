package com.mstudios.monorailmadnessand;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}


}
