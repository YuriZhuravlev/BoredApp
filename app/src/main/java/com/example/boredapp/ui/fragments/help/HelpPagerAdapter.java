package com.example.boredapp.ui.fragments.help;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boredapp.R;


public class HelpPagerAdapter extends RecyclerView.Adapter<PagerViewHolder> {
    private final int[] mList = {R.drawable.page0, R.drawable.page1, R.drawable.page2,
            R.drawable.page3, R.drawable.page4, R.drawable.page5, R.drawable.page6};

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {
        ImageView img = holder.itemView.findViewById(R.id.page_item_image);
        img.setImageResource(mList[position]);
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }
}
