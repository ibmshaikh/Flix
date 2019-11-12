package com.ibm.flix;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Total_Episode extends AppCompatActivity {

    private List<modelEpisode> list;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private modelSeason object;
    private DatabaseReference mref;
    private Adapter_Episode adapter;
    private Seasons object1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total__episode);

        mref = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        object = (modelSeason) getIntent().getSerializableExtra("object");
        object1 = (Seasons) getIntent().getSerializableExtra("info");
        layoutManager = new GridLayoutManager(Total_Episode.this,3);
        recyclerView.setLayoutManager(layoutManager);

        mref.child("Series").child("Name").child(object.getSname()).child(object.getName()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String myParentNode = dataSnapshot.getKey();
                modelEpisode episodes = new modelEpisode();
                episodes.setName(myParentNode);
                episodes.setImg(object.getLink());
                episodes.setSname(object.getName());
                list.add(episodes);
                adapter = new Adapter_Episode(list,object1,object);
                recyclerView.setAdapter(adapter);

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
