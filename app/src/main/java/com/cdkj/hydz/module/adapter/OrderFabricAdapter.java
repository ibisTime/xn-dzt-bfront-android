package com.cdkj.hydz.module.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class OrderFabricAdapter extends RecyclerView.Adapter<OrderFabricAdapter.ViewHolder> {

    int screenWidth;
    private Activity mContext;
    private ArrayList<OrderCraftModel> mData;

    public OrderFabricAdapter(Context mContext, ArrayList<OrderCraftModel> data) {

        this.mData = data;
        this.mContext = (Activity) mContext;
        Log.e("Adapter mData.size()", mData.size() + "");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_fabric, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        OrderCraftModel orderCraftModel = mData.get(position);
        if (orderCraftModel == null) return;

//        DisplayMetrics metric = new DisplayMetrics();
//        this.mContext.getWindowManager().getDefaultDisplay().getMetrics(metric);
//        screenWidth = metric.widthPixels - dp2px(mContext, 140);
//
//        Log.e("screenWidth", screenWidth + "");
//        Log.e("metric.widthPixels", metric.widthPixels + "");
//
//        ViewGroup.LayoutParams layoutParams = holder.layoutItem.getLayoutParams();
//        layoutParams.width = layoutParams.height = screenWidth / 3;
//
//        Log.e("screenWidth / 3", (screenWidth / 3) + "");
//        holder.layoutItem.setLayoutParams(layoutParams);

        if (orderCraftModel.isSelect()) {
            holder.txtBorder.setBackgroundResource(R.drawable.border_order_blue);
        } else {
            holder.txtBorder.setBackgroundResource(R.drawable.border_order_gray);
        }
        ImgUtils.loadRoundImage(mContext, MyConfig.IMGURL + orderCraftModel.getImg(), holder.imgItem);

        holder.txtCode.setText(orderCraftModel.getModelNum());

        holder.txtBorder.setOnClickListener(view -> {
            if (!orderCraftModel.isSelect()) {
                for (OrderCraftModel model : mData) {
                    model.setSelect(false);
                }

                orderCraftModel.setSelect(true);

                OrderCraftModel model = orderCraftModel;
                model.setEventBusTag(EventTags.FABRIC);
                EventBus.getDefault().post(model);

                notifyDataSetChanged();
            }

        });


    }

    @Override
    public int getItemCount() {

        Log.e("mData.size()", mData.size() + "");
        return mData == null ? 0 : mData.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBorder;
        TextView txtCode;
        ImageView imgItem;
        FrameLayout layoutItem;

        public ViewHolder(View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            txtBorder = itemView.findViewById(R.id.txt_border);
            txtCode = itemView.findViewById(R.id.txt_code);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }
    }


    public static int dp2px(Context context, float dpVal) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,

                dpVal, context.getResources().getDisplayMetrics());

    }
}
