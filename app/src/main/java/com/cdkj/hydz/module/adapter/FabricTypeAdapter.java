package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.FabricTypeModel;

import java.util.List;

/**
 * Created by lei on 2017/9/18.
 */

public class FabricTypeAdapter extends BaseAdapter {

    private Context context;
    private ViewHolder holder;
    private List<FabricTypeModel> list;

    public FabricTypeAdapter(Context context, List<FabricTypeModel> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.item_fabric_type, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtName.setText(list.get(i).getValue());
        if (list.get(i).isSelect()){
            holder.txtName.setBackgroundColor(context.getResources().getColor(R.color.white));
        }else {
            holder.txtName.setBackgroundColor(context.getResources().getColor(R.color.gray_cccccc));
        }

        return view;
    }

    static class ViewHolder {
        TextView txtName;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);

        }
    }
}
