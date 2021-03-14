package com.project.sample.data.local;


import android.app.Application;

import com.project.sample.data.db.BookDao;
import com.project.sample.data.db.sampleDatabase;
import com.project.sample.data.model.Book;
import com.project.sample.data.model.BookInfo;
import com.project.sample.data.model.BookNameTuple;

import java.util.List;

import androidx.lifecycle.LiveData;

public class BookRepository {
    private final BookDao bookDao;
    private final LiveData<List<Book>> allBooks;
    public BookRepository(Application application) {
        sampleDatabase db = sampleDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() { return allBooks; }

    public LiveData<BookInfo> getBook(int id){ return bookDao.getBook(id); }

    public LiveData<Book> getSingleBook(int id){ return bookDao.getSingleBook(id); }

    public LiveData<BookNameTuple> getBookName(int id){ return bookDao.getBookName(id); }

}
