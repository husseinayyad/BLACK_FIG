package com.JanaZiaz.black_fig.ui.terms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.JanaZiaz.black_fig.R;

public class TermsFragment extends Fragment {

    private TermsModel termsModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        termsModel =
                ViewModelProviders.of(this).get(TermsModel.class);
        View root = inflater.inflate(R.layout.fragment_terms, container, false);

        return root;
    }
}