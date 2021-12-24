package com.task.black_fig.ui.Privacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.task.black_fig.R;

import static com.task.black_fig.ui.category.CategoryFragment.home;

public class PrivacyFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_privacy, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        home=false;
        return root;
    }
}