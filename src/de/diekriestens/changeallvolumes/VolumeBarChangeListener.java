package de.diekriestens.changeallvolumes;

import android.media.AudioManager;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VolumeBarChangeListener implements OnSeekBarChangeListener {
	/** LogCat prefix */
	private static final String TAG = "VMA_VBChange";

	/** store stremId and AudioManager */
	private int streamId;
	private AudioManager aManager;

	public VolumeBarChangeListener(AudioManager aManager,
			SeekBar bar,
			int streamId) {
		/** constructor */
		Log.d(TAG, "Constructor called for " + streamId);
		this.streamId = streamId;
		this.aManager = aManager;

		// TODO Let ProgressBar work between 0 and 100 and map the value to one of 0..MaxVolume this will make the bars smoother

		Log.i(TAG, String.format("Stream: %d, MaxVolume: %d", this.streamId,
				aManager.getStreamMaxVolume(streamId)));
		// sets the range between 0 and the max volume
		bar.setMax(aManager.getStreamMaxVolume(streamId));
		// set the seek bar progress to 1
		bar.setKeyProgressIncrement(1);
		// sets the progress of the seek bar based on the system's volume
		bar.setProgress(aManager.getStreamVolume(streamId));
		Log.d(TAG, "Constructor called for " + streamId);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		Log.d(TAG, String.format("SeekBar: %d, Progress: %d", this.streamId, progress));
		aManager.setStreamVolume(this.streamId, progress, AudioManager.FLAG_PLAY_SOUND);
		// AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND);
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
