package com.ibm.contentupload;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
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

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class Upload_Movies extends AppCompatActivity{

    public String genre, language, hb,name,desc,year,image,link;
    private Spinner spinner_genre,spinner_language,spinner_hb;
    private EditText edtname,edtdesc,edtyear,edtimage,edtlink;
    private Button upload,vidverify,imgverify;
    private DatabaseReference mref;
    private ProgressDialog dialog;
    private ImageView img;
    JCVideoPlayerStandard jcVideoPlayerStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__movies);

        mref = FirebaseDatabase.getInstance().getReference();
        dialog = new ProgressDialog(Upload_Movies.this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Please wait while uploading a movie....");
        dialog.setCanceledOnTouchOutside(false);
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);


        spinner_genre = findViewById(R.id.genre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_genre.setAdapter(adapter);
        spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Upload_Movies.this, text, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Upload_Movies.this, text, Toast.LENGTH_SHORT).show();
                language = text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_hb = findViewById(R.id.hb);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hb, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hb.setAdapter(adapter2);
        spinner_hb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Upload_Movies.this, text, Toast.LENGTH_SHORT).show();
                hb = text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtname = findViewById(R.id.name);
        edtdesc = findViewById(R.id.desc);
        edtyear = findViewById(R.id.year);
        edtimage = findViewById(R.id.image);
        edtlink = findViewById(R.id.link);
        upload = findViewById(R.id.upload);
        vidverify = findViewById(R.id.vidverify);
        img = findViewById(R.id.img);
        imgverify = findViewById(R.id.imgverify);
        imgverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtname.getText().toString().isEmpty() && !edtimage.getText().toString().isEmpty()){
                    img.setVisibility(View.VISIBLE);
                    Glide.with(Upload_Movies.this).load(edtimage.getText().toString()).into(img);


                }

            }
        });
        vidverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtname.getText().toString().isEmpty() && !edtimage.getText().toString().isEmpty() && !edtlink.getText().toString().isEmpty())
                jcVideoPlayerStandard.setVisibility(View.VISIBLE);
                jcVideoPlayerStandard.setUp(edtlink.getText().toString()
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, edtname.getText().toString().trim());
                jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(edtimage.getText().toString()));
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                name = edtname.getText().toString().trim();
                desc = edtdesc.getText().toString().trim();
                year = edtyear.getText().toString().trim();
                image = edtimage.getText().toString().trim();
                link = edtlink.getText().toString().trim();
                final movie object = new movie();
                object.setName(name);
                object.setDescription(desc);
                object.setGenre(genre);
                object.setImage(image);
                object.setYear(Integer.parseInt(year));
                object.setRating(0);
                object.setLink(link);
                object.setLanguage(language);
                if (hb.equals("Hollywood")){
                    object.setHollywood(true);
                    mref.child("Movies").child("All").push().setValue(object).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mref.child("Movies").child("Hollywood").push().setValue(object);
                            Toast.makeText(Upload_Movies.this,"Successfull uploaded",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
                }else {
                    object.setHollywood(false);
                    mref.child("Movies").child("All").push().setValue(object).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mref.child("Movies").child("Bollywood").push().setValue(object);
                            Toast.makeText(Upload_Movies.this,"Successfull uploaded",Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }
                    });
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
