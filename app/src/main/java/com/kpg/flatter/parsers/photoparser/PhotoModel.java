package com.kpg.flatter.parsers.photoparser;

final public class PhotoModel {

    private String user;
    private String title;
    private String album;
    private String data;

    public PhotoModel(String user, String title, String album) {
        this.user = user;
        this.title = title;
        this.album = album;
    }


    public PhotoModel(String user, String title, String album, String data) {
        this.user = user;
        this.title = title;
        this.album = album;
        this.data = data;
    }

    public PhotoModel(){}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
