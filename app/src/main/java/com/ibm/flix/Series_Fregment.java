package com.ibm.flix;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Series_Fregment extends Fragment {


    public Series_Fregment() {
        // Required empty public constructor
    }

    private View view;
    private RecyclerView newly;
    private DatabaseReference mref;
    private LinearLayoutManager layoutManager,layoutManager2,layoutManager3,layoutManager4;
    private AdapterA adapter;
    private List<Seasons> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_series__fregment, container, false);
        list = new ArrayList<>();
        newly = view.findViewById(R.id.newly);
        layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,true);
        layoutManager.setStackFromEnd(true);
        newly.setLayoutManager(layoutManager);
        mref = FirebaseDatabase.getInstance().getReference();


        mref.child("Series").child("All").limitToLast(15).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Seasons message = dataSnapshot.getValue(Seasons.class);
                list.add(message);
                adapter = new AdapterA(list);
                newly.setAdapter(adapter);

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

        return view;
    }

}
