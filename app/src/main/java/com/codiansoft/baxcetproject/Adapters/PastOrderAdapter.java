package com.codiansoft.baxcetproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codiansoft.baxcetproject.Models.PastOrderModel;
import com.codiansoft.baxcetproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodianSoft on 13/04/2018.
 */

public class PastOrderAdapter extends RecyclerView.Adapter<PastOrderAdapter.MyViewHolder> {


    List<PastOrderModel> list=new ArrayList<>();
    Context context;
    public PastOrderAdapter(List<PastOrderModel> list , Context context)
    {
        this.list=list;
        this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.past_order_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
