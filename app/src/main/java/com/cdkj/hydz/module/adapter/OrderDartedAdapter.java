package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.OrderCraftModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderDartedAdapter extends RecyclerView.Adapter<OrderDartedAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<OrderCraftModel> mData;

    public OrderDartedAdapter(Context mContext, ArrayList<OrderCraftModel> data) {
        this.mData = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_craft, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mData.get(position).isSelect()){
            holder.txtBorder.setBackgroundResource(R.drawable.border_order_blue);
        }else {
            holder.txtBorder.setBackgroundResource(R.drawable.border_order_gray);
        }
        ImgUtils.loadRoundImage(mContext, MyConfig.IMGURL+mData.get(position).getImg(), holder.imgItem);

        holder.txtBorder.setOnClickListener(view -> {
            if(!mData.get(position).isSelect()){
                for (OrderCraftModel model : mData){
                    model.setSelect(false);
                }

                mData.get(position).setSelect(true);

                OrderCraftModel model = mData.get(position);
                model.setEventBusTag(EventTags.DARTED);
                EventBus.getDefault().post(model);

                notifyDataSetChanged();
            }


        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBorder;
        ImageView imgItem;

        public ViewHolder(View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            txtBorder = itemView.findViewById(R.id.txt_border);
        }
    }

}
