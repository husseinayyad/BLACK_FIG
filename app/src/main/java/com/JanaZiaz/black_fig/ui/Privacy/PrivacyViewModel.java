package com.JanaZiaz.black_fig.ui.Privacy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrivacyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrivacyViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}