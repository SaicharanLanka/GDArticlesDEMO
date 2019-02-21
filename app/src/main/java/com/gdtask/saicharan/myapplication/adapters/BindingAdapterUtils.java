package com.gdtask.saicharan.myapplication.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gdtask.saicharan.myapplication.R;


public class BindingAdapterUtils {
    @android.databinding.BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        view.setImageResource(0);
        Glide.with(view.getContext()).load(url).placeholder(R.drawable.loading).into(view);
    }
}