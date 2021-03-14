package com.project.sample.viewmodel;

import android.app.Application;

import com.dylogicapps.muslimquranpro.data.local.BookAboutRepository;
import com.dylogicapps.muslimquranpro.data.model.BookAbout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BookAboutViewModel extends AndroidViewModel {
    private final BookAboutRepository repository;

    public BookAboutViewModel(@NonNull Application application) {
        super(application);
        repository = new BookAboutRepository(application);
    }

    public LiveData<List<BookAbout>> getBookAbout(int bookId) {
        return repository.getBookAbouts(bookId);
    }

}