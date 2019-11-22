package com.ibm.contentupload;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Movies_Fregment extends Fragment {


    public Movies_Fregment() {
        // Required empty public constructor
    }

    private View view;
    private RecyclerView newly,topp,bollywoode,hollywoode;
    private LinearLayoutManager layoutManager,layoutManager2,layoutManager3,layoutManager4;
    private MovieAdapter adapter;
    private List<movie> list,list2,list3,list4;
    //String url ="http://ec2-13-232-16-250.ap-south-1.compute.amazonaws.com:3000/getMovies";
    private DatabaseReference mref;
    private ProgressDialog dialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movies__fregment, container, false);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();

        dialog = new ProgressDialog(view.getContext());
        dialog.setMessage("Loading...");
        dialog.setCanceledOnTouchOutside(false);
        view.setVisibility(View.GONE);
        dialog.show();

        newly = view.findViewById(R.id.newly);
        topp = view.findViewById(R.id.top);
        bollywoode = view.findViewById(R.id.bollywood);
        hollywoode = view.findViewById(R.id.hollywood);

        layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,true);
        layoutManager2 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager3 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager4 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);


        layoutManager.setStackFromEnd(true);

        newly.setLayoutManager(layoutManager);
        topp.setLayoutManager(layoutManager2);
        bollywoode.setLayoutManager(layoutManager3);
        hollywoode.setLayoutManager(layoutManager4);


        mref = FirebaseDatabase.getInstance().getReference();

        final movie object = new movie();
        object.setName("Bahubali");
        object.setDescription("Good one");
        object.setGenre("Action");
        object.setImage("https://4.bp.blogspot.com/-Fxo_qnGJBj0/WRoDPNdlEII/AAAAAAAABF0/1mSHmv5gleQaCsHKEDgTB3DbNghjCXvZACLcB/s1600/logo_firebase_1920px_clr.png");
        object.setHollywood(false);
        object.setYear(2019);
        object.setRating(5);
        object.setLink("https://ibmbucketnew.s3.ap-south-1.amazonaws.com/1.1+HMI(human_machine_interaction)_IN_HINDI.mp4");
        object.setLanguage("English");

        //mref.child("Movies").child("All").push().setValue(object).addOnSuccessListener(new OnSuccessListener<Void>() {
        //    @Override
        //    public void onSuccess(Void aVoid) {
        //        if (object.isHollywood()){
        //            mref.child("Movies").child("Hollywood").push().setValue(object);
        //        }else {
        //            mref.child("Movies").child("Bollywood").push().setValue(object);
//
        //        }
        //    }
        //});
