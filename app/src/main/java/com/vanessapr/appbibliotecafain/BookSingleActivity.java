package com.vanessapr.appbibliotecafain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vanessapr.appbibliotecafain.fragments.BookFragment;
import com.vanessapr.appbibliotecafain.models.Libro;


public class BookSingleActivity extends BaseActivity {
    private static final String TAG = "BookSingleActivity";
    public static final String EXTRA_BOOK = BookSingleActivity.class.getName()+".EXTRA_BOOK";
    private BookFragment bookfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_single);

        Libro libro = getIntent().getParcelableExtra(EXTRA_BOOK);

        bookfragment = (BookFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_single_book);

        //bookfragment.setRetainInstance(true);
        Intent intent = new Intent(BookSingleActivity.this, BooksActivity.class);
        bookfragment.displayBookSingle(libro, intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Intent intent = new Intent(BookSingleActivity.this, BooksActivity.class);
        //bookfragment.onClickListenerLinkText(intent);
    }

}
