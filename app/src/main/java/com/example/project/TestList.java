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
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestList extends AppCompatActivity {
    final int REQ_CODE_ADD = 1;


    PostAdapter adapter;

    ListView lv;
    Button btn_add;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        lv = findViewById(R.id.test_list);
        btn_add = findViewById(R.id.list_btn_add);




        ArrayList<Post> posts = new ArrayList<>();

        /*
        posts.add(new Post("12-May-2015","Ziyad Alaa",
                "Welcome to java",20,30,40,R.drawable.following));

        posts.add(new Post("13-May-2015","Ziyad Alaa",
                "Welcome to java",20,30,40,R.drawable.like));

        posts.add(new Post("14-May-2015","Ziyad Alaa",
                "Welcome to java",20,30,40,R.drawable.posts));

         */

        adapter = new PostAdapter(this, R.layout.custom_post_layout, posts);

        lv.setAdapter(adapter);







        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), AddPostActivity.class);








                startActivityForResult(intent, REQ_CODE_ADD);



            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_ADD && resultCode == RESULT_OK) {

            Post p = (Post) data.getSerializableExtra(AddPostActivity.POST_CONS_NAME);

            adapter.addItem(p);


            Bitmap b = (Bitmap) data.getExtras().get("data");


            adapter.notifyDataSetChanged();


        }


    }
}





