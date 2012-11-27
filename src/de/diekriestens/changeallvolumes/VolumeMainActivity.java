package de.diekriestens.changeallvolumes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.SeekBar;

public class VolumeMainActivity extends Activity {
	/** LogCat prefix */
	private static final String TAG = "VMA_Main";

	/** several private members */
	private AudioManager audioManager;
	private SeekBar alarmBar;
	private SeekBar dtmfBar;
	private SeekBar musicBar;
	private SeekBar notifyBar;
	private SeekBar ringBar;
	private SeekBar systemBar;
	private SeekBar callBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volume_main);
		Log.d(TAG, "onCreate finished");
	}

	@Override
	public void onStart() {
		Log.d(TAG, "onStart called");
		super.onStart();

		// first get the audio manager
		audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

		// TODO Use AdaptorView to avoid repeated code in the layout and here

		// find right bar
		alarmBar = (SeekBar) findViewById(R.id.SeekBar01);
		// now connect seek bars with it's corresponding volume
		alarmBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(
				audioManager, alarmBar, AudioManager.STREAM_ALARM));

		dtmfBar = (SeekBar) findViewById(R.id.SeekBar02);
		dtmfBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(
				audioManager, dtmfBar, AudioManager.STREAM_DTMF));

		musicBar = (SeekBar) findViewById(R.id.SeekBar03);
		musicBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(
				audioManager, musicBar, AudioManager.STREAM_MUSIC));

		notifyBar = (SeekBar) findViewById(R.id.SeekBar04);
		notifyBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(
				audioManager, notifyBar, AudioManager.STREAM_NOTIFICATION));

		ringBar = (SeekBar) findViewById(R.id.SeekBar05);
		ringBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(
				audioManager, ringBar, AudioManager.STREAM_RING));

		systemBar = (SeekBar) findViewById(R.id.SeekBar06);
		systemBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(
				audioManager, systemBar, AudioManager.STREAM_SYSTEM));

		callBar = (SeekBar) findViewById(R.id.SeekBar07);
		callBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(
				audioManager, callBar, AudioManager.STREAM_VOICE_CALL));
		Log.d(TAG, "onStart finished");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "KeyEvent" + keyCode);
		int level = audioManager.getStreamVolume(AudioManager.STREAM_RING);
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			Log.v(TAG, "KEYCODE_VOLUME_DOWN");
			if (level > 0){
				level -= 1;
			}
			break;
		case KeyEvent.KEYCODE_VOLUME_UP:
			Log.v(TAG, "KEYCODE_VOLUME_UP");
			if (level < audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)){
				level += 1;
			}
			break;
		default:
			Log.v(TAG, "keyCode not handeled");
			return false;
		}
		audioManager.setStreamVolume(AudioManager.STREAM_RING, level, AudioManager.FLAG_SHOW_UI);
		ringBar.setProgress(level);
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_volume_main, menu);
		return true;
	}

}
