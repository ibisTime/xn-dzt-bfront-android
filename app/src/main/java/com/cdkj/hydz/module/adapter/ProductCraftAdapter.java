package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.hydz.R;
import com.cdkj.hydz.module.holder.BaseHolder;
import com.cdkj.hydz.module.holder.GridViewHolder;
import com.cdkj.hydz.module.holder.InputViewHolder;
import com.cdkj.hydz.module.holder.StyleViewHolder;
import com.cdkj.hydz.module.model.ProductCraftModel;

import java.util.List;

/**
 * Created by lei on 2017/9/14.
 */

/**
 * 工艺Adapter 根据kind来判断要显示的item类型
 */
public class ProductCraftAdapter extends RecyclerView.Adapter<BaseHolder> {

    //条目样式
    private final int HEAD_VIEW = 1000;
    private final int INPUT_VIEW = 1001;
    private final int GRID_VIEW = 1002;
    private final int STYLE_VIEW = 1003;

    private Context context;
    private List<ProductCraftModel.ProductCategoryListBean> list;

    public ProductCraftAdapter(Context context, List<ProductCraftModel.ProductCategoryListBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD_VIEW:
                return new BaseHolder(context, R.layout.item_craft_head, parent, viewType);
            case GRID_VIEW:
                return new GridViewHolder(context, R.layout.item_craft_grid, parent, viewType);
            case INPUT_VIEW:
                return new InputViewHolder(context, R.layout.item_craft_input, parent, viewType);
            case STYLE_VIEW:
                return new StyleViewHolder(context, R.layout.item_craft_grid, parent, viewType);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

        if (holder instanceof GridViewHolder) {
            holder.refreshData(list, position - 1);

        } else if (holder instanceof InputViewHolder) {
            holder.refreshData(list, position - 1);

        } else if (holder instanceof StyleViewHolder) {
            holder.refreshData(list, position - 1);
        }


    }


    @Override
    public int getItemCount() {

        return list.size() == 0 ? 0 : list.size() + 1; //+1是因为多一个HEAD_VIEW类型布局
    }

    @Override
    public int getItemViewType(int position) {

        try {
            if (position == 0) {
                return HEAD_VIEW;
            } else {
                if (list.get(position - 1).getKind().equals("2")) {
                    return STYLE_VIEW;
                } else if (list.get(position - 1).getKind().equals("3")) {
                    return INPUT_VIEW;
                } else if (list.get(position - 1).getKind().equals("4")) {
                    return GRID_VIEW;
                } else {
                    return GRID_VIEW;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return HEAD_VIEW;
    }

}
