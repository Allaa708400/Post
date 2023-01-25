package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

    private Context c;
    private int resource;
 private ArrayList<Post> posts;

    public PostAdapter(Context c, int resource, ArrayList<Post> posts) {
        this.c = c;
        this.posts = posts;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v==null){
            v = LayoutInflater.from(c).inflate(resource, null, false);
        }

        TextView tv_date = v.findViewById(R.id.custom_post_tv_date);
        TextView tv_name = v.findViewById(R.id.custom_post_tv_name);
        TextView tv_following = v.findViewById(R.id.custom_post_tv_following_data);
        TextView tv_followers = v.findViewById(R.id.custom_post_tv_followers_data);
        TextView tv_posts = v.findViewById(R.id.custom_post_tv_posts_data);
        TextView tv_body= v.findViewById(R.id.custom_post_tv_body);
        ImageView img = v.findViewById(R.id.custom_post_tv_img_profile);

        Post p = getItem(position);

       tv_date.setText(p.getDate());
        tv_name.setText(p.getName());
        tv_body.setText(p.getBody());
        tv_followers.setText(p.getFollowers()+"");
        tv_following.setText(p.getFollowing()+"");
        tv_posts.setText(p.getPosts()+"");
        img.setImageResource(p.getImg());


        return v;
    }

    public void addItem(Post post){

        this.posts.add(post);

    }


}
