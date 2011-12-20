package de.thisiskrid.volchanger;

import android.media.AudioManager;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VolumeBarChangeListener implements OnSeekBarChangeListener {
	// for logging
	private static final String TAG = "VolBarChListener";

	private int streamId;
	private AudioManager amanager;

	public VolumeBarChangeListener(AudioManager amanager, SeekBar bar, int streamId) {
		Log.d(TAG, "Constructor called for "+streamId);
		this.streamId = streamId;
		this.amanager = amanager;
		
		//TODO Let ProgressBar work between 0 and 100 and map the value to one of 0..MaxVolume this will make the bars smoother
		
		Log.i(TAG, String.format("Stream: %d, MaxVolume: %d", this.streamId, amanager.getStreamMaxVolume(streamId)));
        //sets the range between 0 and the max volume
        bar.setMax(amanager.getStreamMaxVolume(streamId));
        //set the seek bar progress to 1
        bar.setKeyProgressIncrement(1);
        //sets the progress of the seek bar based on the system's volume
        bar.setProgress(amanager.getStreamVolume(streamId));

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		Log.d(TAG, String.format("SeekBar: %d, Progress: %d", this.streamId, progress));
		//amanager.setStreamVolume(this.streamId, progress, AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
		amanager.setStreamVolume(this.streamId, progress, AudioManager.FLAG_PLAY_SOUND);

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
