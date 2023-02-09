package com.example.post;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.post.R;

import java.util.ArrayList;


public class PostRVAdapter extends RecyclerView.Adapter<PostRVAdapter.PostViewHolder>{


    public PostRVAdapter() {

    }

    public ArrayList<Post> getPost() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    private ArrayList<Post> posts;
    private OnRecyclerViewItemClickListener listener;
    public PostRVAdapter(ArrayList<Post> posts, OnRecyclerViewItemClickListener listener){
        this.posts = posts;
        this.listener = listener;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_post_layout,null,false);

        PostViewHolder viewHolder = new PostViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post p = posts.get(position);
        if (p.getImg() !=null && !p.getImg().isEmpty()) {
            holder.img.setImageURI(Uri.parse(p.getImg()));
        }

                 holder.tv_name.setText(p.getName());
                 holder.tv_body.setText(p.getBody());
                 holder.tv_date.setText(p.getDate());
                 holder.tv_followers.setText(String.valueOf(p.getFollowers()));
                 holder.tv_following.setText(String.valueOf(p.getFollowing()));
                 holder.tv_posts.setText(String.valueOf(p.getPosts()));





                holder.id = p.getId();

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        int id;

        TextView tv_date,tv_name,tv_following,tv_followers,tv_body  , tv_posts ;

        ImageView img;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.custom_post_tv_img_profile);


            tv_name = itemView.findViewById(R.id.custom_post_tv_name);
            tv_body= itemView.findViewById(R.id.custom_post_tv_body);
            tv_date = itemView.findViewById(R.id.custom_post_tv_date);
            tv_followers = itemView.findViewById(R.id.custom_post_tv_followers_data);
            tv_following = itemView.findViewById(R.id.custom_post_tv_following_data);
            tv_posts = itemView.findViewById(R.id.custom_post_tv_posts_data);






            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(id);
                }
            });
        }
    }
}

