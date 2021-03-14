package com.project.sample.data.db;

import com.dylogicapps.muslimquranpro.data.model.BookAbout;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface BookAboutDao {
    @Transaction
    @Query("SELECT * FROM BookAbout WHERE bookId = :bookId")
    LiveData<List<BookAbout>> getBookAbouts(int bookId);
}
