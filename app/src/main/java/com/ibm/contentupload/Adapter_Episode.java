package com.ibm.contentupload;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapter_Episode extends RecyclerView.Adapter<Adapter_Episode.viewholder> {

    private List<modelEpisode> list;
    private Context ctx;
    private Seasons object1;
    private modelSeason objectSeason;
    public Adapter_Episode(List<modelEpisode> list,Seasons object1,modelSeason objectSeason) {
        this.list = list;
        this.object1 = object1;
        this.objectSeason = objectSeason;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_list, viewGroup, false);
        ctx = viewGroup.getContext();
        return new Adapter_Episode.viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        final DatabaseReference mref = FirebaseDatabase.getInstance().getReference();

        viewholder.name.setText(list.get(i).getName());
        final modelEpisode object = list.get(i);
        Glide.with(ctx).load(list.get(i).getImg()).into(viewholder.imageView);
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ctx,Series_Play.class);
                i.putExtra("object", object);
                i.putExtra("info",object1);
                i.putExtra("objectSeason",objectSeason);
                ctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);

        }
    }
}
