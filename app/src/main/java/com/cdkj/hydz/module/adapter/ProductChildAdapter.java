package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ProductModel;
import com.cdkj.hydz.module.order.product.CraftActivity;
import com.cdkj.hydz.module.order.product.FabricActivity;

import java.util.List;

/**
 * Created by lei on 2017/9/13.
 */

public class ProductChildAdapter extends BaseAdapter {

    private ViewHolder holder;

    private Context context;
    private List<ProductModel.ModelSpecsListBean> list;

    public ProductChildAdapter(Context context, List<ProductModel.ModelSpecsListBean> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0: list.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_product_child, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtChildProduct.setText(list.get(i).getName());
        holder.txtCraft.setText(MoneyUtils.showPriceWithUnit(list.get(i).getCraftPrice()));
        holder.txtFabric.setText(MoneyUtils.showPriceWithUnit(list.get(i).getFabricPrice()));

        holder.layoutFabric.setOnClickListener(v -> {
            FabricActivity.open(context,list.get(i).getCode());
        });

        holder.layoutCraft.setOnClickListener(v -> {
            CraftActivity.open(context,list.get(i).getCode());
        });

        return view;
    }

    static class ViewHolder {
        TextView txtChildProduct;

        TextView txtFabric;
        LinearLayout layoutFabric;

        TextView txtCraft;
        LinearLayout layoutCraft;

        ViewHolder(View view) {
            txtChildProduct = view.findViewById(R.id.txt_child_product);

            txtFabric = view.findViewById(R.id.txt_fabric);
            layoutFabric = view.findViewById(R.id.layout_fabric);

            txtCraft = view.findViewById(R.id.txt_craft);
            layoutCraft = view.findViewById(R.id.layout_craft);
        }
    }
}
