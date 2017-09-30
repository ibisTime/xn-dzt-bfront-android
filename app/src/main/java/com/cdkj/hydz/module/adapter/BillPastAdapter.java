package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdkj.hydz.R;

import java.util.List;

/**
 * Created by lei on 2017/8/25.
 */

public class BillPastAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    private ViewHolder holder;

    public BillPastAdapter(Context context, List<String> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_bill_past, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtMonth.setText(list.get(i).split("-")[1]+"月账单");

        return view;
    }

    static class ViewHolder {
        TextView txtMonth;

        ViewHolder(View view) {
            txtMonth = view.findViewById(R.id.txt_month);
        }
    }
}
