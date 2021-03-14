package com.project.sample.data.local;


import android.app.Application;

import com.dylogicapps.muslimquranpro.data.db.BookDao;
import com.dylogicapps.muslimquranpro.data.db.MuslimProDatabase;
import com.dylogicapps.muslimquranpro.data.model.Book;
import com.dylogicapps.muslimquranpro.data.model.BookInfo;
import com.dylogicapps.muslimquranpro.data.model.BookNameTuple;

import java.util.List;

import androidx.lifecycle.LiveData;

public class BookRepository {
    private final BookDao bookDao;
    private final LiveData<List<Book>> allBooks;
    public BookRepository(Application application) {
        MuslimProDatabase db = MuslimProDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() { return allBooks; }

    public LiveData<BookInfo> getBook(int id){ return bookDao.getBook(id); }

    public LiveData<Book> getSingleBook(int id){ return bookDao.getSingleBook(id); }

    public LiveData<BookNameTuple> getBookName(int id){ return bookDao.getBookName(id); }

}
