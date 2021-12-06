package com.example.p5.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/**
 * Setting the View Model.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    /**
     * Sets index/position
     */
    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    /**
     * Gets all the live data.
     */
    public LiveData<String> getText() {
        return mText;
    }
}