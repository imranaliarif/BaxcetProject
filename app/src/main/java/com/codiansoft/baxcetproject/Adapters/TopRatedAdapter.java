package com.codiansoft.baxcetproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codiansoft.baxcetproject.Models.TopRatedModel;
import com.codiansoft.baxcetproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodianSoft on 12/04/2018.
 */

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.MyViewHolder> {

    List<TopRatedModel> list=new ArrayList<>();
    Context context;
    public TopRatedAdapter(List<TopRatedModel> list , Context context)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_rated_row, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TopRatedModel model = list.get(position);

        holder.title.setText(model.getTitle());
        holder.type.setText(model.getType());

        if(position%2==0)
        {
         holder.ratingBar.setRating(4);
        }
        else
        {
            holder.ratingBar.setRating(5);

        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView type;
        me.zhanghai.android.materialratingbar.MaterialRatingBar ratingBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.item_image);
            title=itemView.findViewById(R.id.tv_title);
            type=itemView.findViewById(R.id.tv_type);
            ratingBar=itemView.findViewById(R.id.rating_bar);
        }
    }
}
