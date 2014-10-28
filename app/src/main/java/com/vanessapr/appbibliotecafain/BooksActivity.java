package com.vanessapr.appbibliotecafain;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.vanessapr.appbibliotecafain.fragments.BooksListFragment;


public class BooksActivity extends ActionBarActivity implements
        BooksListFragment.onBooksListSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Intent intent = getIntent();
        String where = intent.getStringExtra(MainActivity.TAG_WHERE);
        String orderBy = intent.getStringExtra(MainActivity.TAG_ORDERBY);

        Bundle args = new Bundle();
        args.putString(MainActivity.TAG_WHERE, where);
        args.putString(MainActivity.TAG_ORDERBY, orderBy);

        BooksListFragment fragmentBooks = BooksListFragment.newInstance(args);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_books, fragmentBooks);
        ft.commit();
    }


    @Override
    public void onBookSelected(int position) {

    }
}
