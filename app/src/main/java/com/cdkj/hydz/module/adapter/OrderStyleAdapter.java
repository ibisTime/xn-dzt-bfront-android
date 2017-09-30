package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.OrderStyleModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderStyleAdapter extends RecyclerView.Adapter<OrderStyleAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<OrderStyleModel> mData;


    public OrderStyleAdapter(Context mContext,ArrayList<OrderStyleModel> data) {
        this.mData = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_style, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mData.get(position).isSelect()){
            holder.txtName.setBackgroundResource(R.drawable.corner_order_blue);
            holder.txtName.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.txtName.setBackgroundResource(R.drawable.corner_order_gray);
            holder.txtName.setTextColor(mContext.getResources().getColor(R.color.gray_333333));
        }
        holder.txtName.setText(mData.get(position).getName());

        holder.txtName.setOnClickListener(view -> {
            if(!mData.get(position).isSelect()){
                for (OrderStyleModel model : mData){
                    model.setSelect(false);
                }

                mData.get(position).setSelect(true);
                holder.txtName.setBackgroundResource(R.drawable.corner_order_blue);
                holder.txtName.setTextColor(mContext.getResources().getColor(R.color.white));


                OrderStyleModel model = mData.get(position);
                model.setEventBusTag(EventTags.STYLE);
                EventBus.getDefault().post(model);

                notifyDataSetChanged();
            }


        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);

        }

    }


}
