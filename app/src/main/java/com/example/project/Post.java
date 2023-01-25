package com.example.project;

import java.io.Serializable;

public class Post implements Serializable {

    private String date = "";
    private String name = "";
    private String body = "";
    private int followers = 0;
    private int following = 0;
    private int posts = 0;
    private int img = 0;



    public Post(String date, String name, String body, int followers, int following, int posts,int img) {
        this.date = date;
        this.name = name;
        this.body = body;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.img = img;

    }

    public Post(String date, String name, String body, int followers, int following, int posts) {
        this.date = date;
        this.name = name;
        this.body = body;
        this.followers = followers;
        this.following = following;
        this.posts = posts;



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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img; }
}
