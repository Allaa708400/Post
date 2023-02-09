package com.example.post;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.post.R;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    private static final int ADD_POST_REQ_CODE = 1;
    private static final int EDIT_POST_REQ_CODE = 1;
    private static final int PERMISSION_REQ_CODE = 5;
    public static final String POST_KEY = "post_key";
    private RecyclerView rv;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private PostRVAdapter adapter;
    private DatabaseAccess db;

 @SuppressLint("MissingInflatedId")
@Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQ_CODE);
        }

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.main_rv);
        fab = findViewById(R.id.main_fab);

        db = DatabaseAccess.getInstance(this);

        db.open();
        ArrayList<Post> posts = db.getAllPosts();
        db.close();

        adapter = new PostRVAdapter(posts, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int postId) {
                Intent intent = new Intent(getBaseContext(), ViewPostActivity.class);
                intent.putExtra(POST_KEY, postId);
                startActivityForResult(intent, EDIT_POST_REQ_CODE);
            }
        });
        rv.setAdapter(adapter);
     RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

      //  RecyclerView.LayoutManager lm = new GridLayoutManager(this, 2);

        rv.setLayoutManager(lm);

        rv.setHasFixedSize(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), ViewPostActivity.class);
                startActivityForResult(intent, ADD_POST_REQ_CODE);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                db.open();
                ArrayList<Post> posts = db.getPosts(query);
                db.close();
                adapter.setPosts(posts);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                db.open();
                ArrayList<Post> posts = db.getPosts(newText);
                db.close();
                adapter.setPosts(posts);
                adapter.notifyDataSetChanged();


                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                db.open();
                ArrayList<Post> posts = db.getAllPosts();
                db.close();
                adapter.setPosts(posts);
                adapter.notifyDataSetChanged();

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_POST_REQ_CODE){

            db.open();
            ArrayList<Post> posts = db.getAllPosts();
            db.close();
            adapter.setPosts(posts);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case PERMISSION_REQ_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


                } else {


                }
        }
    }

}