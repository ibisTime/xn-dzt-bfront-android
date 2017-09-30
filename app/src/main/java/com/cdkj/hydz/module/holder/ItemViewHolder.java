package com.cdkj.hydz.module.holder;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdkj.baselibrary.appmanager.MyConfig;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.ProductCraftModel;

import java.util.List;

/**
 * Created by lei on 2017/9/14.
 */

public class ItemViewHolder extends BaseHolder<List<ProductCraftModel.ProductCategoryListBean.CraftListBean>> {

    public ImageView imgItem;
    public TextView txtBorder;

    int screenWidth;
    Activity context;

    public ItemViewHolder(Context context, int viewId, ViewGroup parent, int viewType) {
        super(context, viewId, parent, viewType);
        this.context = (Activity) context;

        imgItem = itemView.findViewById(R.id.img_item);
        txtBorder = itemView.findViewById(R.id.txt_border);

        DisplayMetrics metric = new DisplayMetrics();
        this.context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;

        ViewGroup.LayoutParams layoutParams = imgItem.getLayoutParams();
        layoutParams.width = layoutParams.height = screenWidth / 3;
        imgItem.setLayoutParams(layoutParams);
    }

    @Override
    public void refreshData(List<ProductCraftModel.ProductCategoryListBean.CraftListBean> list, final int position) {
        ImgUtils.loadRoundImage(context, MyConfig.IMGURL+list.get(position).getPic(), imgItem);
    }

}
