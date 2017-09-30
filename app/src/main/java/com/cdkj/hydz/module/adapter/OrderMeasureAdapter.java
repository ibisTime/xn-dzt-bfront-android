package com.cdkj.hydz.module.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.OrderMeasureModel;

import java.util.ArrayList;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderMeasureAdapter extends RecyclerView.Adapter<OrderMeasureAdapter.ViewHolder> {

    private int position;
    private ArrayList<OrderMeasureModel> mData;

    public OrderMeasureAdapter(ArrayList<OrderMeasureModel> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_measure, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.position = position;
        if (!mData.get(position).isCanInput()){
            holder.edtValue.setEnabled(false);

        }else {
            holder.edtValue.setEnabled(true);

            holder.edtValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    mData.get((Integer) holder.edtValue.getTag()).setValue(editable.toString());
                }
            });
        }

        holder.txtKey.setText(mData.get(position).getName());
        holder.edtValue.setTag(position);
        holder.edtValue.setText(mData.get(position).getValue());


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtKey;
        EditText edtValue;

        public ViewHolder(View itemView) {
            super(itemView);
            txtKey = itemView.findViewById(R.id.txt_key);
            edtValue = itemView.findViewById(R.id.edt_value);
        }
    }


}
