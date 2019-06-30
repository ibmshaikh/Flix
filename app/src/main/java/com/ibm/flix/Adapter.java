package com.ibm.flix;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

    private List<movie> list;
    private Context ctx;
    public Adapter(List<movie> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_list, viewGroup, false);
        ctx = viewGroup.getContext();
        return new Adapter.viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {

        viewholder.name.setText(list.get(i).getName());
        final movie object = list.get(i);
        Glide.with(ctx).load(list.get(i).getImage()).into(viewholder.imageView);
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,Movies_Play.class);
                i.putExtra("object", object);
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
