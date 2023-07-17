package com.example.lab2.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private BookRepository mRepository;
    private LiveData<List<Book>> mAllBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BookRepository(application);
        mAllBooks = mRepository.getAllItems();
    }

    public LiveData<List<Book>> getAllItems() {
        return mAllBooks;
    }

    public void insert(Book item) {
        mRepository.insert(item);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void deleteLast(){
        mRepository.deleteLastbook();
    }
}

