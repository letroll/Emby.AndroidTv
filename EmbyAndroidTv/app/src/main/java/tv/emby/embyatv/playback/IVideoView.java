package tv.emby.embyatv.playback;

import android.media.MediaPlayer;

/**
 * Created by Eric on 6/13/2015.
 */
public interface IVideoView {
    int getDuration();
    int getCurrentPosition();
    boolean isPlaying();

    void start();
    void pause();
    void stopPlayback();
    void seekTo(int pos);
    void setVideoPath(String path);

    void setOnErrorListener(MediaPlayer.OnErrorListener listener);
    void setOnCompletionListener(MediaPlayer.OnCompletionListener listener);
    void setOnPreparedListener(MediaPlayer.OnPreparedListener listener);
    void setOnSeekCompleteListener(MediaPlayer mp, MediaPlayer.OnSeekCompleteListener listener);
}
