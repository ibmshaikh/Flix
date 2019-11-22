package com.ibm.contentupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class Upload_series extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Series> list;
    private Adapter recylerviewAdpter;
    private Button upload,add;
    public String genre, language, hb,name,desc,year,image,link;
    private Spinner spinner_genre,spinner_language;
    private EditText edtname,edtdesc,edtyear,edtimage,edtlink;
    private Button imgverify;
    private DatabaseReference mref;
    private ProgressDialog dialog;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_series);

        recyclerView = findViewById(R.id.recyclerview);
        edtname = findViewById(R.id.name);
        edtdesc = findViewById(R.id.desc);
        edtyear = findViewById(R.id.year);
        edtimage = findViewById(R.id.image);
        add = findViewById(R.id.add);
        upload = findViewById(R.id.upload);
        imgverify = findViewById(R.id.imgverify);
        img = findViewById(R.id.img);
        list = new ArrayList<>();
        dialog = new ProgressDialog(Upload_series.this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Please wait while uploading a movie....");
        dialog.setCanceledOnTouchOutside(false);
        mref = FirebaseDatabase.getInstance().getReference();



        spinner_genre = findViewById(R.id.genre);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_genre.setAdapter(adapter);
        spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Upload_series.this, text, Toast.LENGTH_SHORT).show();
                genre = text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_language = findViewById(R.id.Language);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.languge, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_language.setAdapter(adapter1);
        spinner_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Upload_series.this, text, Toast.LENGTH_SHORT).show();
                language = text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imgverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtname.getText().toString().isEmpty() && !edtimage.getText().toString().isEmpty()){
                    img.setVisibility(View.VISIBLE);
                    Glide.with(Upload_series.this).load(edtimage.getText().toString()).into(img);


                }

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(Upload_series.this);
                View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
                final EditText name = alertLayout.findViewById(R.id.name);
                final EditText link = alertLayout.findViewById(R.id.link);

                // this is set the view from XML inside AlertDialog
                AlertDialog.Builder alert = new AlertDialog.Builder(Upload_series.this);
                alert.setTitle("Info");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Upload_series.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Series series = new Series();
                        int a = list.size()+1;
                        series.setNumber("Episode "+ a);
                        series.setName(name.getText().toString().trim());
                        series.setLink(link.getText().toString().trim());
                        list.add(series);
                        adapter.notifyDataSetChanged();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });



        GridLayoutManager gm = new GridLayoutManager(Upload_series.this,3);
        recyclerView.setLayoutManager(gm);
        recylerviewAdpter = new Adapter(list);
        recyclerView.setAdapter(recylerviewAdpter);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
                name = edtname.getText().toString().trim();
                desc = edtdesc.getText().toString().trim();
                year = edtyear.getText().toString().trim();
                image = edtimage.getText().toString().trim();
                final Seasons seasons = new Seasons();

                seasons.setName(name);
                seasons.setDescription(desc);
                seasons.setYear(Integer.parseInt(year));
                seasons.setImage(image);
                seasons.setGenre(genre);
                seasons.setLanguage(language);
                seasons.setRating(0);

                mref.child("Series").child("All").push().setValue(seasons).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        mref.child("Series").child("Name").child(name).child("info").setValue(seasons);

                        for (int i=0;i<list.size();i++){

                            mref.child("Series").child("Name").child(name).child("Season 1").child(list.get(i).getNumber()).setValue(list.get(i));
                            Toast.makeText(Upload_series.this,"Successfull uploaded",Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }

                        //mref.child("Series").child("Name").child(name)

                    }
                });


            }
        });


    }
}
