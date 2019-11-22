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

import java.util.List;

public class Adapter_Seasons extends RecyclerView.Adapter<Adapter_Seasons.viewholder> {

    private List<modelSeason> list;
    private Context ctx;
    private Seasons object1;
    public Adapter_Seasons(List<modelSeason> list,Seasons object1) {
        this.list = list;
        this.object1 = object1;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_list, viewGroup, false);
        ctx = viewGroup.getContext();
        return new Adapter_Seasons.viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {

        viewholder.name.setText(list.get(i).getName());
        final modelSeason object = list.get(i);
        Glide.with(ctx).load(list.get(i).getLink()).into(viewholder.imageView);
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,Total_Episode.class);
                i.putExtra("object", object);
                i.putExtra("info",object1);
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
