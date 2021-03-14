package com.project.sample.data.local;


import android.app.Application;

import com.dylogicapps.muslimquranpro.data.db.BookAboutDao;
import com.dylogicapps.muslimquranpro.data.db.MuslimProDatabase;
import com.dylogicapps.muslimquranpro.data.model.BookAbout;

import java.util.List;

import androidx.lifecycle.LiveData;

public class BookAboutRepository {

    private final BookAboutDao bookAboutDao;

    public BookAboutRepository(Application application) {
        MuslimProDatabase db = MuslimProDatabase.getDatabase(application);
        bookAboutDao = db.bookAboutDao();
    }

    public LiveData<List<BookAbout>> getBookAbouts(int bookId){ return bookAboutDao.getBookAbouts(bookId); }
}
