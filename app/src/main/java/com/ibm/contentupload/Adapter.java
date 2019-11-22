package com.ibm.contentupload;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

    private List<Series> list;

    private Context ctx;

    public Adapter(List<Series> list) {
        this.list = list;
    }

    @Override
    public Adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        ctx = parent.getContext();
        return new Adapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewholder holder, int position) {

            holder.name.setText(list.get(position).getName());

    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        private static ImageView imageView;
        private static TextView name;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);

        }
    }
}
