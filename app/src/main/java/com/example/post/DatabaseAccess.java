package com.example.post;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


    @SuppressLint("Range")
    public class DatabaseAccess {

        private SQLiteOpenHelper openHelper;
        private static DatabaseAccess instance;

        private SQLiteDatabase database;

        private DatabaseAccess(Context context){

            this.openHelper = new MyDatabase(context);

        }
        public static DatabaseAccess getInstance(Context context){
            if (instance==null){
                instance = new DatabaseAccess(context);
            }
            return instance;
        }

        public void open(){
            this.database = this.openHelper.getWritableDatabase();

        }
        public void close(){
            if (this.database!=null){
                this.database.close();
            }

        }


        public boolean insertPost(Post post) {

            ContentValues values = new ContentValues();



            values.put(MyDatabase.POST_CLN_DATE, post.getDate());
            values.put(MyDatabase.POST_CLN_NAME, post.getName());
            values.put(MyDatabase.POST_CLN_BODY, post.getBody());


            values.put(MyDatabase.POST_CLN_FOLLOWERS, post.getFollowers());
            values.put(MyDatabase.POST_CLN_FOLLOWING, post.getFollowing());
            values.put(MyDatabase.POST_CLN_POSTS, post.getPosts());

            values.put(MyDatabase.POST_CLN_IMG, post.getImg());

            long result = database.insert(MyDatabase.POST_TB_NAME, null, values);
            return result != -1;
        }

        public boolean updatePost(Post post) {

            ContentValues values = new ContentValues();

            values.put(MyDatabase.POST_CLN_IMG, post.getImg());

            values.put(MyDatabase.POST_CLN_DATE, post.getDate());
            values.put(MyDatabase.POST_CLN_NAME, post.getName());
            values.put(MyDatabase.POST_CLN_BODY, post.getBody());


            values.put(MyDatabase.POST_CLN_FOLLOWERS, post.getFollowers());
            values.put(MyDatabase.POST_CLN_FOLLOWING, post.getFollowing());
            values.put(MyDatabase.POST_CLN_POSTS, post.getPosts());

            String args[] = {String.valueOf(post.getId())};
            int result = database.update(MyDatabase.POST_TB_NAME, values, "id=?", args);
            return result > 0;
        }


        public long getPostsCount(){

            return DatabaseUtils.queryNumEntries(database, MyDatabase.POST_TB_NAME);
        }
        public boolean deletePost(Post post) {


            String args[] = {String.valueOf(post.getId())};
            int result =database.delete(MyDatabase.POST_TB_NAME,"id=?", args);
            return result > 0;
        }
        public ArrayList<Post> getAllPosts(){

            ArrayList<Post> posts = new ArrayList<>();

            Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabase.POST_TB_NAME, null);
            if (cursor != null && cursor.moveToFirst()){
                do {

                    int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_ID));

                    String date = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_DATE));
                    String name = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_NAME));

                    String body = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_BODY));

                    int followers = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_FOLLOWERS));
                    int following = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_FOLLOWING));
                    int post = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_POSTS));

                    String img = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_IMG));

                    Post p = new Post(id,date,name,body,followers,following,post,img);


                    posts.add(p);
                }while (cursor.moveToNext());
                cursor.close();
            }
            return posts;
        }


        public ArrayList<Post> getPosts(String modelSearch){

            ArrayList<Post> posts = new ArrayList<>();

            Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabase.POST_TB_NAME +" WHERE "+MyDatabase.POST_CLN_DATE+" LIKE ?"
                    ,new String[]{modelSearch+"%"});
            if (cursor != null && cursor.moveToFirst()){
                do {

                    int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_ID));

                    String date = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_DATE));
                    String name = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_NAME));

                    String body = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_BODY));

                    int followers = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_FOLLOWERS));
                    int following = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_FOLLOWING));
                    int post = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_POSTS));

                    String img = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_IMG));

                    Post p = new Post(id,date,name,body,followers,following,post,img);


                    posts.add(p);
                }while (cursor.moveToNext());
                cursor.close();
            }
            return posts;
        }

        public Post getPost(int postId){

            Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabase
                    .POST_TB_NAME+" WHERE "+MyDatabase.POST_CLN_ID+"=?", new String[]{String.valueOf(postId)});

            if (cursor != null && cursor.moveToFirst()){


                int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_ID));

                String date = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_DATE));
                String name = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_NAME));

                String body = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_BODY));

                int followers = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_FOLLOWERS));
                int following = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_FOLLOWING));
                int post = cursor.getInt(cursor.getColumnIndex(MyDatabase.POST_CLN_POSTS));

                String img = cursor.getString(cursor.getColumnIndex(MyDatabase.POST_CLN_IMG));

                Post p = new Post(id,date,name,body,followers,following,post,img);


                cursor.close();

                return p;
            }
            return null;
        }



    }






