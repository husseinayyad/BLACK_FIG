package com.task.black_fig.ui.terms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.task.black_fig.R;

import static com.task.black_fig.ui.category.CategoryFragment.home;

public class TermsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        home=false;

        View root = inflater.inflate(R.layout.fragment_terms, container, false);

        return root;
    }
}