package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdkj.baselibrary.appmanager.EventTags;
import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ProductModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lei on 2017/8/23.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mContext;
    private List<ProductModel> mData;

    public ProductAdapter(Context mContext, List<ProductModel> data) {
        this.mData = data;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductModel productModel = mData.get(position);
        if (productModel == null) return;
        if (productModel.isSelect()) {
            holder.txtBorder.setBackgroundResource(R.drawable.border_order_blue);

            productModel.setEventBusTag(EventTags.PRODUCT);
            EventBus.getDefault().post(productModel);
            Log.e("EventTags.PRODUCT", "EventTags.PRODUCT");
        } else {
            holder.txtBorder.setBackgroundResource(R.drawable.border_order_gray);
        }
        ImgUtils.loadRoundImage(mContext, MyConfig.IMGURL + productModel.getPic(), holder.imgItem);

        holder.txtName.setText(productModel.getName());


        holder.txtBorder.setOnClickListener(view -> {

            for (ProductModel model : mData) {
                model.setSelect(false);
            }

            productModel.setSelect(true);

            notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {

        return mData == null ? 0 : mData.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBorder;
        TextView txtName;
        ImageView imgItem;

        public ViewHolder(View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            txtBorder = itemView.findViewById(R.id.txt_border);
            txtName = itemView.findViewById(R.id.txt_name);
        }
    }

}
