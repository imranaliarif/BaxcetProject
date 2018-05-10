package com.codiansoft.baxcetproject.Models;

/**
 * Created by CodianSoft on 12/04/2018.
 */

public class TopRatedModel {


    private String id;
    private String title;
    private String type;
    private String imageLink;
    private int rating;
    private boolean liked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
