package com.project.sample.data.local;


import android.app.Application;

import com.project.sample.data.db.BookAboutDao;
import com.project.sample.data.db.sampleDatabase;
import com.project.sample.data.model.BookAbout;

import java.util.List;

import androidx.lifecycle.LiveData;

public class BookAboutRepository {

    private final BookAboutDao bookAboutDao;

    public BookAboutRepository(Application application) {
        sampleDatabase db = sampleDatabase.getDatabase(application);
        bookAboutDao = db.bookAboutDao();
    }

    public LiveData<List<BookAbout>> getBookAbouts(int bookId){ return bookAboutDao.getBookAbouts(bookId); }
}
