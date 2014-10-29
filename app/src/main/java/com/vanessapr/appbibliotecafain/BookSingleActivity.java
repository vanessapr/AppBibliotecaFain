package com.vanessapr.appbibliotecafain;

import android.os.Bundle;

import com.vanessapr.appbibliotecafain.fragments.BookFragment;
import com.vanessapr.appbibliotecafain.models.Libro;


public class BookSingleActivity extends BaseActivity {
    public static final String EXTRA_BOOK = BookSingleActivity.class.getSimpleName()+".EXTRA_BOOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_single);

        BookFragment bookfragment = (BookFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_single_book);

        Libro libro = (Libro) getIntent().getSerializableExtra(EXTRA_BOOK);

        bookfragment.displayBookSingle(libro);

    }

}
