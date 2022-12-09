package com.example.todolist;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Work {
    private int id;
    private String createDate;
    private String title;
    private String description;
    private Bitmap imgWork;
    public Work(Bitmap imgWork, String createDate, String title, String description) {
        this.createDate = createDate;
        this.title = title;
        this.description = description;
        this.imgWork=imgWork;
    }


    public Bitmap getImgWork() {
        return imgWork;
    }

    public void setImgWork(Bitmap imgWork) {
        this.imgWork = imgWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
