package com.ibm.flix;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class Series_Play extends AppCompatActivity {

    private Seasons info;
    private String url = "https://ibmbucketnew.s3.ap-south-1.amazonaws.com/1.1+HMI(human_machine_interaction)_IN_HINDI.mp4";
    private TextView number,episodename,name,year,genre,desc;
    private RatingBar rating;
    private modelEpisode object;
    private DatabaseReference mref;
    private modelSeason modelSeason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series__play);

        number = findViewById(R.id.number);
        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        desc = findViewById(R.id.desc);
        rating = findViewById(R.id.rating);
        episodename = findViewById(R.id.episodename);

        info = (Seasons) getIntent().getSerializableExtra("info");
        object = (modelEpisode) getIntent().getSerializableExtra("object");
        modelSeason = (modelSeason) getIntent().getSerializableExtra("objectSeason");

        number.setText(modelSeason.getName()+" "+object.getName());
        name.setText(info.getName());
        year.setText(String.valueOf(info.getYear()));
        genre.setText(info.getGenre());
        desc.setText(info.getDescription());
        rating.setRating(info.getRating());
        final JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);


        mref = FirebaseDatabase.getInstance().getReference();
        mref.child("Series").child("Name").child(info.getName()).child(modelSeason.getName()).child(object.getName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                episodename.setText(dataSnapshot.child("name").getValue().toString());

                jcVideoPlayerStandard.setUp(dataSnapshot.child("link").getValue().toString()
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, object.getName());
                jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(info.getImage()));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
