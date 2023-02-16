package com.example.laborator2_videoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private int currentVideoIndex = 0;
    private int[] videoIds = { R.raw.the_lamma_song, R.raw.im_a_bee, R.raw.my_name_is_giovanni_giorgio, R.raw.bigger_better_stronger, R.raw.im_a_sheep};

    private void playNextVideo() {
        if (currentVideoIndex < videoIds.length - 1) {
            currentVideoIndex++;
        } else {
            currentVideoIndex = 0;
        }
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + videoIds[currentVideoIndex]));
        videoView.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);

        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        playNextVideo();
        videoView.setOnCompletionListener(mp -> playNextVideo());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            videoView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}