package com.cdkj.hydz.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.holder.BaseHolder;
import com.cdkj.hydz.module.holder.OrderCraftViewHolder;
import com.cdkj.hydz.module.holder.OrderFabricViewHolder;
import com.cdkj.hydz.module.model.OrderDetailModel;

import java.util.List;

/**
 * Created by lei on 2017/9/14.
 */

public class OrderCraftAdapter extends RecyclerView.Adapter<BaseHolder>{

    //条目样式
    private final int HEAD_VIEW = 1000;
    private final int ORDER_VIEW = 1001;

    private Context context;
    private List<OrderDetailModel.ProductBean.ProductVarListBean> list;

    public OrderCraftAdapter(Context context, List<OrderDetailModel.ProductBean.ProductVarListBean> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD_VIEW:
                return new OrderFabricViewHolder(context,R.layout.item_craft_head,parent,viewType);
            case ORDER_VIEW:
                return new OrderCraftViewHolder(context, R.layout.item_craft_order, parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (holder instanceof OrderCraftViewHolder) {
            holder.refreshData(list.get(0).getProductCategory().get(position-1), position-1);
        }else if (holder instanceof OrderFabricViewHolder) {
            holder.refreshData(list.get(0).getProductSpecs().get(position), position);
        }

    }


    @Override
    public int getItemCount() {

        if (list.size() == 0){
            return 0;
        }

        return list.get(0).getProductCategory().size() == 0 ? 0 : list.get(0).getProductCategory().size()+1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0){
            return HEAD_VIEW;
        } else {
            return ORDER_VIEW;

        }


    }

}
