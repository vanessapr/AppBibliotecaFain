package com.vanessapr.appbibliotecafain;

import android.os.Bundle;

import com.vanessapr.appbibliotecafain.fragments.BookFragment;
import com.vanessapr.appbibliotecafain.models.Libro;


public class BookSingleActivity extends BaseActivity {
    public static final String EXTRA_BOOK = BookSingleActivity.class.getName()+".EXTRA_BOOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_single);

        Libro libro = getIntent().getParcelableExtra(EXTRA_BOOK);

        BookFragment bookfragment = (BookFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_single_book);

        //bookfragment.setRetainInstance(true);
        bookfragment.displayBookSingle(libro);
    }

}
