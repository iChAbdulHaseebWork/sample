package com.project.sample.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    @NonNull
    private int number;
    @NonNull
    private String engName;
    @NonNull
    private String arabicName;
    @NonNull
    private String imagePath;

    public Book(int number, String engName, String arabicName, String imagePath) {
        this.number = number;
        this.engName = engName;
        this.engName = engName;
        this.arabicName = arabicName;
        this.imagePath = imagePath;
    }

    // --- GETTER ---

    public int getId() { return id; }
    public int getNumber() { return number; }
    @NonNull
    public String getEngName() { return engName; }
    @NonNull
    public String getArabicName() { return arabicName; }
    @NonNull
    public String getImagePath() { return imagePath; }

    // --- SETTER ---

    public void setId(int id) { this.id = id; }
    public void setNumber(int number) { this.number = number; }
    public void setEngName(@NonNull String engName) { this.engName = engName; }
    public void setArabicName(@NonNull String arabicName) { this.arabicName = arabicName; }
    public void setImagePath(@NonNull String imagePath) { this.imagePath = imagePath; }
}
