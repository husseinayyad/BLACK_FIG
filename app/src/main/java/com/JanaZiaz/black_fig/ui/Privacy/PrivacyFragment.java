package com.JanaZiaz.black_fig.ui.Privacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.JanaZiaz.black_fig.R;

public class PrivacyFragment extends Fragment {

    private PrivacyViewModel privacyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        privacyViewModel =
                ViewModelProviders.of(this).get(PrivacyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_privacy, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        return root;
    }
}