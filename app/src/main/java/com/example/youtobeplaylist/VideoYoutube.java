package com.example.youtobeplaylist;

public class VideoYoutube {
    private  String Tittle;
    private  String Thumbnail;
    private  String IdVideo;

    public VideoYoutube(String tittle, String thumbnail, String idVideo) {
        Tittle = tittle;
        Thumbnail = thumbnail;
        IdVideo = idVideo;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }
}
