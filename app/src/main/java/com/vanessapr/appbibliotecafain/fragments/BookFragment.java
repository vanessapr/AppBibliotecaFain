package com.vanessapr.appbibliotecafain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanessapr.appbibliotecafain.R;
import com.vanessapr.appbibliotecafain.models.Libro;

/**
 * Created by Milagros on 27/10/2014.
 */
public class BookFragment extends Fragment {
    private static final String TAG = "BookFragment";
    public static final String EXTRA_POSITION_BOOK = BookFragment.class.getName() + ".EXTRA_POSITION_BOOK";
    private ViewGroup mContainer;
    private Libro mCurrentPosition = null;

    public BookFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView..." + savedInstanceState);

        if(savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getParcelable(EXTRA_POSITION_BOOK);

        View view = inflater.inflate(R.layout.fragment_book, container, false);
        mContainer = (ViewGroup) view.findViewById(R.id.container_book);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart..." + mCurrentPosition);
        if(mCurrentPosition != null) {
            displayBookSingle(mCurrentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState...");
        // Save the current book selection in case we need to recreate the fragment
        outState.putParcelable(EXTRA_POSITION_BOOK, mCurrentPosition);
    }

    public void displayBookSingle(Libro libro) {
        Log.i(TAG, "my activity: " + getActivity());
        mContainer.removeAllViews();
        mContainer.addView(libro.render(getActivity(),libro.getTipo()));
        mCurrentPosition = libro;
    }


}
