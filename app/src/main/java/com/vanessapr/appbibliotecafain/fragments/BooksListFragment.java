package com.vanessapr.appbibliotecafain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanessapr.appbibliotecafain.R;

/**
 * Created by Milagros on 27/10/2014.
 */
public class BooksListFragment extends Fragment {

    public BooksListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookslist_fragment, container, false);
        return view;

    }
}
