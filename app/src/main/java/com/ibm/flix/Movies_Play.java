package com.ibm.flix;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;


import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class Movies_Play extends AppCompatActivity {

    //private ImageView playBtn;
    //private VideoView mainVideoView;
    //private TextView currentTime,totalTime;
    //private ProgressBar currentProgressbar,loading;
    //private Uri videoUri;
    //private boolean isPlaying;
    //private int current = 0,total=0;
    //private ParsingVideoView mVideoView;
    private movie object;
    private String url = "https://ibmbucketnew.s3.ap-south-1.amazonaws.com/1.1+HMI(human_machine_interaction)_IN_HINDI.mp4";
    private TextView name,year,genre,desc;
    private RatingBar rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies__play);

        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        desc = findViewById(R.id.desc);
        rating = findViewById(R.id.rating);

        object = (movie) getIntent().getSerializableExtra("object");

        name.setText(object.getName());
        //year.setText(object.getYear());
        genre.setText(object.getGenre());
        desc.setText(object.getDescription());
        //rating.setRating(object.getRating());

        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.setUp(object.getLink()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, object.getName());
        jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(object.getImage()));
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
