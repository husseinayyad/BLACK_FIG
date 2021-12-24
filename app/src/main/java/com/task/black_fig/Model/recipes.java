package com.task.black_fig.Model;

public class recipes {
    String name ,image,time,id,ingredients ;



    public recipes() {
    }


    public recipes(String name, String image, String time, String id, String ingredients) {
        this.name = name;
        this.image = image;
        this.time = time;
        this.id = id;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
