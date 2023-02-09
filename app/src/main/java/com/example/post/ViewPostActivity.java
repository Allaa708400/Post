package com.example.post;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

public class ViewPostActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputEditText et_name, et_body, et_date, et_followers, et_following, et_posts;
    private ImageView iv;
    private int postId = -1;
    private DatabaseAccess db;
    private static final int PICK_IMAGE_REQ_CODE = 4;
    public static final int ADD_POST_RESULT_CODE = 2;
    public static final int EDIT_POST_RESULT_CODE = 3;

    final int CAPTURE_REC_CODE = 1;
    private String imageUri = null;
    private Bitmap imgBit = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);

        iv = findViewById(R.id.img_view);
        et_name = findViewById(R.id.add_post_name);
        et_body = findViewById(R.id.add_post_body);
        et_date = findViewById(R.id.add_post_date);
        et_followers = findViewById(R.id.add_post_followers);
        et_following = findViewById(R.id.add_post_following);
        et_posts = findViewById(R.id.add_post_posts);


        db = DatabaseAccess.getInstance(this);

        Intent intent = getIntent();
        postId = intent.getIntExtra(MainActivity.POST_KEY, -1);

        if (postId == -1) {

            enableFields();
            clearFields();

        } else {

            disableFields();
            db.open();
            Post p = db.getPost(postId);
            db.close();

            if (p != null) {

                fillPostToFiled(p);
            }
        }


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();


                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, CAPTURE_REC_CODE);
            }
        });
/*
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent1, PICK_IMAGE_REQ_CODE);
            }
       });

 */


    }

    private void fillPostToFiled(@NonNull Post p) {

        if (p.getImg() != null) {
            iv.setImageURI(Uri.parse(p.getImg()));

        }

        et_name.setText(p.getName());
        et_body.setText(p.getBody());
        et_date.setText(p.getDate());

        et_followers.setText(p.getFollowers() + "");
        et_following.setText(p.getFollowing() + "");
        et_posts.setText(p.getPosts() + "");


    }

    private void disableFields() {
        if (iv != null) {
            iv.setEnabled(false);
        }


        et_name.setEnabled(false);
        et_body.setEnabled(false);
        et_date.setEnabled(false);
        et_followers.setEnabled(false);
        et_following.setEnabled(false);
        et_posts.setEnabled(false);
    }

    private void enableFields() {

        iv.setEnabled(true);

        et_name.setEnabled(true);
        et_body.setEnabled(true);
        et_date.setEnabled(true);
        et_followers.setEnabled(true);
        et_following.setEnabled(true);
        et_posts.setEnabled(true);

    }

    private void clearFields() {

        iv.setImageURI(null);

        et_name.setText("");
        et_body.setText("");
        et_date.setText("");

        et_followers.setText("");
        et_following.setText("");
        et_posts.setText("");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);

        MenuItem save = menu.findItem(R.id.details_menu_save);
        MenuItem edit = menu.findItem(R.id.details_menu_edit);
        MenuItem delete = menu.findItem(R.id.details_menu_delete);

        if (postId == -1) {

            save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);

        } else {

            save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);


        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String name, body, date;
        String image = null;
        int followers, following, posts;
        db.open();
        switch (item.getItemId()) {
            case R.id.details_menu_save:

                name = et_name.getText().toString();
                body = et_body.getText().toString();
                date = et_date.getText().toString();

                followers = Integer.parseInt(et_followers.getText().toString());
                following = Integer.parseInt(et_following.getText().toString());
                posts = Integer.parseInt(et_posts.getText().toString());

                if (imageUri != null) {
                    image = imageUri;

                }
                boolean res;


                Post p = new Post(postId, date, name, body, followers, following, posts, image);


                if (postId == -1) {
                    res = db.insertPost(p);

                    if (res) {

                        Toast.makeText(this, "Post added successfully",
                                Toast.LENGTH_SHORT).show();
                        setResult(ADD_POST_RESULT_CODE, null);
                        finish();
                    }
                } else {
                    res = db.updatePost(p);
                    if (res) {

                        Toast.makeText(this, "Post modified successfully",
                                Toast.LENGTH_SHORT).show();
                        setResult(EDIT_POST_RESULT_CODE, null);
                        finish();
                    }
                }

                return true;
            case R.id.details_menu_edit:

                enableFields();

                MenuItem save = toolbar.getMenu().findItem(R.id.details_menu_save);
                MenuItem edit = toolbar.getMenu().findItem(R.id.details_menu_edit);
                MenuItem delete = toolbar.getMenu().findItem(R.id.details_menu_delete);

                save.setVisible(true);
                edit.setVisible(false);
                delete.setVisible(false);

                return true;
            case R.id.details_menu_delete:


                p = new Post(postId, null, null, null, 0, 0, 0, null);

                res = db.deletePost(p);
                if (res)

                    Toast.makeText(this, "Post delete successfully", Toast.LENGTH_SHORT).show();
                setResult(EDIT_POST_RESULT_CODE, null);
                finish();

                return true;
        }
        db.close();
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  if(requestCode == PICK_IMAGE_REQ_CODE && resultCode == RESULT_OK){

        //     if (data!=null){
        //      imageUri = data.getData();
        //       iv.setImageURI(imageUri);


        if (requestCode == CAPTURE_REC_CODE && resultCode == RESULT_OK) {


            Bitmap b = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(b);

            imageUri = getImageUri(getApplicationContext(), b).toString();

           //  Log.d("myTag", data.getExtras().get("data").toString());


        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
