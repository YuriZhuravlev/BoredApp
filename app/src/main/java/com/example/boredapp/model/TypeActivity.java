package com.example.boredapp.model;

import androidx.annotation.NonNull;

import com.example.boredapp.R;

public enum TypeActivity {
    EMPTY, EDUCATION, RECREATIONAL, SOCIAL, DIY, CHARITY, COOKING, RELAXATION, MUSIC, BUSYWORK;
//["education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork"]

    public String[] getLowerCase() {
        return new String[]{"empty", "education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork"};
    }
}
