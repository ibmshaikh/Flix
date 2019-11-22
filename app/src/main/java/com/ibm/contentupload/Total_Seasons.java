package com.ibm.contentupload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Total_Seasons extends AppCompatActivity {


    private Seasons object;
    private String url = "https://ibmbucketnew.s3.ap-south-1.amazonaws.com/1.1+HMI(human_machine_interaction)_IN_HINDI.mp4";
    private TextView name,year,genre,desc;
    private RatingBar rating;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private DatabaseReference mref;
    private List<modelSeason> list;
    private Adapter_Seasons adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total__seasons);

        object = (Seasons) getIntent().getSerializableExtra("object");

        list = new ArrayList<>();

        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        desc = findViewById(R.id.desc);
        rating = findViewById(R.id.rating);

        name.setText(object.getName());
        year.setText(String.valueOf(object.getYear()));
        genre.setText(object.getGenre());
        desc.setText(object.getDescription());
        rating.setRating(object.getRating());

        recyclerView = findViewById(R.id.seasons);
        layoutManager = new GridLayoutManager(Total_Seasons.this,3);
        recyclerView.setLayoutManager(layoutManager);
        mref = FirebaseDatabase.getInstance().getReference();

        mref.child("Series").child("Name").child(object.getName()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String myParentNode = dataSnapshot.getKey();
                if (!myParentNode.equals("info")){
                    modelSeason season = new modelSeason();
                    season.setName(myParentNode);
                    season.setLink(object.getImage());
                    season.setSname(object.getName());
                    list.add(season);
                    adapter = new Adapter_Seasons(list,object);
                    recyclerView.setAdapter(adapter);
                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
