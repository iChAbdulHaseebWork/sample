package com.project.sample.viewmodel;

import android.app.Application;

import com.project.sample.data.local.BookRepository;
import com.project.sample.data.model.Book;
import com.project.sample.data.model.BookInfo;
import com.project.sample.data.model.BookNameTuple;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BookViewModel extends AndroidViewModel {
    private final BookRepository repository;
    private final LiveData<List<Book>> allBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public LiveData<BookInfo> getBook(int id) {
        return repository.getBook(id);
    }

    public LiveData<Book> getSingleBook(int id) {
        return repository.getSingleBook(id);
    }

    public LiveData<BookNameTuple> getBookName(int id) {
        return repository.getBookName(id);
    }

}