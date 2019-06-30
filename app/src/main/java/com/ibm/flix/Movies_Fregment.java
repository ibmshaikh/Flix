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
    private RecyclerView newly;
    private LinearLayoutManager layoutManager;
    private Adapter adapter;
    private List<movie> list;
    String url ="http://ec2-13-232-16-250.ap-south-1.compute.amazonaws.com:3000/getMovies";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movies__fregment, container, false);
        list = new ArrayList<>();
        newly = view.findViewById(R.id.newly);
        layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        newly.setLayoutManager(layoutManager);
        //list.add(new movie("Dark","English","Time machine wali movie","https://cdn-images-1.medium.com/max/1200/1*l0TB3UG7S8rSw3d5_yiVpw.png","dughbd",5));
        //list.add(new movie("Dark","English","Time machine wali movie","https://cdn-images-1.medium.com/max/1200/1*l0TB3UG7S8rSw3d5_yiVpw.png","dughbd",5));
        //list.add(new movie("Dark","English","Time machine wali movie","https://cdn-images-1.medium.com/max/1200/1*l0TB3UG7S8rSw3d5_yiVpw.png","dughbd",5));
        list.add(new movie("Dark","English","Time machine wali movie","https://cdn-images-1.medium.com/max/1200/1*l0TB3UG7S8rSw3d5_yiVpw.png","dughbd","Advebture",8,8));

        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                //String name = jsonobject.getString("Name");
                                //String url = jsonobject.getString("Image");
                                //Toast.makeText(view.getContext(),name,Toast.LENGTH_LONG).show();
                                //list.add(new movie(name,jsonobject.getString("Language"),jsonobject.getString("Description"),url,jsonobject.getString("Link"),jsonobject.getInt("Rating")));
                                movie data = new Gson().fromJson(String.valueOf(jsonobject), movie.class);
                                list.add(data);
                                adapter = new Adapter(list);
                                newly.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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