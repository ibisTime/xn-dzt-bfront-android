package com.cdkj.hydz.module.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.OrderBodyModel;
import com.cdkj.hydz.module.order.helper.OrderHelper;

import java.util.ArrayList;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderBodyAdapter extends RecyclerView.Adapter<OrderBodyAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<OrderBodyModel> mData;

    public OrderBodyAdapter(Context mContext, ArrayList<OrderBodyModel> data) {
        this.mData = data;
        this.mContext = mContext;
    }

    public void setSelectStatus(boolean canInput){

        for (OrderBodyModel model : mData){
            model.setCanSelect(canInput);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_body, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mData.get(position).isCanSelect()){
            holder.line.setBackgroundColor(mContext.getResources().getColor(R.color.black));
            holder.imgValue.setVisibility(View.VISIBLE);
        }else {
            holder.line.setBackgroundColor(mContext.getResources().getColor(R.color.gray_cccccc));
            holder.imgValue.setVisibility(View.GONE);
        }
        holder.txtKey.setText(mData.get(position).getName());
        holder.txtValue.setText(mData.get(position).getValue());

        holder.layoutValue.setOnClickListener(view -> {
            if (mData.get(position).isCanSelect()){
                showSelect(holder, position);
            }
        });

    }

    private void showSelect(ViewHolder holder, int position) {
        Log.e("key", mData.get(position).getKey());

        String[] value = OrderHelper.getBodyValue(mData.get(position).getKey()).split(",");
        String[] valueKey = OrderHelper.getBodyValueKey(mData.get(position).getKey()).split(",");

        new AlertDialog.Builder(mContext).setTitle("请选择").setSingleChoiceItems(
                value, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        holder.txtValue.setText(value[which]);
                        mData.get(position).setValue(value[which]);
                        mData.get(position).setValueKey(valueKey[which]);

                        dialog.dismiss();
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", null).show();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View line;
        TextView txtKey;
        TextView txtValue;
        ImageView imgValue;
        LinearLayout layoutValue;

        public ViewHolder(View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.line);
            txtKey = itemView.findViewById(R.id.txt_key);
            txtValue = itemView.findViewById(R.id.txt_value);
            imgValue = itemView.findViewById(R.id.img_value);
            layoutValue = itemView.findViewById(R.id.layout_value);
        }
    }

}
