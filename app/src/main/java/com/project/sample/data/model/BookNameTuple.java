package com.project.sample.data.model;

import androidx.annotation.NonNull;

public class BookNameTuple {
    @NonNull
    public String engName;
    @NonNull
    public String arabicName;

    @NonNull
    public String getEngName() {
        return engName;
    }

    @NonNull
    public String getArabicName() {
        return arabicName;
    }
}
