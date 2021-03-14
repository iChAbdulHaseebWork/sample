package com.project.sample.data.db;

import com.project.sample.data.model.Book;
import com.project.sample.data.model.BookInfo;
import com.project.sample.data.model.BookNameTuple;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface BookDao {

    @Query("SELECT * FROM Book")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT engName,arabicName,imagePath FROM Book WHERE id = :id")
    LiveData<BookInfo> getBook(int id);

    @Query("SELECT * FROM Book WHERE id = :id")
    LiveData<Book> getSingleBook(int id);

    @Query("SELECT engName,arabicName FROM Book WHERE id = :id")
    LiveData<BookNameTuple> getBookName(int id);
}