//

        mref.child("Movies").child("All").limitToLast(15).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                movie message = dataSnapshot.getValue(movie.class);
                list.add(message);
                adapter = new MovieAdapter(list);
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



        //TODO change quary to top rated
        mref.child("Movies").child("Bollywood").limitToLast(15).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                movie message = dataSnapshot.getValue(movie.class);
                list2.add(message);
                adapter = new MovieAdapter(list2);
                topp.setAdapter(adapter);
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

        mref.child("Movies").child("Bollywood").limitToLast(15).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                movie message = dataSnapshot.getValue(movie.class);
                list3.add(message);
                adapter = new MovieAdapter(list3);
                bollywoode.setAdapter(adapter);
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
        mref.child("Movies").child("Hollywood").limitToLast(15).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                movie message = dataSnapshot.getValue(movie.class);
                list4.add(message);
                adapter = new MovieAdapter(list4);
                hollywoode.setAdapter(adapter);
                dialog.dismiss();
                view.setVisibility(View.VISIBLE);
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



       /* RequestQueue queue = Volley.newRequestQueue(view.getContext());

        StringRequest strRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray newlya = null;
                            JSONArray top = null;
                            JSONArray bollywood = null;
                            JSONArray hollywood = null;
                            try {
                                newlya = jsonObject.getJSONArray("new");
                                for (int i = 0; i < newlya.length(); i++) {
                                    JSONObject jsonobject = newlya.getJSONObject(i);
                                    //String name = jsonobject.getString("Name");
                                    //String url = jsonobject.getString("Image");
                                    //Toast.makeText(view.getContext(),name,Toast.LENGTH_LONG).show();
                                    //list.add(new movie(name,jsonobject.getString("Language"),jsonobject.getString("Description"),url,jsonobject.getString("Link"),jsonobject.getInt("Rating")));
                                    movie data = new Gson().fromJson(String.valueOf(jsonobject), movie.class);
                                    list.add(data);
                                    adapter = new Adapter(list);
                                    newly.setAdapter(adapter);
                                }
                                top = jsonObject.getJSONArray("top");
                                for (int i = 0; i < top.length(); i++) {
                                    JSONObject jsonobject = top.getJSONObject(i);
                                    //String name = jsonobject.getString("Name");
                                    //String url = jsonobject.getString("Image");
                                    //Toast.makeText(view.getContext(),name,Toast.LENGTH_LONG).show();
                                    //list.add(new movie(name,jsonobject.getString("Language"),jsonobject.getString("Description"),url,jsonobject.getString("Link"),jsonobject.getInt("Rating")));
                                    movie data = new Gson().fromJson(String.valueOf(jsonobject), movie.class);
                                    list2.add(data);
                                    adapter = new Adapter(list2);
                                    topp.setAdapter(adapter);
                                }
                                bollywood = jsonObject.getJSONArray("bolywood");
                                for (int i = 0; i < bollywood.length(); i++) {
                                    JSONObject jsonobject = bollywood.getJSONObject(i);
                                    //String name = jsonobject.getString("Name");
                                    //String url = jsonobject.getString("Image");
                                    //Toast.makeText(view.getContext(),name,Toast.LENGTH_LONG).show();
                                    //list.add(new movie(name,jsonobject.getString("Language"),jsonobject.getString("Description"),url,jsonobject.getString("Link"),jsonobject.getInt("Rating")));
                                    movie data = new Gson().fromJson(String.valueOf(jsonobject), movie.class);
                                    list3.add(data);
                                    adapter = new Adapter(list3);
                                    bollywoode.setAdapter(adapter);
                                }
                                hollywood = jsonObject.getJSONArray("hollywood");
                                for (int i = 0; i < bollywood.length(); i++) {
                                    JSONObject jsonobject = bollywood.getJSONObject(i);
                                    //String name = jsonobject.getString("Name");
                                    //String url = jsonobject.getString("Image");
                                    //Toast.makeText(view.getContext(),name,Toast.LENGTH_LONG).show();
                                    //list.add(new movie(name,jsonobject.getString("Language"),jsonobject.getString("Description"),url,jsonobject.getString("Link"),jsonobject.getInt("Rating")));
                                    movie data = new Gson().fromJson(String.valueOf(jsonobject), movie.class);
                                    list4.add(data);
                                    adapter = new Adapter(list4);
                                    hollywoode.setAdapter(adapter);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //try {
                        //    JSONArray jsonarray = new JSONArray(response);
                        //    for (int i = 0; i < jsonarray.length(); i++) {
                        //        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        //        //String name = jsonobject.getString("Name");
                        //        //String url = jsonobject.getString("Image");
                        //        //Toast.makeText(view.getContext(),name,Toast.LENGTH_LONG).show();
                        //        //list.add(new movie(name,jsonobject.getString("Language"),jsonobject.getString("Description"),url,jsonobject.getString("Link"),jsonobject.getInt("Rating")));
                        //        movie data = new Gson().fromJson(String.valueOf(jsonobject), movie.class);
                        //        list.add(data);
                        //        adapter = new Adapter(list);
                        //        newly.setAdapter(adapter);
                        //    }
                        //} catch (JSONException e) {
                        //    e.printStackTrace();
                        //}
//

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

        };

        queue.add(strRequest);*/

        return view;
    }

}