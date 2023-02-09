package com.example.post;


import android.annotation.SuppressLint;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


@SuppressLint("Range")
public class MyDatabase extends SQLiteAssetHelper {

        public static final String DB_NAME = "posts.db";
        public static final int DB_VERSION = 1;
        public static final String POST_CLN_ID = "id";
        public static final String POST_CLN_NAME = "name";
        public static final String POST_CLN_DATE = "date";
        public static final String POST_TB_NAME = "post";


        public static final String POST_CLN_BODY = "body";
        public static final String POST_CLN_FOLLOWERS = "followers";
        public static final String POST_CLN_FOLLOWING = "following";
        public static final String POST_CLN_POSTS = "posts";
        public static final String POST_CLN_IMG = "img";



        public MyDatabase(Context context) {

            super(context, DB_NAME, null, DB_VERSION);
        }

    }








