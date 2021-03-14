package com.project.sample.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Book.class,
        parentColumns = "id",
        childColumns = "bookId"
        , onDelete = CASCADE))

public class BookAbout {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    @NonNull
    private String heading;
    @NonNull
    private String detail;
    @NonNull
    private int bookId; // FOREIGN KEY

    public BookAbout(String heading, String detail, int bookId) {
        this.heading = heading;
        this.detail = detail;
        this.bookId = bookId;
    }

    // --- GETTER ---

    public int getId() { return id; }
    @NonNull
    public String getHeading() { return heading; }
    @NonNull
    public String getDetail() { return detail; }
    public int getBookId() { return bookId; }

    // --- SETTER ---

    public void setId(int id) { this.id = id; }
    public void setHeading(@NonNull String heading) { this.heading = heading; }
    public void setDetail(@NonNull String detail) { this.detail = detail; }
    public void setBookId(int bookId) { this.bookId = bookId; }

}

