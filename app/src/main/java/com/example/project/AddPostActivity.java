package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
//import android.widget.ImageView;

public class AddPostActivity extends AppCompatActivity {
    private ArrayList<Post> posts;
    static final String POST_CONS_NAME = "post";
    EditText et_name,et_body,et_date,et_followers,et_following,et_posts;
    Button btn_add;
   // ImageView v_img;

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

/*
        Log.d("et_name", "Ziyad Alaa");
        Log.d("et_body", "alaa alaa alaa");
        Log.d("et_date", "21-May-2121");
        Log.d("et_followers", "20");
        Log.d("et_following", "30");
        Log.d("et_posts", "40");
*/




        // v_img = findViewById(R.id.add_post_img);
        btn_add = findViewById(R.id.add_btn_save);





        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_name.getText().toString();
                String body = et_body.getText().toString();
                String date = et_date.getText().toString();
                int followers = Integer.parseInt(et_followers.getText().toString());
                int following = Integer.parseInt(et_following.getText().toString());
                int posts = Integer.parseInt(et_posts.getText().toString());
             //  int img = Integer.parseInt(v_img.getDrawable().toString());

                Post post = new Post(date,name,body,followers, following, posts);

                Intent intent = new Intent();

               intent.putExtra(POST_CONS_NAME,post);

                setResult(RESULT_OK,intent);
                finish();






            }
        });


    }
}