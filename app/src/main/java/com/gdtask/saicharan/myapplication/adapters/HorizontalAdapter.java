package com.gdtask.saicharan.myapplication.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.gdtask.saicharan.myapplication.R;
import com.gdtask.saicharan.myapplication.models.PopularArticle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<PopularArticle> horizontalList;
    private Context mContext;
    String monthday;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView txtView;
        public TextView tvPopTitle,tvsubtitle,tvdate;


        public MyViewHolder(View view) {
            super(view);
            txtView = (ImageView) view.findViewById(R.id.imgView);
            tvPopTitle = (TextView) view.findViewById(R.id.title);
            tvsubtitle = (TextView) view.findViewById(R.id.subtitle);
            tvdate = (TextView) view.findViewById(R.id.date);
        }
    }

    public HorizontalAdapter(Context context, List<PopularArticle> horizontalList) {
        this.horizontalList = horizontalList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(mContext).load(horizontalList.get(position).imageUrl).into(holder.txtView);
        holder.tvPopTitle.setText(horizontalList.get(position).title);
        holder.tvsubtitle.setText(horizontalList.get(position).tvabstract);
        holder.tvdate.setText(horizontalList.get(position).tvdate);
        /*String strDate = horizontalList.get(position).tvdate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(strDate);
            SimpleDateFormat sdfmonth = new SimpleDateFormat("yyyy-MM-dd");
            monthday = sdfmonth.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvdate.setText(monthday);*/
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}