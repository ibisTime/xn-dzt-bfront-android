package com.cdkj.hydz.module.holder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.OrderDetailModel;

/**
 * Created by lei on 2017/9/14.
 */

public class OrderCraftViewHolder extends BaseHolder<OrderDetailModel.ProductBean.ProductVarListBean.ProductCategoryBeanX> {

    Activity mActivity;

    private TextView txtCraftName;
    private TextView txtCraftValue;

    private TextView txtColorName;
    private TextView txtColorValue;
    private LinearLayout layoutColor;

    public OrderCraftViewHolder(Context context, int viewId, ViewGroup parent, int viewType) {
        super(context, viewId, parent, viewType);
        mActivity = (Activity) context;

        txtCraftName = itemView.findViewById(R.id.txt_craft_name);
        txtCraftValue = itemView.findViewById(R.id.txt_craft_value);

        txtColorName = itemView.findViewById(R.id.txt_color_name);
        txtColorValue = itemView.findViewById(R.id.txt_color_value);
        layoutColor = itemView.findViewById(R.id.layout_color);

    }

    public void refreshData(OrderDetailModel.ProductBean.ProductVarListBean.ProductCategoryBeanX bean, int position) {
        super.refreshData(bean, position);

        txtCraftName.setText(bean.getDvalue());
        if (bean.getProductCraft()!= null){
            txtCraftValue.setText(bean.getProductCraft().getName());
        }

        // 是否有颜色
        if(bean.getProductCategory() != null){
            layoutColor.setVisibility(View.VISIBLE);
            initColorRecycle(bean.getProductCategory(), position);
        }else {
            layoutColor.setVisibility(View.GONE);
        }
    }

    private void initColorRecycle(OrderDetailModel.ProductBean.ProductVarListBean.ProductCategoryBeanX.ProductCategoryBean bean, int position){

        txtColorName.setText(bean.getDvalue());
        if (bean.getColorProductCraft() != null)
            txtColorValue.setText(bean.getColorProductCraft().getName());

    }

}
