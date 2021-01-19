package com.example.boredapp.ui.fragments.help;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.boredapp.MainActivity;
import com.example.boredapp.R;
import com.example.boredapp.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HelpFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help, container, false);
        final ProgressBar progressBar = v.findViewById(R.id.help_bar);
        final ViewPager2 viewPager = v.findViewById(R.id.help_view_pager);
        final FloatingActionButton btn = v.findViewById(R.id.help_btn_check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        viewPager.setAdapter(new HelpPagerAdapter());
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                float progress = (float)(position + 1) / viewPager.getAdapter().getItemCount();
                progressBar.setProgress((int) (progress * 100));
                if (position+1==viewPager.getAdapter().getItemCount()) {
                    btn.setVisibility(View.VISIBLE);
                }
            }
        });

//        if (savedInstanceState != null)
//            loadInstanceState(savedInstanceState);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((MainActivity) getActivity()).getToolBar().setDisplayHomeAsUpEnabled(true);
//        ((MainActivity) getActivity()).getToolBar().setTitle("Info");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}