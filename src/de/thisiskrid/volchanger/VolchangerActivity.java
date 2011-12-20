package de.thisiskrid.volchanger;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class VolchangerActivity extends Activity {
	// for logging
	private static final String TAG = "VolChanger";

	// audiomanager
	private AudioManager audiomanager;
	private SeekBar alarmBar;
	private SeekBar dtmfBar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate gerufen");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// at first get the audio manager
		try {
			audiomanager = (AudioManager) this
					.getSystemService(Context.AUDIO_SERVICE);
		} catch (Exception ex) {
			Log.wtf(TAG, "Did not get the AudioManager", ex);
		}

		// find right bar
		alarmBar = (SeekBar) findViewById(R.id.AlarmSeekBar);
		// now connect seek bars with it's corresponding volume
		alarmBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, alarmBar, AudioManager.STREAM_ALARM));

		dtmfBar = (SeekBar) findViewById(R.id.AlarmSeekBar);
		dtmfBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, dtmfBar, AudioManager.STREAM_DTMF));

	}

	@Override
	protected void onStart() {
		Log.i(TAG, "onStart gerufen");
		super.onStart();
		// The activity is about to become visible.
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume gerufen");
		super.onResume();
		// The activity has become visible (it is now "resumed").
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "onPause gerufen");
		super.onPause();
		// Another activity is taking focus (this activity is about to be
		// "paused").
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop gerufen");
		super.onStop();
		// The activity is no longer visible (it is now "stopped")
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy gerufen");
		super.onDestroy();
		// The activity is about to be destroyed.
	}
}