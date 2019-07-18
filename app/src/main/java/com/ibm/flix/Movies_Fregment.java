package com.ibm.flix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private Adapter adapter;
    private List<movie> list,list2,list3,list4;
    String url ="http://ec2-13-232-16-250.ap-south-1.compute.amazonaws.com:3000/getMovies";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movies__fregment, container, false);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();

        newly = view.findViewById(R.id.newly);
        topp = view.findViewById(R.id.top);
        bollywoode = view.findViewById(R.id.bollywood);
        hollywoode = view.findViewById(R.id.hollywood);

        layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager2 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager3 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager4 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);


        
        newly.setLayoutManager(layoutManager);
        topp.setLayoutManager(layoutManager2);
        bollywoode.setLayoutManager(layoutManager3);
        hollywoode.setLayoutManager(layoutManager4);



        RequestQueue queue = Volley.newRequestQueue(view.getContext());

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

        queue.add(strRequest);

        return view;
    }

}
  /*for (int i = 0; i < jsonarray.length(); i++) {
        JSONObject jsonobject = jsonarray.getJSONObject(i);
        String name = jsonobject.getString("Name");
        String url = jsonobject.getString("Image");
        Toast.makeText(view.getContext(),name,Toast.LENGTH_LONG).show();
        list.add(new movie(name,"English","Time machine wali movie",url,"dughbd",5));
        }*/