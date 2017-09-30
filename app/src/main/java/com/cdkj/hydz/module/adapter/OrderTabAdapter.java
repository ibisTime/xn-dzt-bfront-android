package com.cdkj.hydz.module.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdkj.hydz.R;
import com.cdkj.hydz.module.model.OrderDetailModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class OrderTabAdapter extends RecyclerView.Adapter<OrderTabAdapter.ViewHolder> {

    private Activity context;
    private List<OrderDetailModel.ProductBean.ProductVarListBean> list;

    private int screenWidth;//屏幕宽度

    public OrderTabAdapter(Context context, List<OrderDetailModel.ProductBean.ProductVarListBean> list) {
        this.list = list;
        this.context = (Activity) context;
        basicParamInit();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_tab, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(list.get(position).isSelect()){
            holder.mTextView.setTextColor(context.getResources().getColor(R.color.yellow_dab616));
        }else{
            holder.mTextView.setTextColor(context.getResources().getColor(R.color.white));
        }

        ViewGroup.LayoutParams layoutParams = holder.mTextView.getLayoutParams();
        layoutParams.width = screenWidth / list.size();
        holder.mTextView.setLayoutParams(layoutParams);

        holder.mTextView.setText(list.get(position).getName());

        holder.mTextView.setOnClickListener(view -> {
            for (OrderDetailModel.ProductBean.ProductVarListBean model : list){
                model.setSelect(false);
            }

            list.get(position).setSelect(true);
            list.get(position).setPosition(position);
            notifyDataSetChanged();

            EventBus.getDefault().post(list.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView =  view.findViewById(R.id.txt_type);

        }

    }


    /**
     * 计算屏幕的宽度
     */
    private void basicParamInit() {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);

        screenWidth = metric.widthPixels;

    }

}
