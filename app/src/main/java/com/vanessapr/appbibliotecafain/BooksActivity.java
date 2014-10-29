package com.vanessapr.appbibliotecafain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.vanessapr.appbibliotecafain.fragments.BookFragment;
import com.vanessapr.appbibliotecafain.fragments.BooksListFragment;
import com.vanessapr.appbibliotecafain.models.Libro;


public class BooksActivity extends ActionBarActivity implements
        BooksListFragment.onBooksListSelectListener {
    private static final String TAG = "BooksActivity";
    public static final String EXTRA_WHERE = BooksActivity.class.getName() + ".where";
    public static final String EXTRA_ORDERBY = BooksActivity.class.getName() + ".orderBy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Log.i(TAG, "onCreate... ");

        BooksListFragment booksListFragment = (BooksListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_bookslist);

        String where = getIntent().getStringExtra(EXTRA_WHERE);
        String orderBy = getIntent().getStringExtra(EXTRA_ORDERBY);
        booksListFragment.displayBooksList(where, orderBy);

    }


    @Override
    public void onBookSelected(Libro libro) {
        BookFragment bookFragment = (BookFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_book);

        Log.i(TAG, "onBookSelected: " + bookFragment);

        if(bookFragment != null && bookFragment.getActivity() != null) {
            bookFragment.displayBookSingle(libro);

        } else {
            Intent intent = new Intent(BooksActivity.this, BookSingleActivity.class);
            intent.putExtra(BookSingleActivity.EXTRA_BOOK, libro);
            startActivity(intent);
         }

    }
}
