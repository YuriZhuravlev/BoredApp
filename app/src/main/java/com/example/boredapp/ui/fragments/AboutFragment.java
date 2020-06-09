package com.example.boredapp.ui.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.boredapp.MainActivity;
import com.example.boredapp.R;


public class AboutFragment extends Fragment {
    ImageView mImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        mImage = v.findViewById(R.id.image_833r);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getToolBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getToolBar().setTitle(R.string.about);
        try {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                mImage.setImageDrawable(getResources().getDrawable(R.drawable.img_833r_c0de_light));
            } else {
                mImage.setImageDrawable(getResources().getDrawable(R.drawable.img_833r_c0de));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}