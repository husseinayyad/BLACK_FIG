package com.task.black_fig.Model;

public class feedback {
    String name ,image ,comment, data,time;



    public feedback() {
    }

    public feedback(String name, String image, String comment, String date, String time) {
        this.name = name;
        this.image = image;
        this.comment = comment;
        this.data = date;
        this.time = time;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
