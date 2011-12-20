package de.thisiskrid.volchanger;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SeekBar;

public class VolchangerActivity extends Activity {
	// for logging
	private static final String TAG = "VolChanger";

	// audiomanager
	private AudioManager audiomanager;
	private SeekBar alarmBar;
	private SeekBar dtmfBar;
	private SeekBar musicBar;
	private SeekBar notifyBar;
	private SeekBar ringBar;
	private SeekBar systemBar;
	private SeekBar callBar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate gerufen");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// at first get the audio manager
		audiomanager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

		// find right bar
		alarmBar = (SeekBar) findViewById(R.id.AlarmSeekBar);
		// now connect seek bars with it's corresponding volume
		alarmBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, alarmBar, AudioManager.STREAM_ALARM));

		dtmfBar = (SeekBar) findViewById(R.id.DTMFSeekBar);
		dtmfBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, dtmfBar, AudioManager.STREAM_DTMF));

		musicBar = (SeekBar) findViewById(R.id.MusicSeekBar);
		musicBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, musicBar, AudioManager.STREAM_MUSIC));
		
		notifyBar = (SeekBar) findViewById(R.id.NotifySeekBar);
		notifyBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, notifyBar, AudioManager.STREAM_NOTIFICATION));
		
		ringBar = (SeekBar) findViewById(R.id.RingSeekBar);
		ringBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, ringBar, AudioManager.STREAM_RING));
		
		systemBar = (SeekBar) findViewById(R.id.SystemSeekBar);
		systemBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, systemBar, AudioManager.STREAM_SYSTEM));

		callBar = (SeekBar) findViewById(R.id.CallSeekBar);
		callBar.setOnSeekBarChangeListener(new VolumeBarChangeListener(audiomanager, callBar, AudioManager.STREAM_VOICE_CALL));
		
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		//if one of the volume keys were pressed
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP)
		{
			Log.i(TAG, "KeyEvent");
			//change the seek bar progress indicator position
			ringBar.setProgress(audiomanager.getStreamVolume(AudioManager.STREAM_RING));
		}
		//propagate the key event
		return super.onKeyDown(keyCode, event);
	}

}