package com.codiansoft.baxcetproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codiansoft.baxcetproject.Models.ImageModel;
import com.codiansoft.baxcetproject.R;

import java.util.List;

/**
 * Created by CodianSoft on 17/04/2018.
 */

public class SelectImageAdapter extends RecyclerView.Adapter<SelectImageAdapter.MyViewHolder> {

    List<ImageModel> list;
    Context context;
    public SelectImageAdapter(List<ImageModel> list , Context context)
    {
        this.list=list;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row,parent,false);

        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
      ImageModel model=list.get(position);

        holder.image.setImageURI(model.getUri());

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView cancel;
        public MyViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image1);
            cancel=itemView.findViewById(R.id.cancel);
        }
    }


}
