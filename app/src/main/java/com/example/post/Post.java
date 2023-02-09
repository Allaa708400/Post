package com.example.post;

import java.io.Serializable;

public class Post implements Serializable {
    private int id;
    private String date;
    private String name;
    private String body;
    private int followers;
    private int following;
    private int posts;
    private String img;


    public Post(int id, String date, String name, String body, int followers, int following, int posts, String img) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.body = body;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.img = img;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {this.img = img;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}