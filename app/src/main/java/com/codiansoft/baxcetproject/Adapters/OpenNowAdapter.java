package com.codiansoft.baxcetproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codiansoft.baxcetproject.Activities.AddOrderDetailsActivity;
import com.codiansoft.baxcetproject.Models.OpenNowModel;
import com.codiansoft.baxcetproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodianSoft on 12/04/2018.
 */

public class OpenNowAdapter extends RecyclerView.Adapter<OpenNowAdapter.MyViewHolder>{


    List<OpenNowModel> list=new ArrayList<>();
    Context context;
    Activity activity;
    public OpenNowAdapter(List<OpenNowModel> list, Context context , Activity activity)
    {
        this.list=list;
        this.context=context;
        this.activity=activity;
    }


    @Override
    public OpenNowAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.open_now_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final OpenNowAdapter.MyViewHolder holder, int position) {

        OpenNowModel model = list.get(position);

        holder.title.setText(model.getTitle());
        holder.type.setText(model.getType());
        holder.ratingBar.setRating(model.getRating());

        holder.add_to_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.add_to_favourite.setImageResource(R.mipmap.ic_fav_filled);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context , AddOrderDetailsActivity.class);
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_right);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView add_to_favourite;
        TextView title;
        TextView type;
        me.zhanghai.android.materialratingbar.MaterialRatingBar ratingBar;
        public MyViewHolder(View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.item_image);
            add_to_favourite=itemView.findViewById(R.id.add_to_favourite);
            title=itemView.findViewById(R.id.tv_title);
            type=itemView.findViewById(R.id.tv_type);
            ratingBar=itemView.findViewById(R.id.rating_bar);

        }
    }
}
