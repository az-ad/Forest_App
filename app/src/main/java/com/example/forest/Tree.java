package com.example.forest;

public class Tree {
    private String name;
    private String imageUrl;
    private String description;

   /* public Tree(String name) {
        this.name = name;
    }*/

    public Tree() {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }
    public Tree(String name, String description, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
