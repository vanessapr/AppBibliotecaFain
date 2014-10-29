package com.vanessapr.appbibliotecafain;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.vanessapr.appbibliotecafain.fragments.BooksListFragment;
import com.vanessapr.appbibliotecafain.models.Libro;


public class BooksActivity extends ActionBarActivity implements
        BooksListFragment.onBooksListSelectListener {
    public static final String EXTRA_WHERE = BooksActivity.class.getName() + ".where";
    public static final String EXTRA_ORDERBY = BooksActivity.class.getName() + ".orderBy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent intent = getIntent();
        String where = intent.getStringExtra(EXTRA_WHERE);
        String orderBy = intent.getStringExtra(EXTRA_ORDERBY);

        BooksListFragment booksListfragment = (BooksListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_bookslist);

        booksListfragment.displayBooksList(where, orderBy);
    }


    @Override
    public void onBookSelected(Libro libro) {
        Intent intent = new Intent(BooksActivity.this, BookSingleActivity.class);
        intent.putExtra(BookSingleActivity.EXTRA_BOOK, libro);
        startActivity(intent);
    }
}
