package com.project.sample.data.model;

import androidx.annotation.NonNull;

public class BookInfo {
    @NonNull
    public String engName;
    @NonNull
    public String arabicName;
    @NonNull
    public String imagePath;

    @NonNull
    public String getEngName() {
        return engName;
    }

    @NonNull
    public String getArabicName() {
        return arabicName;
    }

    @NonNull
    public String getImagePath() {
        return imagePath;
    }
}
