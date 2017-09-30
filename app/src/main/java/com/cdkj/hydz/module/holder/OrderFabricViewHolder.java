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

public class OrderFabricViewHolder extends BaseHolder<OrderDetailModel.ProductBean.ProductVarListBean.ProductSpecsBean> {

    Activity mActivity;

    private int craftPosition;
    private int colorPosition;

    private TextView txtCraftName;
    private TextView txtCraftValue;
    private LinearLayout layoutFabric;

    public OrderFabricViewHolder(Context context, int viewId, ViewGroup parent, int viewType) {
        super(context, viewId, parent, viewType);
        mActivity = (Activity) context;

        txtCraftName = itemView.findViewById(R.id.txt_craft_name);
        txtCraftValue = itemView.findViewById(R.id.txt_craft_value);
        layoutFabric = itemView.findViewById(R.id.layout_fabric);

    }

    public void refreshData(OrderDetailModel.ProductBean.ProductVarListBean.ProductSpecsBean bean, int position) {
        super.refreshData(bean, position);

        layoutFabric.setVisibility(View.VISIBLE);
        txtCraftName.setText("面料");
        txtCraftValue.setText(bean.getModelNum());

    }

}
