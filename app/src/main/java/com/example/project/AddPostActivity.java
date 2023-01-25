package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//import android.widget.ImageView;

public class AddPostActivity extends AppCompatActivity {
    private ArrayList<Post> posts;
    static final String POST_CONS_NAME = "post";
    EditText et_name, et_body, et_date, et_followers, et_following, et_posts;
    Button btn_save;


    final int CAPTURE_REC_CODE = 1;
    ImageView img_view ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        et_name = findViewById(R.id.add_post_name);
        et_body = findViewById(R.id.add_post_body);
        et_date = findViewById(R.id.add_post_date);
        et_followers = findViewById(R.id.add_post_followers);
        et_following = findViewById(R.id.add_post_following);
        et_posts = findViewById(R.id.add_post_posts);
        img_view = findViewById(R.id.img_view);

        btn_save = findViewById(R.id.add_btn_save);

/*
        Log.d("et_name", "Ziyad Alaa");
        Log.d("et_body", "alaa alaa alaa");
        Log.d("et_date", "21-May-2121");
        Log.d("et_followers", "20");
        Log.d("et_following", "30");
        Log.d("et_posts", "40");


 */

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name = et_name.getText().toString();
                String body = et_body.getText().toString();
                String date = et_date.getText().toString();
                int followers = Integer.parseInt(et_followers.getText().toString());
                int following = Integer.parseInt(et_following.getText().toString());
                int post = Integer.parseInt(et_posts.getText().toString());

                Log.d(TAG, "Start");

                int img =Integer.parseInt(img_view.getResources().toString());



               Post npost= new Post(date, name, body, followers, following, post,img);

                Intent intent = new Intent();


                intent.putExtra(POST_CONS_NAME, npost);

                setResult(RESULT_OK, intent);


                finish();

            }
        });

        img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();


                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, CAPTURE_REC_CODE);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_REC_CODE && resultCode == RESULT_OK) {


            Bitmap b = (Bitmap) data.getExtras().get("data");

            img_view.setImageBitmap(b);


        }
    }
}